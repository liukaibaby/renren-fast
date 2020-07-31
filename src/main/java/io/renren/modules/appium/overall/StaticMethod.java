package io.renren.modules.appium.overall;

import io.appium.java_client.android.AndroidDriver;
import io.renren.modules.appium.entity.TPfAppiumEntity;
import io.renren.modules.appium.service.TPfAppiumService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author : liukai
 * @date : 2020-07-02 15:10
 * 全局静态方法
 **/

//@Component
public class StaticMethod implements ApplicationRunner {

    @Autowired
    private TPfAppiumService tPfAppiumService;

    /**
     * 执行adb命令
     *
     * @param s 要执行的命令  参数
     */
    public static Process excuteShell(String s) {
        System.out.println(s);
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


    //    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==========启动成功==========");
        getArrDevice(tPfAppiumService);
        getConnectUdidInstall();


    }

    /**
     * 项目启动成功之后执行该方法 PostConstruct
     * 用来获取当前连接设备详情 并插入到数据库
     * 设置adb.exe存放路径，如果设置了环境变量，直接输入adb即可
     * 执行adb device操作，查看pc当前连接手机或模拟器设备列表
     * 注意：一定要先配置好sdk环境变量，否则无法直接执行adb命令
     */
    public static void getArrDevice(TPfAppiumService tPfAppiumService) throws Exception {
        System.out.println("==========获取当前连接设备信息==========");
        try {
            Process process = excuteShell("adb devices -l");
            if (process != null) {
                String line = null;

                ArrayList<String> list = new ArrayList<>();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                while ((line = reader.readLine()) != null) {

                    if (line.length() > 1) {

                        list.add(line);
                    }
                }
                if (!list.contains("* daemon started successfully *")) {
                    if (list != null && list.size() > 1) {
                        for (int i = 1; i < list.size(); i++) {
                            TPfAppiumEntity appiumEntity = new TPfAppiumEntity();
                            String[] str = list.get(i).split(" ");
                            List list1 = new ArrayList();
                            for (int j = 0; j < str.length; j++) {
                                if (!str[j].equals("")) {
                                    list1.add(str[j]);
                                }
                            }
                            if (list1.get(1).equals("device")) {
                                System.out.println("获取设备序列号:" + list1.get(0));
                                appiumEntity.setNumber(String.valueOf(list1.get(0)));
                                System.out.println("获取设备状态:" + list1.get(1));
                                appiumEntity.setEditionStatus(String.valueOf(list1.get(1)));
                                System.out.println("获取设备产品:" + list1.get(2));
                                appiumEntity.setEmentName(String.valueOf(list1.get(2)));
                                System.out.println("获取设备型号:" + list1.get(3));
                                appiumEntity.setEmentModel(String.valueOf(list1.get(3)));
                                System.out.println("获取设备名称:" + list1.get(4));
                                appiumEntity.setName(String.valueOf(list1.get(4)));
                                System.out.println("获取设备运输号:" + list1.get(6));
                                appiumEntity.setTransportId(String.valueOf(list1.get(6)));
                                String shell = " adb -s " + list1.get(0) + " shell cat /system/build.prop /| grep 'ro.build.version.release'";
                                process = StaticMethod.excuteShell(shell);
                                if (process != null) {

                                    String device_version = null;

                                    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                                    while ((line = reader.readLine()) != null) {

                                        if (line.indexOf("ro.build.version.release") != -1) {

                                            device_version = line.split("=")[1];
                                            System.out.println("获取设备版本号:" + device_version);
                                        }
                                    }

                                    appiumEntity.setEditionNumber(device_version == null ? "" : device_version);
                                }
                                Map hashMap = new HashMap();

                                hashMap.put("number", appiumEntity.getNumber());

                                // 检查改设备是否存在

                                List<TPfAppiumEntity> tPfAppiumEntity1 = (List<TPfAppiumEntity>) tPfAppiumService.listByMap(hashMap);


                                // 设备存在返回ID 不存在返回null

                                appiumEntity.setId(tPfAppiumEntity1.size() > 0 ? tPfAppiumEntity1.get(0).getId() : null);

                                // 存在ID 说明已经存在只修改


                                tPfAppiumService.saveOrUpdate(appiumEntity);

                                // 先删除之前之前设备号

                                // 将设备号存入到UDID

                                StaticConstant.UDID.add(appiumEntity.getNumber());

                            } else if (list1.get(1).equals("offline")) {
                                System.out.println("该设备当前处于离线状态");
                            } else {
                                System.out.println("该设备当前没有授权");
                            }
                        }
                    } else {
                        System.out.println("当前设备列表没有连接的设备，请检查！");
                    }
                    System.out.println("================执行结束=============");
                }
            } else {

                System.out.println("当前执行adb命令异常，请检查adb环境！");
            }
        } catch (Exception e) {

            System.err.println("执行异常:" + e);
        }
    }

