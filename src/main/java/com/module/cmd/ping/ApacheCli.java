package com.module.cmd.ping;

import com.module.cmd.ping.response.PingResponse;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.util.DebugUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@Component
public class ApacheCli {

    private static Map<String, PingResponse> pingResponses = new LinkedHashMap<String, PingResponse>();

    public static Map<String, PingResponse> getPingResponses(){
        return pingResponses;
//        Map<String, PingResponse> pingResponseMap = new LinkedHashMap<>();
//        pingResponseMap.put("10.28.56.121", new PingResponse("10.28.56.121", true, "test message RFID", "testTimeStamp"));
//        pingResponseMap.put("10.28.56.122", new PingResponse("10.28.56.122", false, "test message 2", "testTimeStamp2"));
//        pingResponseMap.put("10.28.56.21", new PingResponse("10.28.56.21", false, "test message camera",  "testTimeStamp3"));
//        pingResponseMap.put("10.28.56.61", new PingResponse("10.28.56.61", true, "field bridge 1", "test bridge1"));
//        pingResponseMap.put("10.28.56.62", new PingResponse("10.28.56.62", true, "field bridge 2", "test bridge2"));
//        pingResponseMap.put("10.28.56.63", new PingResponse("10.28.56.63", false, "field bridge 3", "test bridge3"));
//        pingResponseMap.put("10.28.56.10", new PingResponse("10.28.56.10", true, "server...", "server..."));
//        pingResponseMap.put("10.28.56.12", new PingResponse("10.28.56.12", true, "server....", "server...."));
//        pingResponseMap.put("10.28.56.13", new PingResponse("10.28.56.13", false, "server.....", "server....."));
//        return pingResponseMap;
    }

    public static void doPingLogic(String remoteIP){
        final CommandLine cmdLine = CommandLine.parse("ping " + remoteIP);
        try{
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(null);

            executor.setStreamHandler(new MyHandler(remoteIP));
            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            executor.execute(cmdLine, resultHandler);


        } catch (Exception e){e.printStackTrace();}
    }

    public static class MyHandler extends PumpStreamHandler{
        private final String remoteIP;
        public MyHandler(String remoteIP){
            this.remoteIP = remoteIP;
        }

        @Override
        protected Thread createPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
            Thread result = null;
            try{
                result = new Thread(new ReaderPumper(
                        new BufferedReader(new InputStreamReader(is, "gbk")), new PrintStream(os), closeWhenExhausted, remoteIP));
            } catch (Exception e){e.printStackTrace();}
            result.setDaemon(true);
            return result;
        }
    }

    // 使用字符流逐行读取子进程的输出流
    public static class ReaderPumper implements Runnable {
        private final BufferedReader is;
        private final PrintStream os;
        private boolean finished;
        private final boolean closeWhenExhausted;
        private final String remoteIP;

        public ReaderPumper(final BufferedReader is, final PrintStream os,
                            final boolean closeWhenExhausted, final String remoteIP) {
            this.is = is;
            this.os = os;
            this.closeWhenExhausted = closeWhenExhausted;
            this.remoteIP = remoteIP;
        }

        public ReaderPumper(final BufferedReader is, final PrintStream os,
                            final boolean closeWhenExhausted, final int size, final String remoteIP) {
            this.is = is;
            this.os = os;
            this.closeWhenExhausted = closeWhenExhausted;
            this.remoteIP = remoteIP;
        }

        public ReaderPumper(final BufferedReader is, final PrintStream os, final String remoteIP) {
            this(is, os, false, remoteIP);
        }

        public void run() {
            synchronized (this) {
                // Just in case this object is reused in the future
                finished = false;
            }

            String line = null;
            try {
                while ((line = is.readLine()) != null) {
//                    os.println(remoteIP + " " + line + " " + System.currentTimeMillis());
                    if(line.contains("正在 Ping") || line.equals(""))continue;
                    PingResponse pingResponse = new PingResponse();
                    pingResponse.setIpAddress(remoteIP);
                    String timeStamp = new SimpleDateFormat("HH:mm:ss:SSS").format(System.currentTimeMillis());
                    pingResponse.setTimeStamp(timeStamp);
                    pingResponse.setMessage(line);
                    pingResponse.setOnline(true);
                    if(line.contains("请求超时") || line.contains("100% 丢失")){ pingResponse.setOnline(false); }
                    pingResponses.put(remoteIP, pingResponse);
                }
            } catch (final Exception e) {
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

        public synchronized boolean isFinished() {
            return finished;
        }

        public synchronized void waitFor() throws InterruptedException {
            while (!isFinished()) {
                wait();
            }
        }
    }

    public static void main(String[] args){
        new Thread(() -> {
            String cmd = "10.28.56.121";
            doPingLogic(cmd);
            String cmd1 = "10.28.56.122";
            doPingLogic(cmd1);
            String cmd2 = "10.28.56.123";
            doPingLogic(cmd2);
            String cmd3 = "10.28.56.124";
            doPingLogic(cmd3);
            String cmd4 = "10.28.56.125";
            doPingLogic(cmd4);
            String cmd5 = "10.28.56.126";
            doPingLogic(cmd5);

            String cmd6 = "10.28.56.127";
            doPingLogic(cmd6);
            String cmd7 = "10.28.56.128";
            doPingLogic(cmd7);
            String cmd8 = "10.28.56.129";
            doPingLogic(cmd8);
            String cmd9 = "10.28.56.130";
            doPingLogic(cmd9);
            String cmd10 = "10.28.56.131";
            doPingLogic(cmd10);
            String cmd11 = "10.28.56.132";
            doPingLogic(cmd11);
            String cmd12 = "10.28.56.133";
            doPingLogic(cmd12);
            String cmd13 = "10.28.56.134";
            doPingLogic(cmd13);
            String cmd14 = "10.28.56.135";
            doPingLogic(cmd14);
            String cmd15 = "10.28.56.136";
            doPingLogic(cmd15);
            String cmd16 = "10.28.56.137";
            doPingLogic(cmd16);
            String cmd17 = "10.28.56.138";
            doPingLogic(cmd17);
        }).start();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        try{
            countDownLatch.await();
        }catch (InterruptedException e){e.printStackTrace();}

    }


}
