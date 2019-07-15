package org.cth.gmweb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * @author cth
 * @date 2019/06/17
 */
@Data
@TableName("user_info")
public class UserInfoBean implements Serializable {

    // 主键
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 创建时间
    @TableField(value = "createAt")
    private Date createAt;

    // 修改时间
    @TableField(value = "updateAt")
    private Date updateAt;

    // 用户名
    @TableField(value = "userName")
    private String userName;

    // 密码
    private String password;

    // 1启用 0停用
    private Integer status;

    // 电话
    private String phone;

    // 备注
    private String remark;

}