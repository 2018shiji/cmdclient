package com.module.cmd.ping;

import com.module.cmd.ping.executorn.XxlJobHandler;
import com.module.cmd.ping.response.PingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
public class NavigatorN {
    @Autowired
    private XxlJobHandler xxlJobHandler;

    @RequestMapping("/getRFIDPingResponses")
    public String getRFIDPingResponses(){
        Map<String, PingResponse> pingResponses = xxlJobHandler.getRfidExecutor().getPingResponses();
        return null;
    }
}
