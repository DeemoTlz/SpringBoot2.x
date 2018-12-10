package com.albrus.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class GeneratorServiceEntity {

    public static void main(String[] args) {
        String packageName = "com.albrus";
        String modelName = "shiro";

        generateByTables(packageName, modelName, "albrus_user");
    }

    private static void generateByTables(String packageName, String modelName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/albrus_account?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("cyberaudit")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("albrus_")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        //是否继承实体bean父类
        strategyConfig.setSuperEntityClass("com.albrus.common.entity.BaseEntity");
        strategyConfig.setSuperEntityColumns("id", "generate_by", "generate_time", "update_by", "update_time");

        strategyConfig.setLogicDeleteFieldName("deleted");

        //所有的Controller使用@RestController
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setSuperControllerClass("com.albrus.common.controller.BaseController");

        config.setActiveRecord(true)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setAuthor("albrus")
                .setEnableCache(false)
                .setOutputDir("D:\\WorkSpace\\IDEA\\albrusAccount\\albrus-" + modelName + "\\src\\main\\java")
                .setFileOverride(true);
        /*if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }*/
        new AutoGenerator().setGlobalConfig(config)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setModuleName(modelName)
                                .setController("controller")
                                .setEntity("entity")
                                .setXml("mapper.mapping")
                ).execute();
    }
}
