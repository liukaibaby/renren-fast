package io.renren.modules.appium.overall;


import java.util.ArrayList;


/**
 * @author : liukai
 * @date : 2020-06-23 10:34
 **/
public class StaticConstant {

    public static ArrayList<String> UDID = new ArrayList<>(); // 用来接收设备号

    public static final Integer WORKER = 10; // 固定使用5个Worker

    public static final String PLATFORMNAME = "Android"; // 系统

    public static final Integer NEWCOMMANDTIMEOUT = 60; //没有新命令时的超时时间设置

    public static final String platformVersion = "5.1.1"; // android 版本号

    public static final String APPPACKAGE = "com.ss.android.ugc.aweme"; //App安装后的包名,注意与原来的CalcTest.apk不一样

    public static final String APPACTIVITY = "com.ss.android.ugc.aweme.splash.SplashActivity"; //app测试人员常常要获取activity，进行相关测试,后续会讲到

    public static final String UNICODEKEYBOARD = "True"; //支持中文输入

    public static final String RESETKEYBOARD = "True";  //支持中文输入

    public static final String NOSIGN = "True";  // 跳过重复安装

    public static final String NORESET = "True";  //跳过检查和对应用进行 debug 签名的步骤

    public static final String URL = "http://127.0.0.1:4723/wd/hub";// 执行服务器

    public static final String apkName = "com.ss.android.ugc.aweme"; // apk 包名

    public static final String apkUrl = "D:\\Downloads\\aweme_aweGW_v11.3.0_b5b6cb6.apk"; // Apk 绝对路径根据自己本地的apk下载路径为准


}
