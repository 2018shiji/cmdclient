package com.module.cmd.core.cmdpumpern;

import java.io.BufferedReader;
import java.io.PrintStream;

public interface ICmdPumpStreamHandlerN <T> {
    T parseCmdRecord(BufferedReader bufReader, PrintStream outputStream) throws Exception;
}
