package com.module.screencmd;

import com.module.screencmd.cmd.ScreenAppCommand;
import com.module.screencmd.cmd.ScreenCtrlCommand;
import com.module.screencmd.pojo.AppCmdRecordPojo;
import com.module.screencmd.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class Navigator {
    Logger logger = LoggerFactory.getLogger(Navigator.class);

    @ResponseBody
    @RequestMapping("startUpAppScript")
    public String startUpAppScript(){
        System.out.println("开始运行应用程序启动脚本");
        BeanUtil.getBean(ScreenAppCommand.class).doScreenAppCommand(null);
        getStructuredCmd();
        return "OK";
    }

    @ResponseBody
    @RequestMapping("startUpCtrlScript")
    public boolean startUpCtrlScript(){
        System.out.println("开始运行屏控程序启动脚本");
        BeanUtil.getBean(ScreenCtrlCommand.class).doScreenControlCmd(null);
        return true;
    }

    @ResponseBody
    @RequestMapping("getAppCommandResult")
    public List<AppCmdRecordPojo> getAppCommandResult(){
        return BeanUtil.getBean(ScreenAppCommand.class).getAppCmdRecordPojoList();
    }


    public List<AppCmdRecordPojo> getStructuredCmd(){

        BeanUtil.getBean(ScreenAppCommand.class).doScreenAppCommand(null);
        List<AppCmdRecordPojo> appCmdRecordPojoList = BeanUtil.getBean(ScreenAppCommand.class).getAppCmdRecordPojoList();
        return appCmdRecordPojoList;
    }



}
