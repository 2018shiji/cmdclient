package com.module.cmd.core.cmdpumpern;

import org.apache.commons.exec.PumpStreamHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

public class RealTimePumpSHandlerN<T> extends PumpStreamHandler {
    private ICmdPumpStreamHandlerN<T> cmdPumpHandler;
    private BlockingQueue<T> pumpProductList;

    public RealTimePumpSHandlerN(ICmdPumpStreamHandlerN<T> cmdPumpHandler, BlockingQueue<T> pumpProductList){
        this.cmdPumpHandler = cmdPumpHandler;
        this.pumpProductList = pumpProductList;
    }

    @Override
    protected Thread createPump(InputStream is, OutputStream os){
        RealTimePumperRunnable pumpRunnable = new RealTimePumperRunnable(is, os, cmdPumpHandler, pumpProductList);

        Thread pumpThread = new Thread(pumpRunnable);
        pumpThread.setDaemon(true);
        return pumpThread;
    }

}
