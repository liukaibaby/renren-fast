package io.renren.config;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : liukai
 * @date : 2020-06-12 15:18
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    //捕获所有异常 获取内部异常处理类：Exception
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Map excption(Exception e, HttpServletRequest request){

        String eMessage = e.getMessage();
        StringBuffer url = request.getRequestURL();
        System.out.println("ErrorMsg-->"+eMessage);
        System.out.println("url-->"+url);

        //打印日志
      //  LOG.error("错误信息-->"+eMessage+"===请求链接--->"+url);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("msg",eMessage);
        hashMap.put("url",url);

        return hashMap;
    }
}
