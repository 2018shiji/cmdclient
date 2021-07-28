package com.module.cmd.ping.executor;

import com.module.cmd.ping.response.PingResponse;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class PingScheduler {
    public static final String REMOTE_ADDR_KEY = "remoteAddr";
    public static final Map<String, PingResponse> responseMap = new LinkedHashMap<>();


    public void schedulePingJob(List<String> remoteAddr){
        try{
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();

            PingJobExecutor.clearPingProductList();
            scheduleServerJob(scheduler, null);
            scheduleRFIDJob(scheduler, null);
            scheduleCameraJob(scheduler, null);
            scheduleFieldBridgeJob(scheduler, null);

        } catch (Exception e){e.printStackTrace();}
    }

    public Map<String, PingResponse> getResponseMap(){
        LinkedBlockingDeque<PingResponse> pingProductList = PingJobExecutor.getPingProductList();
        pingProductList.forEach(response -> { responseMap.put(response.getIpAddress(), response); });
        String timeStamp = new SimpleDateFormat("HH:mm:ss:SSS").format(System.currentTimeMillis());
        responseMap.put("standardTimeStamp",
                new PingResponse("standardTimeStamp", false, "", timeStamp));

        return responseMap;
    }

    private void scheduleServerJob(Scheduler scheduler, List<String> addresses) throws SchedulerException {
        List<String> addressList = new ArrayList<>();
        addressList.addAll(IPProvider.MAIN_SERVER_ADDRESSES);

        for(int i = 0; i < addressList.size(); i++){
            scheduler.scheduleJob(withPingJobDetail(addressList.get(i)),
                    withTriggerPerSeconds(0, 30));
        }
    }

    private void scheduleRFIDJob(Scheduler scheduler, List<String> addresses) throws SchedulerException {
        List<String> addressList = new ArrayList<>();
        addressList.addAll(IPProvider.RFID_ADDRESSES);

        for (int i = 0; i < addressList.size(); i++) {
            scheduler.scheduleJob(withPingJobDetail(addressList.get(i)),
                    withTriggerPerSeconds(10, 30));
        }
    }

    private void scheduleCameraJob(Scheduler scheduler, List<String> addresses) throws SchedulerException {
        List<String> addressList = new ArrayList<>();
        addressList.addAll(IPProvider.CAMERA_ADDRESSES);

        for (int i = 0; i < addressList.size(); i++) {
            scheduler.scheduleJob(withPingJobDetail(addressList.get(i)),
                    withTriggerPerSeconds(10, 30));
        }
    }

    private void scheduleFieldBridgeJob(Scheduler scheduler, List<String> addresses) throws SchedulerException {
        List<String> addressList = new ArrayList<>();
        addressList.addAll(IPProvider.FIELD_BRIDGE_ADDRESSES);

        for (int i = 0; i < addressList.size(); i++) {
            scheduler.scheduleJob(withPingJobDetail(addressList.get(i)),
                    withTriggerPerSeconds(20, 30));
        }
    }

    private JobDetail withPingJobDetail(String remoteAddr){
        responseMap.put(remoteAddr, new PingResponse());
        JobDetail jobDetail = JobBuilder.newJob(PingJobExecutor.class)
                .usingJobData(REMOTE_ADDR_KEY, remoteAddr)
                .build();
        return jobDetail;
    }

    private Trigger withTriggerPerSeconds(int cap, int seconds){
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(cap + "/" + seconds + " * * * * ? 2020"))
                .build();
        return trigger;
    }

}
