package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "**.repositories")
@PropertySource("classpath:persistence-sqlite.properties")
// Permet la configuration de/des bases de données.
public class DbConfig {

    private final Environment env;

    @Autowired
    public DbConfig(Environment env) {
        this.env = env;
    }

    /**
     * Configure l'accès à la bdd.
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("driverClassName")));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("user"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;
    }

    /**
     * Spécifie les classes à prendre en compte.
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("**/models");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    /**
     * Lie les propriétés relatives au dialogue avec Sqlite.
     * @return Properties
     */
    private Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        String hbm = "hibernate.hbm2ddl.auto";
        if (env.getProperty(hbm) != null) {
            hibernateProperties.setProperty(hbm, env.getProperty(hbm));
        }
        String dialect = "hibernate.dialect";
        if (env.getProperty(dialect) != null) {
            hibernateProperties.setProperty(dialect, env.getProperty(dialect));
        }
        String showSql = "hibernate.show_sql";
        if (env.getProperty(showSql) != null) {
            hibernateProperties.setProperty(showSql, env.getProperty(showSql));
        }
        return hibernateProperties;
    }

}


