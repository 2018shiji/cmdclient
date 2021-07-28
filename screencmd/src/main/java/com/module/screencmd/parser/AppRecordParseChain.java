package com.module.screencmd.parser;

import com.module.cmd.core.cmdpumper.ICmdPumpStreamHandler;
import com.module.screencmd.pojo.AppCmdRecordPojo;
import com.module.screencmd.websocket.WebSocketServer;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppRecordParseChain implements ICmdPumpStreamHandler {
    public List<AppCmdRecordPojo> appCmdRecordPojoList;

    @Override
    public void parseCmdRecord(BufferedReader bufReader, PrintStream printStream) throws Exception {
        appCmdRecordPojoList = new ArrayList<>();
        String line;
        while((line = bufReader.readLine()) != null) {
            WebSocketServer.BroadCastInfo(line);

            String regex = "^(.:)(.*?)>";
            String[] split = line.split(regex);
            if(split.length < 2)continue;

            String[] results = split[1].split("\\s+");
        }
    }

    @Override
    public List<? extends ICmdRecordPojo> getCmdParsePojoList() {
        return appCmdRecordPojoList;
    }


}
