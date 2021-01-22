package com.module.screencmd.parser;

import com.module.cmd.core.recode.ICmdRecordParser;
import com.module.cmd.core.recode.ICmdRecordPojo;
import com.module.screencmd.parser.app_parser.AppRecordParseCategory;
import com.module.screencmd.parser.app_parser.AppRecordParseOnOffArg;
import com.module.screencmd.parser.app_parser.AppRecordParseOption;
import com.module.screencmd.pojo.AppCmdRecordPojo;
import com.module.screencmd.websocket.WebSocketServer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppRecordParseChain implements ICmdRecordParser {
    public List<IAppRecordParser> appRecordParserChain;
    public List<AppCmdRecordPojo> appCmdRecordPojoList;

    @PostConstruct
    public void initAppRecordParserChain(){
        appRecordParserChain = new ArrayList<>();
        appendAppRecordParser(new AppRecordParseOption());
        appendAppRecordParser(new AppRecordParseOnOffArg());
        appendAppRecordParser(new AppRecordParseCategory());
    }

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
            AppCmdRecordPojo appCmdRecordPojo = new AppCmdRecordPojo();
            for (int i = 0; i < appRecordParserChain.size(); i++) {
                results = appRecordParserChain.get(i).parseAppRecord(results, appCmdRecordPojo);
            }
            appCmdRecordPojoList.add(appCmdRecordPojo);
        }
    }

    @Override
    public List<? extends ICmdRecordPojo> getCmdParsePojoList() {
        return appCmdRecordPojoList;
    }

    public void appendAppRecordParser(IAppRecordParser parser){
        appRecordParserChain.add(parser);
    }

}
