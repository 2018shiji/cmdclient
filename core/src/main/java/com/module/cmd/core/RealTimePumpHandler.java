package com.module.cmd.core;

import com.module.cmd.core.cmdpumpern.ICmdPumpStreamHandlerN;
import lombok.SneakyThrows;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RealTimePumpHandler<T> extends PumpStreamHandler implements ICmdPumpStreamHandlerN<T>, Runnable {
    private InputStream is;
    private OutputStream os;
    private BlockingQueue<T> pumpProductList;
    private final ReentrantLock lock = new ReentrantLock();

    public RealTimePumpHandler(BlockingQueue<T> queue){
        this.pumpProductList = queue;
    }

    @Override
    protected Thread createPump(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
        Thread pumpThread = new Thread(this);
        pumpThread.setDaemon(true);
        return pumpThread;
    }

    @Override
    @SneakyThrows
    public void run() {
        lock.lock();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gbk"));
            PrintStream writer = new PrintStream(os);
            T result = parseCmdRecord(reader, writer);
            pumpProductList.add(result);
        } finally {
            lock.unlock();
            is.close();
            os.close();
        }
    }

    public BlockingQueue<T> getThreadResults(){
        return pumpProductList;
    }

}
