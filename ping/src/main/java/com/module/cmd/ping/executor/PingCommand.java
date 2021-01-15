package com.module.cmd.ping.executor;

import com.cmdclient.core.recode.ICmdRecordParser;
import com.cmdclient.core.recode.RealTimePumpStreamHandler;
import com.module.cmd.ping.response.PingResponse;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.util.DebugUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@Component
public class PingCommand {

    public static Map<String, PingResponse> getPingResponses(){
        return PingRecordParser.getPingResponses();
    }

    public static void doPingCommand(String remoteIP){
        final CommandLine cmdLine = CommandLine.parse("ping " + remoteIP);
        try{
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(null);
            ICmdRecordParser cmdRecordParser = new PingRecordParser(remoteIP);
            executor.setStreamHandler(new RealTimePumpStreamHandler(cmdRecordParser));
            executor.execute(cmdLine);

        } catch (Exception e){e.printStackTrace();}
    }


}
