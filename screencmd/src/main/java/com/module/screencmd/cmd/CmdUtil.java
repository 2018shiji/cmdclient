package com.module.screencmd.cmd;

import com.module.screencmd.websocket.WebSocketServer;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CmdUtil {

    public static String startCmdWithOutput(String cmd) throws Exception {
        CommandLine chromesLine = CommandLine.parse(cmd);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValues(null);
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
        executor.setStreamHandler(streamHandler);
        executor.execute(chromesLine);
        String output = outputStream.toString("gbk");
        String err = errorStream.toString("gbk");
        String line = output + "\\r\\n" + err;
        WebSocketServer.BroadCastInfo(line);
        return line;
    }

    public static List<String> interceptProcessID(String output){
        List<String> processIds = new ArrayList<>();
        String lines[] = output.split("\\r?\\n");
        for(int i = 0; i < lines.length; i++){
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(lines[i]);
            if(m.find()) processIds.add(m.group(0));
        }
        processIds.forEach(item -> System.out.print(item + "\t"));
        return processIds;
    }
}
