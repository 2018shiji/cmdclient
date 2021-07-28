package com.module.cmd.ping.executorn;

import com.module.cmd.core.cmdpumpern.ICmdPumpStreamHandlerN;
import com.module.cmd.ping.pojo.PingJobDetail;
import com.module.cmd.ping.response.PingResponse;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class PingPumpStreamHandlerN implements ICmdPumpStreamHandlerN<PingResponse> {
    private PingJobDetail ping;

    public PingPumpStreamHandlerN(PingJobDetail ping){
        this.ping = ping;
    }

    @Override
    public PingResponse parseCmdRecord(BufferedReader bufReader, PrintStream outputStream) throws Exception {
        String line;
        PingResponse response = new PingResponse();
        while((line = bufReader.readLine()) != null){
            System.out.println(">------> get cmd ping line: " + line + Thread.currentThread().getName());
            if(line.contains("正在 Ping") || line.equals(""))
                continue;

            response.setIpAddress(ping.getIp());
            String timeStamp = new SimpleDateFormat("HH:mm:ss:SSS").format(System.currentTimeMillis());
            response.setTimeStamp(timeStamp);
            response.setMessage(line);
            response.setOnline(true);
            if(line.contains("请求超时") || line.contains("100% 丢失")){ response.setOnline(false); }
        }
        System.out.println(response);
        return response;
    }

}
