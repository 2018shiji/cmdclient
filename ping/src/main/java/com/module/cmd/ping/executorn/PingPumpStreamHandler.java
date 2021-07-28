package com.module.cmd.ping.executorn;

import com.module.cmd.core.RealTimePumpHandler;
import com.module.cmd.ping.pojo.PingJobDetail;
import com.module.cmd.ping.response.PingResponse;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;

public class PingPumpStreamHandler extends RealTimePumpHandler<PingResponse> {
    private PingJobDetail ping;

    public PingPumpStreamHandler(PingJobDetail ping){
        super(new LinkedBlockingQueue<>());
        this.ping = ping;
    }

    @Override
    public PingResponse parseCmdRecord(BufferedReader bufReader, PrintStream outputStream) throws Exception {
        String line;
        PingResponse response = new PingResponse();
        while((line = bufReader.readLine()) != null){
            if(line.contains("正在 Ping") || line.equals(""))
                continue;

            response.setIpAddress(ping.getIp());
            String timeStamp = new SimpleDateFormat("HH:mm:ss:SSS").format(System.currentTimeMillis());
            response.setTimeStamp(timeStamp);
            response.setMessage(line);
            response.setOnline(true);
            if(line.contains("请求超时") || line.contains("100% 丢失")){ response.setOnline(false); }
        }
        return response;
    }
}
