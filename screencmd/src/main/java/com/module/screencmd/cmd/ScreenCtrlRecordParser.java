package com.module.screencmd.cmd;

import com.cmdclient.core.recode.ICmdRecordParser;
import com.cmdclient.core.recode.ICmdRecordPojo;
import com.module.screencmd.BeanUtil;
import com.module.screencmd.parser.CtrlRecordParseChain;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

@Component
public class ScreenCtrlRecordParser implements ICmdRecordParser {
    private CtrlRecordParseChain ctrlRecordChain = BeanUtil.getBean(CtrlRecordParseChain.class);

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
