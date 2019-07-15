package org.cth.gmweb.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * @author cth
 * @date 2019/06/04
 */
@Aspect
@Service
public class WebAopService {
    private Logger log = LoggerFactory.getLogger(WebAopService.class);

    @Pointcut("execution(public * org.cth.gmweb.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        log.info("==================start======================");
        // 记录内容
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String classMethod = String.format("%s.%s()", joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName());
        String sessionId = request.getSession().getId();
        String uri = request.getRequestURI();
        String reqMethod = request.getMethod();
//        String ip = getRemoteIp(request);
        String ip = request.getHeader("X-Real_IP");
        String ipAddr = ip == null ? "无" : ip;
        String params = request.getParameterMap().isEmpty() ? "无" : JSONObject.toJSONString(request.getParameterMap());
        log.info(">>>request class:{}", classMethod);
        log.info(">>>sessionId:{}", sessionId);
        log.info(">>>uri:{}", uri);
        log.info(">>>http method:{}", reqMethod);
        log.info(">>>ip:{}", ipAddr);
        log.info(">>>http input:{}", params);
        log.info("========================================");
    }

    @AfterReturning(pointcut = "webLog()", returning = "resp")
    public void doAfter(Object resp){
        log.info("========================================");
        if (resp == null) {
            log.info(">>>output:null");
        } else {
            log.info(">>>response class:" + resp.getClass().getName());
            if (resp instanceof HashMap) {
                log.info(">>>output:" + JSONObject.toJSONString(resp));
            } else if (resp instanceof String) {
                log.info(">>>output:" + resp);
            } else {
                log.info(">>>output:" + resp);
            }
        }
        log.info("==================finish======================");
    }

    /**
     * nginx代理后不需要了
     * @param request request
     * @return String
     */
    @Deprecated
    private String getRemoteIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ip = null;
        String[] headKeys = {"x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP", "remote"};
        for (String key: headKeys) {
            if ("remote".equals(key)) {
                ip = request.getRemoteAddr();
            } else {
                ip = request.getHeader(key);
                if (StringUtils.isNotEmpty(ip)) {
                    break;
                }
            }
        }
        String localIp = "0:0:0:0:0:0:0:1";
        if(localIp.equals(ip)){
            //根据网卡取本机配置的IP
            try {
                InetAddress inet = InetAddress.getLocalHost();
                ip = inet.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ip;
    }
}
