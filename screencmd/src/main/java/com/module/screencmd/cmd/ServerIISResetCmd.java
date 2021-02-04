package com.module.screencmd.cmd;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class ServerIISResetCmd {
    public void doServerIISReset(){
        try{
            String line = "cmd.exe /c iisreset";

            System.out.println(CmdUtil.startCmdWithOutput(line));

        } catch (Exception e){e.printStackTrace();}
    }

    public static void main(String[] args){
        ServerIISResetCmd serverIISResetCmd = new ServerIISResetCmd();
        serverIISResetCmd.doServerIISReset();
    }
}
