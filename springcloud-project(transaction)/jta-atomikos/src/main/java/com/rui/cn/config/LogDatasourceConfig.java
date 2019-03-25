package com.rui.cn.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置log数据源
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
@Configuration
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "com.rui.cn.dao.log", entityManagerFactoryRef = "logEntityManager",
        transactionManagerRef = "transactionManager")
public class LogDatasourceConfig {

    @Bean(name = "logDatasource")
    @Qualifier("logDatasource")
    @ConfigurationProperties(prefix="spring.jta.atomikos.datasource.log")
    public DataSource logDatasource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "logEntityManager")
    public LocalContainerEntityManagerFactoryBean logEntityManager(TransactionManager transactionManager, UserTransaction userTransaction) throws Throwable {
        AtomikosJtaPlatfrom.setTransactionManager(transactionManager);
        AtomikosJtaPlatfrom.setUserTransaction(userTransaction);

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabase(Database.H2);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatfrom.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        properties.put("hibernate.hbm2ddl.auto","update");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(logDatasource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.rui.cn.domain.log");
        entityManager.setPersistenceUnitName("logPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }
}
