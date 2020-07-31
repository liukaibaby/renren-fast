package io.renren.modules.appium.throwable;


import io.renren.modules.appium.entity.TPfAppiumEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author : liukai
 * @date : 2020-07-06 09:50
 **/
public class Worker implements Runnable {

    //任务队列，用于取得子任务

    protected ConcurrentLinkedQueue<TPfAppiumEntity> workQueue;

    //子任务处理结果集

    protected Map<String, Object> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue<TPfAppiumEntity> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    //子任务处理的逻辑，在子类中实现具体逻辑

    public TPfAppiumEntity handle(TPfAppiumEntity input) {
        return input;
    }


    @Override
    public void run() {
        while (true) {
            //获取子任务
            TPfAppiumEntity input = workQueue.poll();

            if (input == null) {
                break;
            }

            //处理子任务
            TPfAppiumEntity re = handle(input);

            resultMap.put(Integer.toString(input.hashCode()), re);
        }
    }

}
