package com.module.screencmd;

import com.module.screencmd.cmd.ScreenAppCommand;
import com.module.screencmd.cmd.ScreenCtrlCommand;
import com.module.screencmd.cmd.ServerIISResetCmd;
import com.module.screencmd.cmd.app.VideoAppCmd;
import com.module.screencmd.cmd.app.WebAppCmd;
import com.module.screencmd.pojo.AppCmdRecordPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class Navigator {
    Logger logger = LoggerFactory.getLogger(Navigator.class);

    @ResponseBody
    @RequestMapping("startUpAppScript")
    public String startUpAppScript(){
        System.out.println("开始运行应用程序启动脚本");
        BeanUtil.getBean(ScreenAppCommand.class).doScreenAppCmdByBat(null);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("startUpCtrlScript")
    public boolean startUpCtrlScript(){
        System.out.println("开始运行屏控程序启动脚本");
        BeanUtil.getBean(ScreenCtrlCommand.class).doScreenControlCmd(null);
        return true;
    }

    @ResponseBody
    @RequestMapping("resetServerIIS")
    public void resetServerIIS(){
        ServerIISResetCmd iisResetCmd = BeanUtil.getBean(ServerIISResetCmd.class);
        System.out.println("执行服务端IIS重启命令");
        iisResetCmd.doServerIISReset();
    }

    @ResponseBody
    @RequestMapping("initAllVideo")
    public void initAllVideoApp(@RequestParam Map<String, String> videoMap){
        System.out.println(videoMap);
        String videoTitle = videoMap.get("videoTitle");
        List<String> videoTitleList = stringToList(videoTitle);
        String url = videoMap.get("url");
        List<String> urlList = stringToList(url);
        BeanUtil.getBean(VideoAppCmd.class).initVideoAppCmd(urlList, videoTitleList);
    }

    @ResponseBody
    @RequestMapping("startUpVideoApp")
    public void startUpVideoApp(){
        System.out.println("启动视频");
        BeanUtil.getBean(ScreenAppCommand.class).startUpVideoApp();
    }

    @ResponseBody
    @RequestMapping("taskKillAllVideo")
    public void taskKillAllVideoApp(){
        System.out.println("终止所有video进程");
        BeanUtil.getBean(ScreenAppCommand.class).killAllVideoApp();
    }

    @ResponseBody
    @RequestMapping("initAllWebPage")
    public void initAllWebApp(@RequestParam Map<String, String> webMap){
        System.out.println(webMap);
        String windowTitle = webMap.get("windowTitle");
        List<String> winTitleList = stringToList(windowTitle);
        String url = webMap.get("url");
        List<String> urlList = stringToList(url);
        BeanUtil.getBean(WebAppCmd.class).initWebAppCmd(urlList, winTitleList);
    }

    @ResponseBody
    @RequestMapping("startUpWebPage")
    public void startUpWebPage(){
        System.out.println("启动网页");
        BeanUtil.getBean(ScreenAppCommand.class).startUpWebApp();
    }

    @ResponseBody
    @RequestMapping("taskKillAllWebPage")
    public void taskKillAllWebApp(){
        System.out.println("终止所有Chrome进程");
        BeanUtil.getBean(ScreenAppCommand.class).killAllWebApp();
    }

    private List<String> stringToList(String oralStr){
        if(StringUtils.isEmpty(oralStr))
            return new ArrayList<>();
        String[] stringArray = StringUtils.commaDelimitedListToStringArray(oralStr.substring(1, oralStr.length() - 1));
        System.out.println(stringArray);
        for(int i = 0; i < stringArray.length; i++){
            stringArray[i].trim();
        }
        System.out.println(stringArray);
        return Arrays.asList(stringArray);
    }

}
