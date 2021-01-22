package com.module.screencmd.survive;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ServiceSurvive {
    private DefaultExecutor exec = new DefaultExecutor();

    public String isClientScreenJarSurvive(String hostIp){
        String cmdLineStr = "";
        return isServiceSurvive(hostIp, cmdLineStr);
    }

    public String isClientScreenCtrlSurvive(String hostIp){
        String cmdLineStr = "tasklist /fi \"imagename eq ScreenServiceHost.exe\"";
        return isServiceSurvive(hostIp, cmdLineStr);
    }

    public String isServerScreenServiceSurvive(String hostIP){
        String cmdLineStr = "tasklist /fi \"imagename eq HikServer.exe\"";
        return isServiceSurvive(hostIP, cmdLineStr);
    }

    //IIS 服务器
    public String isServerScreenIISCtrlSurvive(String hostIP){
        String cmdLineStr = "tasklist /fi \"imagename eq IIS.exe";
        return isServiceSurvive(hostIP, cmdLineStr);
    }

    //Server端nginx服务器
    public String isServerScreenNginxCtrlSurvive(String hostIP){
        String cmdLineStr = "tasklist /fi \"imagename eq Nginx.exe";
        return isServiceSurvive(hostIP, cmdLineStr);
    }

    public String isServiceSurvive(String hostIp, String cmdLineStr){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

        final CommandLine cmdLine = CommandLine.parse(cmdLineStr);
        try{
            exec.setExitValues(null);
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
            exec.setStreamHandler(streamHandler);
            exec.execute(cmdLine);
            String output = outputStream.toString("gbk");
            String error = errorStream.toString("gbk");
            return output + "\n" + error;
        } catch (Exception e){
            e.printStackTrace();
            return e.toString();
        } finally {
            try {
                outputStream.close();
                errorStream.close();
            } catch (IOException e){e.getMessage();}
        }
    }

    public static void main(String[] args){
        ServiceSurvive serviceSurvive = new ServiceSurvive();
        System.out.println(serviceSurvive.isClientScreenCtrlSurvive(null));
    }
}
