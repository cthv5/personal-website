package org.cth.gmweb.controller.cth;

import org.cth.gmweb.controller.BaseController;
import org.cth.gmweb.model.CthUserBean;
import org.cth.gmweb.utils.CommonConfig;
import org.cth.gmweb.utils.CommonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cth
 * @date 2019/06/03
 */
@RestController
@RequestMapping("v1/test")
public class TestController extends BaseController {
    @GetMapping("test")
    public Map<String, Object> testGet() {
        return CommonUtil.returnResponse();
    }

    @GetMapping("testGet")
    public Map<String, Object> test(@RequestParam(defaultValue="10") Long size, @RequestParam(defaultValue="1") Long current) {
        log.info(">>>{},{}", size, current);
        List<Map> tmpList = new ArrayList<>();
        if (current < 5) {
            for (int i = 0; i <= 3; i++) {
                Map<String, String> tmpMap = new HashMap<>();
                tmpMap.put("feature", String.valueOf(i));
                tmpMap.put("images", "/img/lizi.jpg");
                tmpMap.put("diagDoc", "吐吐吐" + i);
                tmpMap.put("diagTime", "2019/06/09");
                tmpList.add(tmpMap);
            }
        }
        return CommonUtil.returnResponse(CommonConfig.getInstance()
                .setList(tmpList)
                .setReturnCode(0)
                .setMessage("成功"));
    }

    @GetMapping("testGet1")
    public Map<String, Object>  testGet1() {
        CthUserBean user = new CthUserBean();
        user.setAcctName("ccc");
        user.setAge(13);
        userService.insert(user);
        return CommonUtil.returnResponse();
    }

    @GetMapping("testGet2")
    public Map<String, Object> testGet2() throws Exception{
        String toEncode = "客户 and+ () 中文啦啦";
        String encoded = URLEncoder.encode(toEncode, "utf-8");
        log.info(encoded);
        String decoded = URLDecoder.decode(encoded, "utf-8");
        log.info(decoded);

        List<CthUserBean> res = userService.findAll();
        return CommonUtil.returnResponse(CommonConfig.getInstance()
                .setList(res));
    }

    @GetMapping("testGet3")
    public Map<String, Object> testGet3() throws Exception{
        String toEncode = "客户 and%2b+() 中文啦啦";
        String decoded = URLDecoder.decode(toEncode, "utf-8");
        log.info(decoded);
        String toEncode1 = "客户 #&()+-*/,:;<=>?@中文啦啦";
        String decoded1 = URLDecoder.decode(toEncode1, "utf-8");
        log.info(decoded1);
        return CommonUtil.returnResponse();
    }
}
