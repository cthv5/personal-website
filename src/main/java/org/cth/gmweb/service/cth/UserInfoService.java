package org.cth.gmweb.service.cth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cth.gmweb.mapper.UserInfoMapper;
import org.cth.gmweb.model.UserInfoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息
 * @author cth
 * @date 2019/06/17
 */
@Service
public class UserInfoService {
    private Logger log = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfoBean selectByUserName(String userName) {
        // mp 条件构造器
        QueryWrapper<UserInfoBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userName);
        return userInfoMapper.selectOne(queryWrapper);
    }

}
