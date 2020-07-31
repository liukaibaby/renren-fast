package io.renren.modules.appium.throwable;

import io.renren.modules.appium.entity.TPfAppiumEntity;
import io.renren.modules.appium.overall.StaticConstant;

/**
 * @author : liukai
 * @date : 2020-07-06 09:51
 **/
public class MainMethod {

    public static boolean openTask() {

        //固定使用5个Worker，并指定Worker
        Master m = new Master(new PlusWorker(), StaticConstant.WORKER);

        // 判断是否有连接设备号 没有 直接退出
        if (StaticConstant.UDID == null || StaticConstant.UDID.size() <= 0) {
            return false;
        }

        //根据用户 开启线程
        for (int i = 0; i < 1; i++) {
            TPfAppiumEntity tPfAppiumEntity = new TPfAppiumEntity();

            tPfAppiumEntity.setNumber(StaticConstant.UDID.get(0));

            if (tPfAppiumEntity.getNumber() != null && !tPfAppiumEntity.getNumber().equals("")) {

                // 线程提交

                m.submit(tPfAppiumEntity);

                // 线程提交成功 删除 当前 手机设备 防止手机设备重复执行

                //   StaticConstant.UDID.remove(0);
            }
        }
        // 程序开始时间

        long start = System.currentTimeMillis();

        //开始计算

        m.execute();

        // 所有线程全部执行完毕 打印 执行执行时间

        while (true) {
            if (m.isComplete()) {
                System.out.println();
                long end = System.currentTimeMillis() - start;
                System.out.println("程序执行时间：" + end + "毫秒");
                break;
            }
        }
        return true;
    }

}
