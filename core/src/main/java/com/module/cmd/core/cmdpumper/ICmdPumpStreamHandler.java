package com.module.cmd.core.cmdpumper;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.PrintStream;

@Component
public interface ICmdPumpStreamHandler {
    void parseCmdRecord(BufferedReader bufReader, PrintStream printStream) throws Exception;
}
