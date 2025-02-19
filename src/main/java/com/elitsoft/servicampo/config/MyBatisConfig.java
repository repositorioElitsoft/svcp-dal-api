package com.elitsoft.servicampo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 */
@Configuration
@MapperScan("com.elitsoft.servicampo.mapper") // Scan your Mapper Interface Package
public class MyBatisConfig {

    @Value("${mybatis.schema}")
    private String schema;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // This is the correct way to set the log implementation:
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        Properties variables = new Properties();
        variables.setProperty("schema", schema + ".");
        configuration.setVariables(variables);
        configuration.setLogImpl(org.apache.ibatis.logging.slf4j.Slf4jImpl.class); // Or Log4j2Impl, etc.

        factoryBean.setConfiguration(configuration);

        factoryBean.setTypeAliasesPackage("com.elitsoft.servicampo.domain.entity"); // Optional: For type aliases
        // Correct way to set mapper locations:
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml")); // Use Resource array

        // You can add MyBatis configuration here if needed (e.g., type aliases, mappers location)
        return factoryBean.getObject();
    }
}