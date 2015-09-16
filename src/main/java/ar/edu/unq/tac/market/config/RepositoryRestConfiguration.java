package ar.edu.unq.tac.market.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import ar.edu.unq.tac.market.domain.Event;

@Configuration
public class RepositoryRestConfiguration extends RepositoryRestMvcConfiguration {
    @Value("${rest.base_path}")
    private String base_path;

    @Override
    protected void configureRepositoryRestConfiguration(org.springframework.data.rest.core.config.RepositoryRestConfiguration config) {
        config.exposeIdsFor(Event.class);
        try {
            config.setBaseUri(new URI(this.base_path));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
