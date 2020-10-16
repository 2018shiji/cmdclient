package com.module.cmd.iperf;

public class IPerfUtil {
    /**
     * -f [k|m|K|M] 以KBits, MBits, KBytes, MBytes显示报告，默认以MBits位单位
     * -i sec 以秒为单位显示报告间隔，eg:iperf -c 222.35.11.23 -i 2
     * -l 缓冲区大小，默认是8KB，eg:iperf -c 222.35.11.23 -l 16
     * -m 显示tcp最大mtu值
     * -o 将报告和错误信息输出到文件 eg:iperf -c 222.35.11.23 -o c:\iperflog.txt
     * -p 指定服务器端使用的端口或客户端所连接的端口 eg:iperf -s -p 9999; iperf -c 222.35.11.23 -p 9999
     * -u 使用udp协议
     * -w 指定TCP窗口大小，默认是8KB
     * -B 绑定一个主机地址或接口（当主机有多个地址或接口时使用该参数）
     * -C 兼容旧版本（当server端和client端版本不一样时使用）
     * -M 设定TCP数据包的最大mtu值
     * -N 设定TCP不延时
     * -V 传输ipv6数据包 server专用参数
     * -D 以服务方式运行ipserf，eg:iperf -s -D
     * -R 停止iperf服务，eg:iperf -s -R
     * -d 同时进行双向传输测试
     * -n 指定传输的字节数，eg:iperf -c 222.35.11.23 -n 100000
     * -r 单独及逆行双向传输测试
     * -t 测试时间，默认10秒，eg:iperf -c 222.35.11.23 -t 5
     * -F 指定需要传输的文件
     * -T 指定TTL值
     * -b 发送的总流量大小（-b #K或 --b #M）
     * iperf -c 127.0.0.1:如果执行失败说明iPerf安装失败
     * 如果你测试的流量将要通过网络防火墙，那么要确保打开端口5001或指定iPerf使用
     * 已经开放的端口(如iperf -c host -p 80)。
     * 如果你的iPerf服务器处于NAT防火墙之后，那么你可能需要配置一个端口转发规则来进行连接。
     * 对于同时连接到Ethernet和Wi-Fi（3G、Wi-Fi）的多连接笔记本而言，将iPerf客户端绑定到一个适配器上是相当重要的。
     * 视频流媒体通过缓冲输入而能够容忍更多的延迟，而语音通讯则随着延迟增长性能下降明显。
     * 如果你正在测试的路径中包括Ethernet和802.11，那么要控制你的测试数据包长度，使它在一个Ethernet帧以内，以避免分片。
     * --d选项：告知iPerf服务器马上连接回iPerf客户端
     * --L选项：手动指定端口，以支持同时测试两个方向的传输
     * --r选项：告知iPerf服务器等到客户端测试完成之后再在相反的方向中重复之前的测试
     * --B选项：手动指定多点传送组IP地址来启动多个iPerf服务器，适用于多点传送应用
     */
    //TCP服务器执行
    public static final String serverCmd = "iperf -s -i 1 -w 1M";
    //TCP客户端执行
    public static final String clientCmd = "iperf -c host -i 1 -w 1M";
    //UDP服务器执行
    public static final String serverCmdUdp = "iperf -u -s";
    //UDP客户端执行
    public static final String clientCmdUdp = "iperf -u -c 192.168.21.128 -b 900M -i 1 -w 1M -t 60";

}
