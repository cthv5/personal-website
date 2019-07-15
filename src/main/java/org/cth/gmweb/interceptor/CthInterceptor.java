package org.cth.gmweb.interceptor;

import org.cth.gmweb.utils.CookieUtil;
import org.cth.gmweb.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CthInterceptor implements HandlerInterceptor {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;

    //存放鉴权信息的Header名称，默认是Authorization
    private static final String HTTP_AUTH_HEADER = "authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(">>>CthInterceptor preHandle...");
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        String token = request.getParameter(HTTP_AUTH_HEADER);
        String token = CookieUtil.getCookieValue(request, HTTP_AUTH_HEADER);
        log.info(">>>token:{}", token);
        if (redisUtil.checkToken(token)) {
            return true;
        } else {
            throw new RuntimeException(">>>token检验失败,不允许访问...");
            // ajax请求无法重定向
//            response.sendRedirect("/myerror");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
