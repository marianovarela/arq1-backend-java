package ar.edu.unq.tac.market;

import ar.edu.unq.tac.market.config.RepositoryRestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories
@Import(RepositoryRestConfiguration.class)
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}