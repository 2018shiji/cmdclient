package com.module.cmd.core.cmdpumper;

import org.apache.commons.exec.PumpStreamHandler;

import java.io.*;

public class RealTimePumpStreamHandler extends PumpStreamHandler {
    private ICmdPumpStreamHandler cmdRecordParser;

    public RealTimePumpStreamHandler(ICmdPumpStreamHandler cmdRecordParser){
        this.cmdRecordParser = cmdRecordParser;
    }

    @Override
    protected Thread createPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
        Thread result = null;
        try{
            result = new Thread(new RealTimeReaderPumper(
                    new BufferedReader(new InputStreamReader(is, "gbk")), new PrintStream(os), closeWhenExhausted, cmdRecordParser));
        } catch (Exception e){e.printStackTrace();}
        result.setDaemon(true);
        return result;
    }


}
