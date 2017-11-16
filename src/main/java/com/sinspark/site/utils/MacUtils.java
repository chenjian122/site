package com.sinspark.site.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * MAC地址工具
 * Created by sinspark on 2017/11/16.
 */
public class MacUtils {

    private static Logger looger = LoggerFactory.getLogger(MacUtils.class);

    /**
     * 获取当前操作系统名称.
     * @return 操作系统名称 如:windows,Linux,Unix等.
     */
    public static String getOSName(){
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * 获取widnows网卡的mac地址.
     *
     * @return mac地址
     */
    public static String getWindowsMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ipconfig /all");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split("-").length == 6){
                    index = line.indexOf(":");
                    if (index != -1) {
                        mac = line.substring(index + 1).trim();
                    }
                    break;
                }
                index = line.toLowerCase().indexOf("物理地址");
                if (index != -1) {
                    index = line.indexOf(":");
                    if (index != -1) {
                        mac = line.substring(index + 1).trim();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            looger.error(e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                looger.error(e1.getMessage());
            }
            bufferedReader = null;
            process = null;
        }
        return mac;
    }

    /**
     * 获取Unix网卡的mac地址.
     * @return mac地址
     */
    public static String getUnixMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            //Unix下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
            process = Runtime.getRuntime().exec("ifconfig eth0");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                //寻找标示字符串[hwaddr]
                index = line.toLowerCase().indexOf("hwaddr");
                if (index != -1) {
                    //取出mac地址并去除2边空格
                    mac = line.substring(index + "hwaddr".length() + 1).trim();
                    break;
                }
            }
        } catch (IOException e) {
            looger.error(e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                looger.error(e1.getMessage());
            }
            bufferedReader = null;
            process = null;
        }
        return mac;
    }

    /**
     * 获取Linux网卡的mac地址.
     * @return mac地址
     */
    public static String getLinuxMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            //linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
            process = Runtime.getRuntime().exec("ifconfig eth0");
            bufferedReader = new BufferedReader(new InputStreamReader( process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("硬件地址");
                if (index != -1) {
                     //取出mac地址并去除2边空格
                    mac = line.substring(index + 4).trim();
                    break;
                }
            }
        } catch (IOException e) {
            looger.error(e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                looger.error(e1.getMessage());
            }
            bufferedReader = null;
            process = null;
        }
        // 取不到，试下Unix取发
        if (mac == null){
            return getUnixMACAddress();
        }
        return mac;
    }

    /**
     * 获取Mac地址
     * @return
     */
    public static String getMac(){
        String os = getOSName();
        String mac;
        if (os.startsWith("windows")) {
            mac = getWindowsMACAddress();
        } else if (os.startsWith("linux")) {
            mac = getLinuxMACAddress();
        } else {
            mac = getUnixMACAddress();
        }
        return mac == null ? "" : mac;
    }


    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getOSName());
        System.out.println(getMac());
    }

}
