package com.module.screencmd.cmd.app;

import com.module.screencmd.cmd.CmdUtil;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AIS: start "" chrome "http://10.28.29.50:7901/" --start-fullscreen --new-window
 * GIS: start "" chrome "http://10.28.29.50:7902/gis/realTime" --start-fullscreen --new-window
 * BI: start "" chrome "http://10.28.29.50:7904/powerbi/gate" --start-fullscreen --new-window
 * 智慧气象: start "" chrome "http://10.28.29.50:7906" --start-fullscreen --new-window
 * 智慧安全: start "" chrome "http://10.28.29.27:7908/sams/security-monitoring" --start-fullscreen --new-window
 * ePort: start "" chrome "http://eport.szvsc.com" --start-fullscreen --new-window
 */
@Component
public class WebAppCmd {
    private List<String> hosts = new ArrayList<>();
    private List<String> windowTitles = new ArrayList<>();

    public void initWebAppCmd(List<String> hosts, List<String> windowTitles){
        this.hosts = hosts;
        this.windowTitles = windowTitles;
    }

    public void startWebApp(){
        try{
            for(int i = 0; i < hosts.size(); i++) {
                String line = "cmd.exe /c start \"\" chrome " + hosts.get(i) + " --start-fullscreen --new-window";
                System.out.println(CmdUtil.startCmdWithOutput(line));
                Thread.sleep(1000);
            }

        } catch (Exception e){e.printStackTrace();}
    }

    public void taskKillAllChrome(){
        try{
            String line = "taskkill /f /im chrome.exe /t";
            System.out.println(CmdUtil.startCmdWithOutput(line));
        } catch (Exception e){e.printStackTrace();}
    }

    public void taskKillConcreteChrome(String windowTitle){
        try{
            String line = "taskkill /fi \"windowtitle eq " + windowTitle + "*\"";
            System.out.println(CmdUtil.startCmdWithOutput(line));

        } catch (Exception e){e.printStackTrace();}
    }

    public void showAllChromeProcess(){
        try {
            String chromes = "cmd.exe /c tasklist | findstr chrome.exe";
            String output = CmdUtil.startCmdWithOutput(chromes);
            List<String> chromeIds = CmdUtil.interceptProcessID(output);
        }catch(Exception e) {e.printStackTrace();}
    }

    public static void main(String[] args) throws Exception{
        String host1 = "https://www.baidu.com";
        String host2 = "http://www.bilibili.com";
        String host3 = "http://www.hupu.com";
        List<String> hosts = new ArrayList<>();
        hosts.addAll(Arrays.asList(host1, host2, host3));

        String windowTitle1 = "windowTitle1";
        String windowTitle2 = "windowTitle2";
        String windowTitle3 = "windowTitle3";
        List<String> windowTitles = new ArrayList<>();
        windowTitles.addAll(Arrays.asList(windowTitle1, windowTitle2, windowTitle3));

        WebAppCmd webAppCmd = new WebAppCmd();
        webAppCmd.initWebAppCmd(hosts, windowTitles);
        webAppCmd.startWebApp();

        Thread.sleep(10000);

        String windowTitle = "虎扑";
        webAppCmd.taskKillConcreteChrome(windowTitle);

    }

}
