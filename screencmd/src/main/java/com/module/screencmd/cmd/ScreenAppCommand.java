package com.module.screencmd.cmd;

import com.module.cmd.core.recode.ICmdRecordParser;
import com.module.cmd.core.recode.RealTimePumpStreamHandler;
import com.module.screencmd.BeanUtil;
import com.module.screencmd.cmd.app.VideoAppCmd;
import com.module.screencmd.cmd.app.WebAppCmd;
import com.module.screencmd.pojo.AppCmdRecordPojo;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class ScreenAppCommand {
    @Autowired
    private VideoAppCmd videoAppCmd;
    @Autowired
    private WebAppCmd webAppCmd;

    private ICmdRecordParser cmdRecordParser = BeanUtil.getBean(ScreenAppRecordParser.class);

    public void doScreenAppCmdByBat(String execPath){
        try{
//            String line = "cmd.exe /c cd C:\\Users\\lizhuangjie.chnet\\Desktop\\ScreenServiceHost && ScreenServiceHost.exe";
//            String line = "cmd.exe /c start C:\\Users\\Administrator\\Desktop\\ScreenServiceHost\\ScreenServiceHost.exe";
            String line = "cmd.exe /c cd C:\\Users\\admin\\Desktop\\ScreenServiceHost && StartScene.cmd";
//            String line = "cmd.exe /k tasklist | findstr java";
            CommandLine cmdLine = CommandLine.parse(line);
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(null);
            executor.setStreamHandler(new RealTimePumpStreamHandler(cmdRecordParser));
            executor.execute(cmdLine);

        } catch (Exception e){e.printStackTrace();}
    }

    public void startUpWebApp(){
        webAppCmd.startWebApp();
    }

    public void startUpVideoApp(){
        videoAppCmd.startVideoApp();
    }

    public void killAllWebApp(){
        webAppCmd.taskKillAllChrome();
    }

    public void killAllVideoApp(){
        videoAppCmd.taskKillAllVlc();
    }

    public void doScreenAppVolume(){
        try {
            String line1 = "E:\\software\\nircmd-x64\\nircmd.exe mutesysvolume 0";
            String line2 = "E:\\software\\nircmd-x64\\nircmd.exe setsysvolume 10000";

            CommandLine cmdLine1 = CommandLine.parse(line1);
            CommandLine cmdLine2 = CommandLine.parse(line2);

            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(null);
            executor.execute(cmdLine1);
            executor.execute(cmdLine2);
        } catch (Exception e) {e.printStackTrace();}
    }

    public List<AppCmdRecordPojo> getAppCmdRecordPojoList() {
        if(cmdRecordParser == null) {
            System.out.println("尚未执行cmd指令和CmdRecordParser的初始化工作");
            return null;
        }
        return (List<AppCmdRecordPojo>) cmdRecordParser.getCmdParsePojoList();
    }

    public static void main(String[] args){
        new ScreenAppCommand().doScreenAppCmdByBat(null);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try{
            countDownLatch.await();
        }catch (InterruptedException e){e.printStackTrace();}
    }

}
