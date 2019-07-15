package org.cth.gmweb.service.cth;

import com.alibaba.fastjson.JSON;
import nl.bitwalker.useragentutils.UserAgent;
import org.apache.commons.codec.digest.DigestUtils;
import org.cth.gmweb.model.TokenDto;
import org.cth.gmweb.model.UserInfoBean;
import org.cth.gmweb.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * token
 * @author cth
 * @date 2019/06/28
 */
@Service
public class TokenService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;

    //生成token(格式为token:设备-加密的用户名-时间-六位随机数)
    public String generateToken(String userAgentStr, String username) {
        StringBuffer token = new StringBuffer("token:");
        //设备
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        if (userAgent.getOperatingSystem().isMobileDevice()) {
            token.append("MOBILE-");
        } else {
            token.append("PC-");
        }
        //加密的用户名
        token.append(DigestUtils.md5Hex(username) + "-");
        //时间
        token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "-");
        //六位随机字符串
        token.append(new Random().nextInt(999999 - 111111 + 1) + 111111 );
        log.info("token-->{}", token.toString());
        return token.toString();
    }

    //把token存到redis中
    public void save(String token, UserInfoBean userInfoBean) {
        TokenDto dto = new TokenDto();
        dto.setToken(token);
        dto.setTokenCreatedDate(System.currentTimeMillis());
        dto.setUserName(userInfoBean.getUserName());
        if (token.startsWith("token:PC")) {
            redisUtil.setSed(token, JSON.toJSONString(dto));
        } else {
            redisUtil.set(token, JSON.toJSONString(dto));
        }
    }
}