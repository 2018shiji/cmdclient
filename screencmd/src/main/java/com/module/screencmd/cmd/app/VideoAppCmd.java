package com.module.screencmd.cmd.app;

import com.module.screencmd.cmd.CmdUtil;
import org.apache.commons.exec.CommandLine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AR: start "" vlc  "rtsp://10.28.120.5:9090/dss/monitor/param?cameraid=1000054$24&substream=1&channel=1" --no-video-title-show --fullscreen --video-title "T4AR全景"
 *无人机实时: start "" vlc "rtsp://10.28.120.5:9090/dss/monitor/param?cameraid=1000076$0&substream=1&channel=1" --no-video-title-show --fullscreen --video-title "无人机实时"
 *巡逻机器人实时: start "" vlc "rtsp://10.28.120.5:9090/dss/monitor/param?cameraid=1000054%2479&substream=1"  --no-video-title-show --fullscreen --video-title "巡逻机器人实时"
 *
 * 无人机录像: start "" vlc  "D:\Software\播放文件\无人机环绕.mp4" --no-video-title-show --no-video-title-show --fullscreen --video-title "无人机环绕" --repeat
 * 无人驾驶录像: start "" vlc  "D:\Software\播放文件\无人驾驶-928-车内.mp4" --no-video-title-show --fullscreen --no-video-title-show --video-title "无人驾驶-928-车内" --repeat
 * 妈湾智慧港: start "" vlc  "C:\Users\admin\Desktop\sct行政接待\新建文件夹\视频\妈湾智慧港成果展示发布会0826最终.mp4" --no-video-title-show --fullscreen --video-title "妈湾成果视频" --repeat
 */
@Component
public class VideoAppCmd {
    private List<String> urls = new ArrayList<>();
    private List<String> videoTitles = new ArrayList<>();

    public void initVideoAppCmd(List<String> urls, List<String> videoTitles){
        this.urls = urls;
        this.videoTitles = videoTitles;
    }

    public void startVideoApp(){
        try{
            for(int i = 0; i < urls.size(); i++){
                String line = "cmd.exe /c start \"\" vlc  \"" + urls.get(i) + "\" --no-video-title-show --fullscreen --video-title \"" + videoTitles.get(i) + "\"";
                System.out.println(CmdUtil.startCmdWithOutput(line));
                Thread.sleep(1000);
            }
        } catch (Exception e){e.printStackTrace();}
    }

    public void taskKillAllVlc(){
        try{
            String line = "taskkill /f /im vlc.exe /t";
            System.out.println(CmdUtil.startCmdWithOutput(line));
        } catch (Exception e){e.printStackTrace();}
    }

    public void showAllVlcProcess(){
        try{
            String vlcs = "cmd.exe /c tasklist | findstr vlc.exe";
            String output = CmdUtil.startCmdWithOutput(vlcs);
            List<String> vlcIds = CmdUtil.interceptProcessID(output);
        } catch (Exception e){e.printStackTrace();}
    }

    public void taskKillVlcByPID(String PID){
        try{
            String toBeKilled = "cmd.exe /c taskkill /fi /pid" + PID;
            System.out.println(CmdUtil.interceptProcessID(toBeKilled));
        } catch (Exception e){e.printStackTrace();}
    }

    public static void main(String[] args){
        String url1 = "";
        String url2 = "";
        String url3 = "";
        List<String> urls = new ArrayList<>(Arrays.asList(url1, url2, url3));

        String window1Title = "";
        String window2Title = "";
        String window3Title = "";
        List<String> windowTitles = new ArrayList<>(Arrays.asList(window1Title, window2Title, window3Title));

        String url1Title = "";
        String url2Title = "";
        String url3Title = "";
        List<String> titles = new ArrayList(Arrays.asList(url1Title, url2Title, url3Title));

        VideoAppCmd videoAppCmd = new VideoAppCmd();
        videoAppCmd.initVideoAppCmd(urls, titles);
        videoAppCmd.startVideoApp();

    }

}
