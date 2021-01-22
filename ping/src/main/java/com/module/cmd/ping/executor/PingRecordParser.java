package com.module.cmd.ping.executor;

import com.module.cmd.core.recode.ICmdRecordParser;
import com.module.cmd.core.recode.ICmdRecordPojo;
import com.module.cmd.ping.response.PingResponse;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PingRecordParser implements ICmdRecordParser {
    private static Map<String, PingResponse> pingResponses = new LinkedHashMap<>();
    private String remoteIP;

    public PingRecordParser(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public static Map<String, PingResponse> getPingResponses(){
//        Map<String, PingResponse> pingResponseMap = new LinkedHashMap<>();
//        pingResponseMap.put("10.28.56.121", new PingResponse("10.28.56.121", true, "test message RFID", "testTimeStamp"));
//        pingResponseMap.put("10.28.56.122", new PingResponse("10.28.56.122", false, "test message 2", "testTimeStamp2"));
//        pingResponseMap.put("10.28.56.21", new PingResponse("10.28.56.21", false, "test message camera",  "testTimeStamp3"));
//        pingResponseMap.put("10.28.56.61", new PingResponse("10.28.56.61", true, "field bridge 1", "test bridge1"));
//        pingResponseMap.put("10.28.56.62", new PingResponse("10.28.56.62", true, "field bridge 2", "test bridge2"));
//        pingResponseMap.put("10.28.56.63", new PingResponse("10.28.56.63", false, "field bridge 3", "test bridge3"));
//        pingResponseMap.put("10.28.56.10", new PingResponse("10.28.56.10", true, "server...", "server..."));
//        pingResponseMap.put("10.28.56.12", new PingResponse("10.28.56.12", true, "server....", "server...."));
//        pingResponseMap.put("10.28.56.13", new PingResponse("10.28.56.13", false, "server.....", "server....."));
//        return pingResponseMap;
        return pingResponses;
    }

    @Override
    public void parseCmdRecord(BufferedReader bufReader, PrintStream printStream) throws Exception{
        String line = null;
        while((line = bufReader.readLine()) != null) {
            printStream.println(remoteIP + "\t" + line  + "\t" + System.currentTimeMillis());
            if(line.contains("正在 Ping") || line.equals(""))continue;
            PingResponse pingResponse = new PingResponse();
            pingResponse.setIpAddress(remoteIP);
            String timeStamp = new SimpleDateFormat("HH:mm:ss:SSS").format(System.currentTimeMillis());
            pingResponse.setTimeStamp(timeStamp);
            pingResponse.setMessage(line);
            pingResponse.setOnline(true);
            if(line.contains("请求超时") || line.contains("100% 丢失")){ pingResponse.setOnline(false); }
            pingResponses.put(remoteIP, pingResponse);
        }
    }

    @Override
    public List<? extends ICmdRecordPojo> getCmdParsePojoList() {
        return null;
    }
}
