package io.renren;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author : liukai
 * @date : 2020-07-07 16:05
 **/
public class AppiumJava {

    public static void main(String[] str) {
        getConnectUdidInstall();
    }

    /**
     * 获取当前连接的手机UDID 进行安装
     */
    public static void getConnectUdidInstall() {
        String adb_path = "adb";
        ArrayList<String> list = null;
        String line = "";
        String apkUrl = "D:\\Downloads\\youdaodict_android_youdaoweb.apk"; // Apk 绝对路径根据自己本地的apk下载路径为准
        try {
            list = new ArrayList<String>();
            Process process = excuteShell(adb_path + " devices "); // 查询当前连接的手机 adb  devices
            if (process != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    if (line.length() > 1) {
                        list.add(line);
                    }
                }
                System.out.println(list);
                if (list != null && list.size() > 1) {
                    for (int i = 1; i < list.size(); i++) {
                        String[] str = list.get(i).split("\t");
                        if (str != null && str[1].equals("device")) {
                            System.out.println("获取设备序列号:" + str[0]);
                            String shell = adb_path + " -s " + str[0] + " install " + apkUrl;
                            System.out.println(shell);  // 打印 adb 语句 看看是否拼写正确
                            excuteShell(shell);
                        }
                    }
                } else {
                    System.out.println("当前设备列表没有连接的设备，请检查！");
                }
            } else {
                System.out.println("当前执行adb命令异常，请检查adb环境！");
            }
        } catch (Exception e) {
            System.err.println("IOException" + e.getMessage());
        }
    }

    /**
     * 执行 adb
     */
    public static Process excuteShell(String s) {
        Process proc = null;
        Runtime runtime = Runtime.getRuntime();
        try {
            proc = runtime.exec(s);
        } catch (Exception e) {
            System.out.print("执行命令:" + s + "出错啦！");
            return null;
        }
        return proc;
    }
}
