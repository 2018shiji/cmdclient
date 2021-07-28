package com.module.screencmd.cmd;

import com.module.cmd.core.cmdpumper.ICmdPumpStreamHandler;
import com.module.screencmd.BeanUtil;
import com.module.screencmd.parser.AppRecordParseChain;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

@Component
public class ScreenAppRecordParser implements ICmdPumpStreamHandler {
    private AppRecordParseChain appRecordChain = BeanUtil.getBean(AppRecordParseChain.class);

    @Override
    public void parseCmdRecord(BufferedReader bufReader, PrintStream printStream) throws Exception {
        appRecordChain.parseCmdRecord(bufReader, printStream);
    }

    @Override
    public List<? extends ICmdRecordPojo> getCmdParsePojoList() {
        List<? extends ICmdRecordPojo> cmdAppParsePojoList = appRecordChain.getCmdParsePojoList();
        return cmdAppParsePojoList;
    }

}
