package ar.edu.unq.tac.market.config;

/**
 * HTTPS Configuration
 */

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class HTTPSConfig {

	@Value("${keystore.file:}")
	private String keystoreFile;

	@Value("${keystore.pass:}")
	private String keystorePassword;

	@Value("${keystore.alias:}")
	private String keystoreAlias;

	@Value("${keystore.type:}")
	private String keystoreType;

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer factory) {
				if (factory instanceof TomcatEmbeddedServletContainerFactory) {
					customizeTomcat((TomcatEmbeddedServletContainerFactory) factory);
				}
			}

			public void customizeTomcat(
					TomcatEmbeddedServletContainerFactory factory) {
				factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

					@Override
					public void customize(Connector connector) {

						if (!StringUtils.isEmpty(keystoreFile)) {
							connector.setSecure(true);
							connector.setScheme("https");
							connector.setAttribute("keyAlias", keystoreAlias);
							connector.setAttribute("keystorePass",
									keystorePassword);
							connector
									.setAttribute("keystoreFile", keystoreFile);
							connector
									.setAttribute("keystoreType", keystoreType);
							connector.setAttribute("clientAuth", "false");
							connector.setAttribute("sslProtocol", "TLS");
							connector.setAttribute("SSLEnabled", true);
						}
					}
				});
			}
		};
	}
}