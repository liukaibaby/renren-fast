package io.renren.modules.appium.throwable;

import io.appium.java_client.android.AndroidDriver;
import io.renren.modules.appium.entity.TPfAppiumEntity;
import io.renren.modules.appium.overall.StaticConstant;
import io.renren.modules.appium.overall.StaticMethod;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * @author : liukai
 * @date : 2020-07-06 09:51
 **/
public class PlusWorker extends Worker {

    @Override
    public TPfAppiumEntity handle(TPfAppiumEntity input) {

        AndroidDriver androidDriver = null;
        // 获取当前进程名称

        String threadName = Thread.currentThread().getName();

        // 选择执行服务器

        String url = choiceServer(threadName);

        // 添加配置，创建DesiredCapabilities对象

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        //指定测试设备的UDID

        desiredCapabilities.setCapability("udid", input.getNumber());

        // 指定测试设备的名称

        desiredCapabilities.setCapability("deviceName", input.getNumber());

        // 添加操作系统配置

        desiredCapabilities.setCapability("platformName", StaticConstant.PLATFORMNAME);

        // 添加操作系统版本设置

        desiredCapabilities.setCapability("platformVersion", StaticConstant.platformVersion);

        // 没有新命令时的超时时间设置

        desiredCapabilities.setCapability("newCommandTimeout", StaticConstant.NEWCOMMANDTIMEOUT);

        // 跳过重复安装

        desiredCapabilities.setCapability("noSign", StaticConstant.NOSIGN);

        // 跳过检查和对应用进行 debug 签名的步骤

        desiredCapabilities.setCapability("noReset", StaticConstant.NORESET);

        // 支持中文输入

        //    desiredCapabilities.setCapability("noSign", StaticConstant.RESETKEYBOARD);

        // 支持中文输入

        //   desiredCapabilities.setCapability("noReset", StaticConstant.UNICODEKEYBOARD);

        //指定想要测试应用的包名

        desiredCapabilities.setCapability("appPackage", "com.ss.android.ugc.aweme");

        //指定想要测试应用的入口activity

        desiredCapabilities.setCapability("appActivity", "com.ss.android.ugc.aweme.splash.SplashActivity");

        try {
            //虚拟机默认地址
            androidDriver = new AndroidDriver(new URL(url), desiredCapabilities);

            // 线程 等待10秒 待抖音完全加载完成之后在执行下一步操作

            Thread.sleep(10000);

            // 执行点赞功能

            StaticMethod.appiumClik(androidDriver, input);

            // 输出 信息

            System.out.println("执行成功设备号: " + input.getNumber());

        } catch (Exception e) {
            System.out.println("执行失败设备号: " + input.getNumber());
        } finally {
            StaticConstant.UDID.add(input.getNumber());
            if (androidDriver != null) {
                androidDriver.quit();
            }
        }
        return input;
    }

    /**
     * 执行可选Appium 服务器
     *
     * @param threadName
     * @return
     */
    public static String choiceServer(String threadName) {
        String url = null;
        try {
            switch (Integer.parseInt(threadName)) {
                case 0: //可选
                    url = "http://127.0.0.1:4723/wd/hub";
                    break;
                case 1: //可选
                    url = "http://127.0.0.1:4725/wd/hub";
                    break;
                default:
                    url = "http://127.0.0.1:4723/wd/hub";
            }
        } catch (Exception e) {
            url = "http://127.0.0.1:4723/wd/hub";
        }

        return url;
    }

}
