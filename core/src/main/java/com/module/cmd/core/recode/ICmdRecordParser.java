package com.module.cmd.core.recode;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

@Component
public interface ICmdRecordParser {
    void parseCmdRecord(BufferedReader bufReader, PrintStream printStream) throws Exception;
    List<? extends ICmdRecordPojo> getCmdParsePojoList();
}
