package org.cth.gmweb.controller;

import org.cth.gmweb.service.cth.TokenService;
import org.cth.gmweb.service.cth.UserInfoService;
import org.cth.gmweb.service.cth.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cth
 * @date 2019/06/03
 */
public class BaseController {
    protected Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserInfoService userInfoService;

    @Autowired
    protected TokenService tokenService;
}
