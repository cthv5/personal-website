package org.cth.gmweb.utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 返回字段设置
 * @author cth
 * @date 2019/06/17
 */
@Data
@Accessors(chain = true)
public class CommonConfig {
    private Integer returnCode;
    private String message;
    private List list;
    private Integer total;
    private Object newObj;

    private CommonConfig() {
    }

    public static CommonConfig getInstance() {
        return new CommonConfig();
    }
}
