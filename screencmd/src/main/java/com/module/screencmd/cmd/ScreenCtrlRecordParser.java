package com.module.screencmd.cmd;

import com.module.cmd.core.cmdpumper.ICmdPumpStreamHandler;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

@Component
public class ScreenCtrlRecordParser implements ICmdPumpStreamHandler {

    @Override
    public void parseCmdRecord(BufferedReader bufReader, PrintStream printStream) throws Exception {
        ctrlRecordChain.parseCmdRecord(bufReader, printStream);
    }

    @Override
    public List<? extends ICmdRecordPojo> getCmdParsePojoList() {
        List<? extends ICmdRecordPojo> ctrlPojoList = ctrlRecordChain.getCmdParsePojoList();
        return ctrlPojoList;
    }
}
