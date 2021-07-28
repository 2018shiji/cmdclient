package com.module.cmd.ping.executor;

import com.module.cmd.core.cmdpumper.ICmdPumpStreamHandler;
import com.module.cmd.ping.response.PingResponse;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class PingPumpStreamHandler implements ICmdPumpStreamHandler {
    private String remoteIP;
    private PingResponse pingResponse;

    public PingPumpStreamHandler(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public PingResponse getPingResponse(){
        return pingResponse;
    }

    @Override
    public void parseCmdRecord(BufferedReader bufReader, PrintStream printStream) throws Exception{
        String line = null;
        while((line = bufReader.readLine()) != null) {
            printStream.println(remoteIP + "\t" + line  + "\t" + System.currentTimeMillis());
            if(line.contains("正在 Ping") || line.equals(""))
                continue;

            pingResponse = new PingResponse();
            pingResponse.setIpAddress(remoteIP);
            String timeStamp = new SimpleDateFormat("HH:mm:ss:SSS").format(System.currentTimeMillis());
            pingResponse.setTimeStamp(timeStamp);
            pingResponse.setMessage(line);
            pingResponse.setOnline(true);
            if(line.contains("请求超时") || line.contains("100% 丢失")){
                pingResponse.setOnline(false);
            }
        }
    }

}
