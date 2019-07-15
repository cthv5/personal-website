package org.cth.gmweb.model;

import lombok.Data;

/**
 * token dto
 * @author cth
 * @date 2019/06/28
 */
@Data
public class TokenDto {

    // token值
    private String token;

    // token创建时间
    private Long tokenCreatedDate;

    // user
    private String userName;

    // token过期时间
//    private Long tokenExpiryDate;

    // 登录状态
//    private Boolean isLogin;
}
