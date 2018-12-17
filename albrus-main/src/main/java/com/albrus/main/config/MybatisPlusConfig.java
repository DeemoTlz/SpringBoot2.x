package com.albrus.main.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.albrus.*.mapper"})
public class MybatisPlusConfig {

    /*@Bean("mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.albrus.*.entity");
        return sqlSessionFactory.getObject();
    }*/
}
