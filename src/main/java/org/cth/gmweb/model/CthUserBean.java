package org.cth.gmweb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 测试用
 * @author cth
 * @date 2019/06/03
 */
@Data
@TableName("cth_user")
public class CthUserBean implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String acctName;

    private int age;
}
