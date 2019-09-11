package com.spbu.askimage.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.spbu.askimage.repository"])
@PropertySource("classpath:ascii.properties")
open class ApplicationConfig