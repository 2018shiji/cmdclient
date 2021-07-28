package com.module.screencmd.cmd;

import com.module.cmd.core.cmdpumper.ICmdPumpStreamHandler;
import com.module.cmd.core.cmdpumper.RealTimePumpStreamHandler;
import com.module.screencmd.BeanUtil;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * String line = "cmd.exe /c cd C:\\Users\\lizhuangjie.chnet\\Desktop\\ScreenServiceHost && application.bat";
 */
@Component
public class ScreenCtrlCommand {
    private ICmdPumpStreamHandler ctrlRecordParser = BeanUtil.getBean(ScreenCtrlRecordParser.class);

    public void doScreenControlCmd(String path){
        try{
            String line = "E:\\software\\nircmd-x64\\nircmd.exe elevate E:\\software\\nssm-2.24-101-g897c7ad\\win64\\nssm.exe restart ScreenService";
//            String line = "C:\\Users\\admin\\Desktop\\ScreenServiceHost\\nircmd\\nircmd.exe elevate C:\\Users\\admin\\Desktop\\ScreenServiceHost\\nssm-2.24-101-g897c7ad\\win64\\nssm.exe restart ScreenServiceCtrl";
            CommandLine cmdLine = CommandLine.parse(line);
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(null);

            executor.setStreamHandler(new RealTimePumpStreamHandler(ctrlRecordParser));

            executor.execute(cmdLine);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static void main(String[] args){
        new ScreenCtrlCommand().doScreenControlCmd(null);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try{
            countDownLatch.await();
        }catch(InterruptedException e){e.printStackTrace();}
    }
}
