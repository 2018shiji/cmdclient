package com.cmdclient.core.recode;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.exec.util.DebugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * 使用字符流实时逐行读取子进程的输出流
 */
public class RealTimeReaderPumper implements Runnable {
    private final BufferedReader is;
    private final PrintStream os;
    private boolean finished;
    private final boolean closeWhenExhausted;
    private final ICmdRecordParser cmdRecordParser;

    public RealTimeReaderPumper(final BufferedReader is, final PrintStream os,
                        final boolean closeWhenExhausted, final ICmdRecordParser cmdRecordParser) {
        this.is = is;
        this.os = os;
        this.closeWhenExhausted = closeWhenExhausted;
        this.cmdRecordParser = cmdRecordParser;
    }

    public RealTimeReaderPumper(final BufferedReader is, final PrintStream os, final ICmdRecordParser cmdRecordParser) {
        this(is, os, false, cmdRecordParser);
    }

    public void run() {
        synchronized (this) {
            // Just in case this object is reused in the future
            finished = false;
        }
        try {
            cmdRecordParser.parseCmdRecord(is, os);
        } catch (final Exception e) {
            e.printStackTrace();
            // nothing to do - happens quite often with watchdog
        } finally {
            if (closeWhenExhausted) {
                try {
                    os.close();
                } catch (final Exception e) {
                    final String msg = "Got exception while closing exhausted output stream";
                    DebugUtils.handleException(msg ,e);
                }
            }
            synchronized (this) {
                finished = true;
                notifyAll();
            }
        }
    }
}
