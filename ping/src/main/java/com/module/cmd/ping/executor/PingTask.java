package com.module.cmd.ping.executor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static com.module.cmd.ping.executor.DeviceMonitor.REMOTE_ADDR_KEY;

public class PingTask implements Job, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ApacheCli.doPingLogic((String)jobExecutionContext.getJobDetail().getJobDataMap().get(REMOTE_ADDR_KEY));
    }
}
