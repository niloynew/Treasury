package com.mislbd.ababil.treasury.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("AbabilTreasuryRepositoryConfiguration")
@EnableTransactionManagement
@EntityScan(basePackages = {"com.mislbd"})
@EnableJpaRepositories(basePackages = {"com.mislbd.ababil.treasury.repository.jpa", "com.mislbd"})
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {}
