package org.cth.gmweb.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cth
 * @date 2019/06/03
 */
public class MpGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        String prefix = "UserInfo";
        String tableName = "user_info";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("cth");
        gc.setOpen(false);
        gc.setMapperName(prefix + "Mapper");
        gc.setEntityName(prefix + "Bean");
        gc.setControllerName(prefix + "Controller");
        gc.setServiceName(prefix + "Service");
        //主键策略
        gc.setIdType(null);
        // 设置时间类型使用哪个包下的
        gc.setDateType(DateType.ONLY_DATE);
        //实体属性 Swagger2 注解
        gc.setSwagger2(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://*:3306/cthdev??useUnicode=true&characterEncoding=utf8&useSSL=false");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("");
        dsc.setPassword("");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("org.cth.gmweb")
                .setEntity("model")
                .setMapper("mapper")
                .setController("controller.cth")
                .setService("service.cth");
        mpg.setPackageInfo(pc);

        //配置自定义属性注入
        InjectionConfig injectionConfig = new InjectionConfig() {
            //.vm模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                map.put("lowerCaseMapperName", lowCaseFirstChar(this.getConfig().getGlobalConfig().getMapperName()));
                map.put("lowerCaseEntityName", lowCaseFirstChar(this.getConfig().getGlobalConfig().getEntityName()));
                map.put("methodPrefix", prefix);
                map.put("lowerCasePrefix", lowCaseFirstChar(prefix));
                map.put("localDate", dateFormat());
                this.setMap(map);
            }
        };
        mpg.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity2.java");
        templateConfig.setController("templates/controller2.java");
        templateConfig.setMapper("templates/mapper2.java");
        templateConfig.setService("templates/service2.java");
        templateConfig.setServiceImpl(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
        // restController
        strategy.setRestControllerStyle(true);
        // 自定义继承的类全称，带包名
        strategy.setSuperControllerClass("org.cth.gmweb.controller.BaseController");
        strategy.setSuperServiceClass(null);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    private static String lowCaseFirstChar(String str) {
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }

    private static String dateFormat() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTimeFormatter.format(dateTime);
    }

}
