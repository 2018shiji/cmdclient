package com.module.cmd.ping.executor;

import com.module.cmd.core.recode.ICmdRecordParser;
import com.module.cmd.core.recode.RealTimePumpStreamHandler;
import com.module.cmd.ping.response.PingResponse;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PingCommand {

    public static Map<String, PingResponse> getPingResponses(){
        return PingRecordParser.getPingResponses();
    }

    public static void doPingCommand(String remoteIP){
        final CommandLine cmdLine = CommandLine.parse("ping " + remoteIP);
        try{
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(null);
            ICmdRecordParser cmdRecordParser = new PingRecordParser(remoteIP);
            executor.setStreamHandler(new RealTimePumpStreamHandler(cmdRecordParser));
            executor.execute(cmdLine);

        } catch (Exception e){e.printStackTrace();}
    }


}
