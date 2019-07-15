package org.cth.gmweb.service.cth;

import org.cth.gmweb.mapper.CthUserMapper;
import org.cth.gmweb.model.CthUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cth
 * @date 2019/06/03
 */
@Service
public class UserService {
    @Autowired
    private CthUserMapper userMapper;

    public void insert(CthUserBean user) {
        userMapper.insertUser(user);
    }

    public List<CthUserBean> findAll() {
        return userMapper.findAllUser();
    }
}
