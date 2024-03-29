package com.module.cmd.ping.executor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class IPProvider {
    public static final Map<String, String> SERVER_MAP = new HashMap();
    public static final Map<String, String> RFID_MAP = new HashMap();
    public static final Map<String, String> CAMERA_MAP = new HashMap();
    public static final Map<String, String> FIELD_BRIDGE_MAP = new HashMap();

    public static final List<String> MAIN_SERVER_ADDRESSES = Arrays.asList(
            "10.28.56.10", "10.28.56.12", "10.28.56.13", "10.28.32.246", "10.28.32.71",
            "10.28.32.47", "10.28.32.137", "10.28.32.163", "10.28.32.223", "10.28.32.72",
            "10.28.32.119","10.28.32.233"
    );

    public static final List<String> RFID_ADDRESSES = Arrays.asList(
            "10.28.56.121", "10.28.56.122", "10.28.56.123", "10.28.56.124", "10.28.56.125",
            "10.28.56.126", "10.28.56.127", "10.28.56.128", "10.28.56.129", "10.28.56.130",
            "10.28.56.131", "10.28.56.132", "10.28.56.133", "10.28.56.134", "10.28.56.135",
            "10.28.56.136", "10.28.56.137", "10.28.56.138", "10.28.56.139", "10.28.56.140",
            "10.28.56.141", "10.28.56.142", "10.28.56.143", "10.28.56.144", "10.28.56.145",
            "10.28.56.146", "10.28.56.147", "10.28.56.148", "10.28.56.149", "10.28.56.150",
            "10.28.56.151", "10.28.56.152"
    );

    public static final List<String> CAMERA_ADDRESSES = Arrays.asList(
            "10.28.56.21", "10.28.56.22", "10.28.56.23", "10.28.56.24", "10.28.56.25",
            "10.28.56.26", "10.28.56.27", "10.28.56.28", "10.28.56.29", "10.28.56.30",
            "10.28.56.31", "10.28.56.32", "10.28.56.33", "10.28.56.34", "10.28.56.35",
            "10.28.56.36", "10.28.56.37", "10.28.56.38", "10.28.56.39", "10.28.56.40",
            "10.28.56.41", "10.28.56.42", "10.28.56.43", "10.28.56.44", "10.28.56.45",
            "10.28.56.46", "10.28.56.47", "10.28.56.48", "10.28.56.49", "10.28.56.50",
            "10.28.56.51", "10.28.56.52"
    );

    public static final List<String> FIELD_BRIDGE_ADDRESSES = Arrays.asList(
            "10.28.56.61", "10.28.56.62", "10.28.56.63", "10.28.56.64", "10.28.56.65",
            "10.28.56.66", "10.28.56.67", "10.28.56.68", "10.28.56.69", "10.28.56.70",
            "10.28.56.71", "10.28.56.72", "10.28.56.73", "10.28.56.74", "10.28.56.75",
            "10.28.56.76", "10.28.56.77", "10.28.56.78", "10.28.56.79", "10.28.56.80",
            "10.28.56.81", "10.28.56.82", "10.28.56.83", "10.28.56.84", "10.28.56.85",
            "10.28.56.86", "10.28.56.87", "10.28.56.88", "10.28.56.89", "10.28.56.90",
            "10.28.56.91", "10.28.56.92", "10.28.56.93", "10.28.56.94", "10.28.56.95",
            "10.28.56.96", "10.28.56.97", "10.28.56.98", "10.28.56.99", "10.28.56.100",
            "10.28.56.101", "10.28.56.102", "10.28.56.103", "10.28.56.104", "10.28.56.105",
            "10.28.56.106", "10.28.56.107", "10.28.56.108", "10.28.56.109", "10.28.56.110",
            "10.28.56.111", "10.28.56.112", "10.28.56.113", "10.28.56.114"
    );

    public static void initIPProvider(){
        SERVER_MAP.put("10.28.56.10", "图像处理服务器");
        SERVER_MAP.put("10.28.56.12", "CORS1");
        SERVER_MAP.put("10.28.56.13", "CORS2");
        SERVER_MAP.put("10.28.32.246", "Tomcat服务器");
        SERVER_MAP.put("10.28.32.71", "RFID数据处理服务器");
        SERVER_MAP.put("10.28.32.47", "车载终端管理服务器");
        SERVER_MAP.put("10.28.32.137", "Cors站管理服务器");
        SERVER_MAP.put("10.28.32.163", "图层管理服务器");
        SERVER_MAP.put("10.28.32.223", "应用备份服务器");
        SERVER_MAP.put("10.28.32.72", "数据库服务器-72");
        SERVER_MAP.put("10.28.32.119", "数据库服务器-119");
        SERVER_MAP.put("10.28.32.233", "数据库服务器-233");

        for (int i = 1; i <= RFID_ADDRESSES.size(); i++) {
            RFID_MAP.put( RFID_ADDRESSES.get(i-1),"T" + i);
        }
        for (int i = 1; i <= CAMERA_ADDRESSES.size(); i++) {
            CAMERA_MAP.put(CAMERA_ADDRESSES.get(i-1),"T" + i);
        }
        for (int i = 1; i <= FIELD_BRIDGE_ADDRESSES.size(); i++){
            FIELD_BRIDGE_MAP.put(FIELD_BRIDGE_ADDRESSES.get(i-1), "3" + (Math.ceil((i+1)/2) < 10 ? "0" + (int)Math.ceil((i+1)/2) : (int)Math.ceil((i+1)/2)));
        }
    }

}
