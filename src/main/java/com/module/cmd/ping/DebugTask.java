package com.module.cmd.ping;

import com.module.cmd.ping.response.PingResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DebugTask implements Job, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("i am here ****************************");
        Map<String, PingResponse> pingResponses = ApacheCli.getPingResponses();
        for(Map.Entry item : pingResponses.entrySet()){
            System.out.println(item.getKey() + item.getValue().toString());
        }
    }

}