    /**
     * 获取当前连接的手机UDID 进行安装
     */
    public static void getConnectUdidInstall() {
        System.out.println("============设备开始安装apk============");
        String apkUrl = StaticConstant.apkUrl; // Apk 绝对路径根据自己本地的apk下载路径为准
        String apkName = StaticConstant.apkName; // apk 包名
        try {
            Process process = excuteShell("adb devices "); // 查询当前连接的手机 adb  devices
            if (process != null) {
                ArrayList<String> list = bufferedReader(process);
                if (list != null && list.size() > 1) {
                    for (int i = 1; i < list.size(); i++) {
                        String[] str = list.get(i).split("\t");
                        if (str != null && str[1].equals("device")) {
                            System.out.println("设备:" + str[0]);
                            // 安装之前先查询当前设备是否存在
                            Process pro = excuteShell("adb -s " + str[0] + " shell pm list packages "); // 查询当前连接的手机 adb  devices
                            ArrayList<String> apkArr = bufferedReader(pro);
                            boolean check = apkArr.contains("package:" + apkName);
                            if (check) {
                                System.out.println("设备:" + str[0] + " apk已经存在无需重复安装");
                            } else {
                                // 插件不存在 执行 apk 安装
                                String shell = "adb -s " + str[0] + " install " + apkUrl;
                                Process process1 = excuteShell(shell);
                                if (process1 != null) {
                                    System.out.println("设备:" + str[0] + " Apk安装成功");
                                } else {
                                    System.out.println("设备:" + str[0] + " Apk安装失败 请检查设备连接");
                                }
                            }
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
     * 将cmd 返回的信息 转换成String 并用arraylist接收
     * BufferedReader
     */
    public static ArrayList<String> bufferedReader(Process process) {
        String line = null;
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        try {
            while ((line = reader.readLine()) != null) {
                if (line.length() > 1) {
                    list.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println("执行命令: 出错啦！" + e);
        }
        return list;

    }


    /**
     * 单设备连接
     *
     * @param input
     * @return
     */
    public static TPfAppiumEntity handle(TPfAppiumEntity input) {

        AndroidDriver androidDriver = null;
        // 获取当前进程名称

        String threadName = Thread.currentThread().getName();

        // 选择执行服务器

        String url = StaticConstant.URL;

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

        desiredCapabilities.setCapability("noSign", StaticConstant.RESETKEYBOARD);

        // 支持中文输入

        desiredCapabilities.setCapability("noReset", StaticConstant.UNICODEKEYBOARD);

        // 每次启动时覆盖session，否则第二次后运行会报错不能新建session

        desiredCapabilities.setCapability("sessionOverride", true);

        //指定想要测试应用的包名

        desiredCapabilities.setCapability("appPackage", StaticConstant.APPPACKAGE);

        //指定想要测试应用的入口activity

        desiredCapabilities.setCapability("appActivity", StaticConstant.APPACTIVITY);

        try {
            //虚拟机默认地址
            androidDriver = new AndroidDriver(new URL(url), desiredCapabilities);

            // 线程 等待10秒 待抖音完全加载完成之后在执行下一步操作

            androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//隐式等待 10 秒

            // 执行点赞/评论 功能

            appiumClik(androidDriver, input);

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
     * 点 赞
     */
    public static boolean appiumClik(AndroidDriver driver, TPfAppiumEntity tPfAppiumEntity) {
        try {
            // ID  com.ss.android.ugc.aweme:id/aev 个人信息保护指引 用户同意协议
//            driver.findElement(By.id("com.ss.android.ugc.aweme:id/aev")).click();
//            Thread.sleep(2000);
            //   坐标滑动
            driver.swipe(490, 1105, 479, 311, 800);
            Thread.sleep(2000);
            // com.ss.android.ugc.aweme:id/aqb
            driver.findElement(By.id("c_9")).click();

            Thread.sleep(2000);
            // com.ss.android.ugc.aweme:id/ai5 搜索框
            WebElement ai5 = driver.findElement(By.id("com.ss.android.ugc.aweme:id/ai5"));
            ai5.sendKeys(tPfAppiumEntity.getUser());
            // com.ss.android.ugc.aweme:id/cyj
            Thread.sleep(2000);
            WebElement cyj = driver.findElementByLinkText(tPfAppiumEntity.getUser());
            cyj.click();
            Thread.sleep(2000);
            // com.ss.android.ugc.aweme:id/aqb 点赞按钮
            WebElement aqb = driver.findElement(By.id("com.ss.android.ugc.aweme:id/aqb"));
            aqb.click();
            Thread.sleep(2000);
            // 判断 是否有评论 有评论 添加评论
            if (tPfAppiumEntity.getComments() != null && tPfAppiumEntity.getComments() != "") {
                System.out.println("存在评论");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (driver != null) {
                // 关闭执行程序
                driver.quit();
            }
        }
        return false;
    }
}
