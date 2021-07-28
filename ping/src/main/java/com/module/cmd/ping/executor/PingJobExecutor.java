package com.module.cmd.ping.executor;

import com.module.cmd.core.cmdpumper.RealTimePumpStreamHandler;
import com.module.cmd.ping.response.PingResponse;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.LinkedBlockingDeque;

import static com.module.cmd.ping.executor.PingScheduler.REMOTE_ADDR_KEY;

public class PingJobExecutor implements Job {
    private static LinkedBlockingDeque<PingResponse> pingProductList = new LinkedBlockingDeque();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        doPingCommand((String)jobDataMap.get(REMOTE_ADDR_KEY));
    }

    private void doPingCommand(String remoteIP){
        final CommandLine cmdLine = CommandLine.parse("ping " + remoteIP);
        try{
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(null);
            PingPumpStreamHandler cmdRecordParser = new PingPumpStreamHandler(remoteIP);
            executor.setStreamHandler(new RealTimePumpStreamHandler(cmdRecordParser));
            executor.execute(cmdLine);//阻塞

            pingProductList.add(cmdRecordParser.getPingResponse());
        } catch (Exception e){e.printStackTrace();}
    }

    public static void clearPingProductList(){
        pingProductList = new LinkedBlockingDeque<>();
    }

    public static LinkedBlockingDeque<PingResponse> getPingProductList(){
        return pingProductList;
    }

}
