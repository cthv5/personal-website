package org.cth.gmweb.controller.cth;

import org.cth.gmweb.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * spring mvc 返回静态页面，不能使用restController，页面默认在static下
 * @author cth
 * @date 2019/06/15
 */
@Controller
@RequestMapping("/")
public class HtmlController extends BaseController {
    @GetMapping("/")
    public String loginHtm() {
        log.info(">>>login");
        return "/page/login/login.html";
    }
}
