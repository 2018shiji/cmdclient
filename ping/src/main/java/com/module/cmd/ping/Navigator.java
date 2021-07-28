package com.module.cmd.ping;

import com.alibaba.fastjson.JSON;
import com.module.cmd.ping.executor.IPProvider;
import com.module.cmd.ping.executor.PingScheduler;
import com.module.cmd.ping.response.FieldBridgeResp;
import com.module.cmd.ping.response.MainServerResp;
import com.module.cmd.ping.response.PingResponse;
import com.module.cmd.ping.response.RFIDCameraResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Navigator {
    Logger logger = LoggerFactory.getLogger(Navigator.class);

    @Autowired
    PingScheduler pingScheduler;

//    @PostConstruct
    public void beginPing(){ pingScheduler.schedulePingJob(null); }

    @ResponseBody
    @RequestMapping("getIpsStatusResp")
    public String getIpResponse(){
        logger.info("--------------->dispatch getIpsStatusResp method");
        List<PingResponse> responses = new ArrayList<>();
        Map<String, PingResponse> pingResponses = pingScheduler.getResponseMap();
        for(Map.Entry<String, PingResponse> item : pingResponses.entrySet()){
            if(item.getKey().equals("standardTimeStamp"))continue;
            responses.add(item.getValue());
        }
        String result = JSON.toJSONString(responses);
        System.out.println("GGGGGGGGGGGGGGGGGGGGGG" + result);
        logger.info("GGGGGGGGGGGGGGGGGGGGGG" + result);
        return result;
    }

    @ResponseBody
    @RequestMapping("getMainServerResp")
    public String getServerResp(){
        List<MainServerResp> serverRespList = new ArrayList<>();
        Map<String, PingResponse> pingResponse = pingScheduler.getResponseMap();

        for(Map.Entry<String, PingResponse> item : pingResponse.entrySet()){
            fulfillMainServerList(item, serverRespList);
        }

        System.out.println("GGGGGGGGGGGGGGGGGGGGGG" + JSON.toJSONString(serverRespList));
        return JSON.toJSONString(serverRespList);
    }

    @ResponseBody
    @RequestMapping("getRFIDAndCamera")
    public String getRFIDCameraResp(){
        List<RFIDCameraResp> rfidCameraRespList = new ArrayList<>();
        Map<String, PingResponse> pingResponse = pingScheduler.getResponseMap();

        for(Map.Entry<String, PingResponse> item : pingResponse.entrySet()){
            fulfillRFIDCameraList(item, rfidCameraRespList);
        }

        System.out.println("GGGGGGGGGGGGGGGGGGGGGG" + JSON.toJSONString(rfidCameraRespList));
        return JSON.toJSONString(rfidCameraRespList);
    }

    @ResponseBody
    @RequestMapping("getFieldBridge")
    public String getFieldBridgeResp(){
        List<FieldBridgeResp> fieldBridgeResps = new ArrayList<>();
        Map<String, PingResponse> pingResponse = pingScheduler.getResponseMap();

        for(Map.Entry<String, PingResponse> item : pingResponse.entrySet()){
            fulfillBridgeList(item, fieldBridgeResps);
        }

        System.out.println("GGGGGGGGGGGGGGGGGGGGGG" + JSON.toJSONString(fieldBridgeResps));
        return JSON.toJSONString(fieldBridgeResps);
    }

    private void fulfillMainServerList(Map.Entry<String, PingResponse> item, List<MainServerResp> serverRespList){
        if(IPProvider.MAIN_SERVER_ADDRESSES.contains(item.getKey())){
            MainServerResp mainServerResp = new MainServerResp(IPProvider.SERVER_MAP.get(item.getKey()));
            PingResponse pingResponse = item.getValue();
            mainServerResp.setServerIP(pingResponse.getIpAddress());
            mainServerResp.setServerOnline(pingResponse.isOnline());
            mainServerResp.setServerTimeStamp(pingResponse.getTimeStamp());
            serverRespList.add(mainServerResp);
        }
    }

    private void fulfillRFIDCameraList(Map.Entry<String, PingResponse> item, List<RFIDCameraResp> rfidCameraRespList){
        if(IPProvider.RFID_ADDRESSES.contains(item.getKey())){
            RFIDCameraResp rfidCameraResp = new RFIDCameraResp(IPProvider.RFID_MAP.get(item.getKey()));
            if(!rfidCameraRespList.contains(rfidCameraResp))
                doFirstInitAndPutRFID(rfidCameraRespList, item.getValue(), rfidCameraResp);
            else
                doGetInitAndPutRFID(rfidCameraRespList, item.getValue(), rfidCameraResp);
        } else if(IPProvider.CAMERA_ADDRESSES.contains(item.getKey())){
            RFIDCameraResp rfidCameraResp = new RFIDCameraResp(IPProvider.CAMERA_MAP.get(item.getKey()));
            if (!rfidCameraRespList.contains(rfidCameraResp))
                doFirstInitAndPutCamera(rfidCameraRespList, item.getValue(), rfidCameraResp);
             else
                doGetInitAndPutCamera(rfidCameraRespList, item.getValue(), rfidCameraResp);
        }
    }

    private void doFirstInitAndPutRFID(List<RFIDCameraResp> rfidCameraRespList, PingResponse pingResponse, RFIDCameraResp rfidCameraResp){
        rfidCameraResp.setRFIDIP(pingResponse.getIpAddress());
        rfidCameraResp.setRFIDOnline(pingResponse.isOnline());
        rfidCameraResp.setRFIDTimeStamp(pingResponse.getTimeStamp());
        rfidCameraRespList.add(rfidCameraResp);
    }

    private void doGetInitAndPutRFID(List<RFIDCameraResp> rfidCameraRespList, PingResponse pingResponse, RFIDCameraResp rfidCameraResp){
        int index = rfidCameraRespList.indexOf(rfidCameraResp);
        rfidCameraRespList.get(index).setRFIDIP(pingResponse.getIpAddress());
        rfidCameraRespList.get(index).setRFIDOnline(pingResponse.isOnline());
        rfidCameraRespList.get(index).setRFIDTimeStamp(pingResponse.getTimeStamp());
    }

    private void doFirstInitAndPutCamera(List<RFIDCameraResp> rfidCameraRespList, PingResponse pingResponse, RFIDCameraResp rfidCameraResp){
        rfidCameraResp.setCameraIP(pingResponse.getIpAddress());
        rfidCameraResp.setCameraOnline(pingResponse.isOnline());
        rfidCameraResp.setCameraTimeStamp(pingResponse.getTimeStamp());
        rfidCameraRespList.add(rfidCameraResp);
    }

    private void doGetInitAndPutCamera(List<RFIDCameraResp> rfidCameraRespList, PingResponse pingResponse, RFIDCameraResp rfidCameraResp){
        int index = rfidCameraRespList.indexOf(rfidCameraResp);
        rfidCameraRespList.get(index).setCameraIP(pingResponse.getIpAddress());
        rfidCameraRespList.get(index).setCameraOnline(pingResponse.isOnline());
        rfidCameraRespList.get(index).setCameraTimeStamp(pingResponse.getTimeStamp());
    }

    private void fulfillBridgeList(Map.Entry<String, PingResponse> item, List<FieldBridgeResp> fieldBridgeRespList){
        if(IPProvider.FIELD_BRIDGE_ADDRESSES.contains(item.getKey())){
            FieldBridgeResp fieldBridgeResp = new FieldBridgeResp(IPProvider.FIELD_BRIDGE_MAP.get(item.getKey()));
            if(!fieldBridgeRespList.contains(fieldBridgeResp))
                doFirstInitAndPutBridge(fieldBridgeRespList, item.getValue(), fieldBridgeResp);
            else
                doGetInitAndPutBridge(fieldBridgeRespList, item.getValue(), fieldBridgeResp);
        }
    }

    private void doFirstInitAndPutBridge(List<FieldBridgeResp> fieldBridgeRespList, PingResponse pingResponse, FieldBridgeResp fieldBridgeResp){
        fieldBridgeResp.setFBridgeIP(pingResponse.getIpAddress());
        fieldBridgeResp.setFBridgeOnline(pingResponse.isOnline());
        fieldBridgeResp.setFBridgeTimeStamp(pingResponse.getTimeStamp());
        fieldBridgeRespList.add(fieldBridgeResp);
    }

    private void doGetInitAndPutBridge(List<FieldBridgeResp> fieldBridgeRespList, PingResponse pingResponse, FieldBridgeResp fieldBridgeResp){
        int index = fieldBridgeRespList.indexOf(fieldBridgeResp);
        fieldBridgeRespList.get(index).setSBridgeIP(pingResponse.getIpAddress());
        fieldBridgeRespList.get(index).setSBridgeOnline(pingResponse.isOnline());
        fieldBridgeRespList.get(index).setSBridgeTimeStamp(pingResponse.getTimeStamp());
    }
}
