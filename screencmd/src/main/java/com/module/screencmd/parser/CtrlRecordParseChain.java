package com.module.screencmd.parser;

import com.cmdclient.core.recode.ICmdRecordParser;
import com.cmdclient.core.recode.ICmdRecordPojo;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.module.screencmd.pojo.CtrlCmdRecordPojo;
import com.module.screencmd.websocket.WebSocketServer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class CtrlRecordParseChain implements ICmdRecordParser {
    public List<ICmdRecordParser> ctrlRecordParserChain;
    public List<CtrlCmdRecordPojo> ctrlCmdRecordPojoList;

    @PostConstruct
    public void initCtrlRecordParserChain() {
        ctrlRecordParserChain = new ArrayList<>();
    }

    @Override
    public void parseCmdRecord(BufferedReader bufReader, PrintStream printStream) throws Exception {
        ctrlCmdRecordPojoList = new ArrayList<>();
        /**
         * 从固定路径读取输出日志作为返回数据
         */
        if (bufReader.readLine() == null) {
            Files.asCharSource(new File("D:\\log\\serviceErr2.log"), Charsets.UTF_8).readLines(
                    new LineProcessor<String>() {
                        @Override
                        public boolean processLine(String line) {
                            WebSocketServer.BroadCastInfo(line);
                            return true;
                        }

                        @Override
                        public String getResult() {
                            return null;
                        }
                    }
            );

        }

    }

    @Override
    public List<? extends ICmdRecordPojo> getCmdParsePojoList() {
        return ctrlCmdRecordPojoList;
    }
}
