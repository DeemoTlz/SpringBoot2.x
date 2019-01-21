package com.albrus.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

public class GeneratorServiceEntity {

    public static void main(String[] args) {
        String packageName = "com.albrus";
        String modelName = "shiro";

        generateByTables(packageName, modelName, "albrus_resource");
    }

    private static void generateByTables(String packageName, String modelName, String... tableNames) {
        String projectPath = "D:/WorkSpace/IDEA/albrusAccount/albrus-" + modelName + "/src/main";
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/albrus_account?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";

        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)
                .setOpen(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setAuthor("albrus")
                .setEnableCache(false)
                .setOutputDir(projectPath + "/java")
                .setFileOverride(true);
        /*if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }*/

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

        // 逻辑删除字段
        strategyConfig.setLogicDeleteFieldName("deleted");

        //所有的Controller使用@RestController
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setSuperControllerClass("com.albrus.common.controller.BaseController");

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);

        new AutoGenerator().setGlobalConfig(config)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setCfg(cfg)
                .setTemplate(templateConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setModuleName(modelName)
                ).execute();
    }
}
