package org.cth.gmweb.controller.cth;

import org.cth.gmweb.controller.BaseController;
import org.cth.gmweb.model.UserInfoBean;
import org.cth.gmweb.utils.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author cth
 * @date 2019/06/17
 */
@RestController
@RequestMapping("v1/login")
public class LoginController extends BaseController {
    @GetMapping
    public Map<String, Object> login(String userName, String pwd, HttpServletRequest request, HttpServletResponse response) {
        UserInfoBean userInfo = userInfoService.selectByUserName(userName);
        CommonConfig config = CommonConfig.getInstance();
        if (userInfo == null) {
            config.setMessage("用户不存在").setReturnCode(CommonEnum.FAILURE.getValue());
        } else {
            if (userInfo.getStatus() == 0) {
                config.setMessage("用户已经停用").setReturnCode(CommonEnum.FAILURE.getValue());
            } else if (userInfo.getPassword().equals(pwd)) {
                config.setMessage("登陆成功").setReturnCode(CommonEnum.SUCCESS.getValue());
            } else {
                config.setMessage("登陆失败,密码错误").setReturnCode(CommonEnum.FAILURE.getValue());
            }
        }
        if (config.getReturnCode() == 0) {
            String userAgent = request.getHeader("user-agent");
            String token = this.tokenService.generateToken(userAgent, userName);
            this.tokenService.save(token, userInfo);
            // 浏览器关闭即失效
            CookieUtil.setCookie(request, response, "authorization", token);
        }
        return CommonUtil.returnResponse(config);
    }
}
