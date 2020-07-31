package io.renren.modules.appium.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.common.utils.Query;
import io.renren.modules.appium.dao.TPfAppiumDao;
import io.renren.modules.appium.entity.TPfAppiumEntity;
import io.renren.modules.appium.overall.StaticMethod;
import io.renren.modules.appium.service.TPfAppiumService;
import org.springframework.stereotype.Service;

import java.util.Map;


import io.renren.common.utils.PageUtils;

import static io.renren.modules.appium.overall.StaticMethod.excuteShell;


@Service("tPfAppiumService")
public class TPfAppiumServiceImpl extends ServiceImpl<TPfAppiumDao, TPfAppiumEntity> implements TPfAppiumService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<TPfAppiumEntity> page = this.page(
                new Query<TPfAppiumEntity>().getPage(params),
                new QueryWrapper<TPfAppiumEntity>()

        );
        return new PageUtils(page);
    }

    @Override
    public void clickOnComments(TPfAppiumEntity tPfAppiumEntity) {

        // AndroidDriver driver = StaticMethod.DeviceConnection(tPfAppiumEntity);
        StaticMethod.handle(tPfAppiumEntity);


    }

    @Override
    public void reboot(TPfAppiumEntity tPfAppiumEntity) {
        excuteShell("adb -s " + tPfAppiumEntity.getNumber() + " reboot");
    }


//    public void appiumRun(TPfAppiumEntity tPfAppiumEntity) {
//        AndroidDriver androidDriver = null;
//        if (tPfAppiumEntity != null) {
//            DesiredCapabilities desiredCapabilities = StaticConstant.getAndroidDriver(tPfAppiumEntity);
//            try {
//                androidDriver = new AndroidDriver(new URL(StaticConstant.URL), desiredCapabilities);//虚拟机默认地址
//                if (androidDriver != null) {
//                    try {
//                        // 确定提醒点击
////                        Thread.sleep(5000);
//                        WebElement findElement1 = androidDriver.findElement(By.id("com.ss.android.ugc.aweme:id/aev"));
////                        WebElement findElement1 = StaticConstant.check(androidDriver, "com.ss.android.ugc.aweme:id/aev");
//                        findElement1.click();
//                    } catch (Exception e) {
//                        // 报错 校验一下是否存在其他干扰
//                        StaticConstant.allcheck(androidDriver);
//                        System.out.println("确定不存在 跳过执行下一步");
//                    }
//                    Thread.sleep(1000);
//                    for (int i = 0; i < 10; i++) {
//                        androidDriver.swipe(490, 1105, 479, 311, 1000);
//                    }
////                    WebElement findElement2 = androidDriver.findElement(By.id("com.ss.android.ugc.aweme:id/aqb"));
////                    findElement2.click();
//                    System.out.println("=============执行成功============");
//                }
//            } catch (Exception e) {
//                System.out.println("=============失败============");
//                System.out.println(e);
//            } finally {
//                if (androidDriver != null) {
//                    androidDriver.closeApp();
//                }
//            }
//        }
//    }
}
