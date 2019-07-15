package org.cth.gmweb.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cth.gmweb.model.CthUserBean;

import java.util.List;

/**
 * 测试用
 * @author cth
 * @date 2019/06/03
 */
public interface CthUserMapper extends BaseMapper<CthUserBean> {
    /**
     *  新增用户
     * @param user 用户
     * @return int
     */
    int insertUser(CthUserBean user);

    /**
     * 查询所有用户
     * @return list
     */
    List<CthUserBean> findAllUser();
}
