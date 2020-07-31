//package io.renren;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.renren.modules.appium.overall.StaticConstant;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author : liukai
// * @date : 2020-07-10 11:48
// **/
//public class AppDemo {
//
//    public static void main(String[] str) {
//        handle();
//    }
//
//    /**
//     * 单设备连接
//     *
//     * @param
//     * @return
//     */
//    public static void handle() {
//
//        AndroidDriver androidDriver = null;
//        // 获取当前进程名称
//
//        String threadName = Thread.currentThread().getName();
//
//        // 选择执行服务器
//
//        String url = StaticConstant.URL;
//
//        // 添加配置，创建DesiredCapabilities对象
//
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//
//        //指定测试设备的UDID
//
//        desiredCapabilities.setCapability("udid", "127.0.0.1:62001");
//
//        // 指定测试设备的名称
//
//        desiredCapabilities.setCapability("deviceName", "127.0.0.1:62001");
//
//        // 添加操作系统配置
//
//        desiredCapabilities.setCapability("platformName", StaticConstant.PLATFORMNAME);
//
//        // 添加操作系统版本设置
//
//        desiredCapabilities.setCapability("platformVersion", StaticConstant.platformVersion);
//
//        // 没有新命令时的超时时间设置
//
//        desiredCapabilities.setCapability("newCommandTimeout", StaticConstant.NEWCOMMANDTIMEOUT);
//
//        // 跳过重复安装
//
//        desiredCapabilities.setCapability("noSign", StaticConstant.NOSIGN);
//
//        // 跳过检查和对应用进行 debug 签名的步骤
//
//        desiredCapabilities.setCapability("noReset", StaticConstant.NORESET);
//
//        // 支持中文输入
//
//        //    desiredCapabilities.setCapability("noSign", StaticConstant.RESETKEYBOARD);
//
//        // 支持中文输入
//
//        //   desiredCapabilities.setCapability("noReset", StaticConstant.UNICODEKEYBOARD);
//
//        //指定想要测试应用的包名
//
//        desiredCapabilities.setCapability("appPackage", StaticConstant.APPPACKAGE);
//
//        //指定想要测试应用的入口activity
//
//        desiredCapabilities.setCapability("appActivity", StaticConstant.APPACTIVITY);
//
//        try {
//            //虚拟机默认地址
//            androidDriver = new AndroidDriver(new URL(url), desiredCapabilities);
//
//            // 线程 等待10秒 待抖音完全加载完成之后在执行下一步操作
//
//            androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//隐式等待
//
//            // 执行点赞/评论 功能
//
//            appiumClik(androidDriver);
//
//            // 输出 信息
//
//            //   System.out.println("执行成功设备号: " + input.getNumber());
//
//        } catch (Exception e) {
//            //   System.out.println("执行失败设备号: " + input.getNumber());
//        } finally {
//            //   StaticConstant.UDID.add(input.getNumber());
//            if (androidDriver != null) {
//                androidDriver.quit();
//            }
//        }
//    }
//
//    /**
//     * 点 赞
//     */
//    public static boolean appiumClik(AndroidDriver driver) {
//        try {
//            // ID  com.ss.android.ugc.aweme:id/aev 个人信息保护指引 用户同意协议
//            try {
////                driver.findElement(By.id("com.ss.android.ugc.aweme:id/aev")).click();
////                Thread.sleep(2000);
//
//                //   坐标滑动
//
//                driver.swipe(490, 1105, 479, 311, 800);
//                Thread.sleep(5000);
//                driver.findElementById("b_9").click();
//
//                //  driver.findElement(By.id("com.ss.android.ugc.aweme:id/b_9")).click();
//
//                // com.ss.android.ugc.aweme:id/b_8 搜索按钮ID com.ss.android.ugc.aweme:id/b_8
////                WebElement b_8 = driver.findElement(By.id(""));
////                WebElement b_8 = driver.findElement(By.xpath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.view.DmtViewPager.MyAccessibilityDelegate/android.widget.TabHost/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]"));
////                b_8.click();
//                Thread.sleep(2000);
//                // com.ss.android.ugc.aweme:id/ai5 搜索框
//                WebElement ai5 = driver.findElement(By.id("com.ss.android.ugc.aweme:id/ai5"));
//                //    ai5.sendKeys(tPfAppiumEntity.getUser());
//                // com.ss.android.ugc.aweme:id/cyj
//                Thread.sleep(2000);
////                WebElement cyj = driver.findElementByLinkText(tPfAppiumEntity.getUser());
////                cyj.click();
//                Thread.sleep(2000);
//                // com.ss.android.ugc.aweme:id/aqb 点赞按钮
//                WebElement aqb = driver.findElement(By.id("com.ss.android.ugc.aweme:id/aqb"));
//                aqb.click();
//                Thread.sleep(2000);
//            } catch (Exception e) {
//                System.out.println(e);
//            } finally {
//
//            }
//            // 判断 是否有评论 有评论 添加评论
////            if (tPfAppiumEntity.getComments() != null && tPfAppiumEntity.getComments() != "") {
////                System.out.println("存在评论");
////            }
//            return true;
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            if (driver != null) {
//                // 关闭执行程序
//                driver.quit();
//            }
//        }
//        return false;
//    }
//}
