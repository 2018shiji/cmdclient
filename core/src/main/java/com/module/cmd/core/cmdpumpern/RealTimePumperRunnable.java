package com.module.cmd.core.cmdpumpern;

import lombok.SneakyThrows;

import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class RealTimePumperRunnable<T> implements Runnable {
    private final BufferedReader isr;
    private final PrintStream osp;
    private final ReentrantLock lock = new ReentrantLock();
    private final ICmdPumpStreamHandlerN<T> cmdPumpHandler;
    private BlockingQueue<T> pumpProductList;

    @SneakyThrows(UnsupportedEncodingException.class)
    public RealTimePumperRunnable(final InputStream is, final OutputStream os,
                                  final ICmdPumpStreamHandlerN<T> cmdPumpHandler, BlockingQueue<T> pumpProductList){
        this.isr = new BufferedReader(new InputStreamReader(is, "gbk"));
        this.osp = new PrintStream(os);
        this.cmdPumpHandler = cmdPumpHandler;
        this.pumpProductList = pumpProductList;
    }

    @SneakyThrows(IOException.class)
    @Override
    public void run() {
        lock.lock();
        try {
            T pumpProduct = cmdPumpHandler.parseCmdRecord(isr, osp);
            pumpProductList.add(pumpProduct);
        } catch (Exception e){
            e.printStackTrace();// nothing to do - happens quite often with watchdog
        } finally {
            lock.unlock();
            isr.close();
            osp.close();
        }

    }

}
