package org.cth.gmweb.controller;

import org.cth.gmweb.utils.CommonConfig;
import org.cth.gmweb.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author cth
 * @date 2019/06/04
 */
@ControllerAdvice
public class ExceptController {
    private Logger log = LoggerFactory.getLogger(ExceptController.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception e) {
        log.info(">>>msg:" + e.getMessage());
        return CommonUtil.returnResponse(CommonConfig.getInstance()
                .setReturnCode(-1)
                .setMessage(e.getMessage()));
    }
}
