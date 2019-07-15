package org.cth.gmweb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回类
 * @author cth
 * @date 2019/06/17
 */
public class CommonUtil {
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    private CommonUtil() {
    }

    public static Map<String, Object> returnResponse() {
        return returnResponse(CommonConfig.getInstance());
    }

    public static Map<String, Object> returnResponse(CommonConfig config){
        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "success");
        result.put("returnCode", CommonEnum.SUCCESS.getValue());
        Field[] fields = config.getClass().getDeclaredFields();
        try {
            for (Field item: fields) {
                String fieldName = item.getName();
                String methodName = "get" + upperFirstChar(fieldName);
                Method m = config.getClass().getDeclaredMethod(methodName);
                Object value = m.invoke(config);
                if (value != null){
                    result.put(item.getName(), value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String upperFirstChar(String target) {
        return target.substring(0,1).toUpperCase() + target.substring(1);
    }
}
