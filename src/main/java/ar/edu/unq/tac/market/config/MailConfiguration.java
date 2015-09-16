package ar.edu.unq.tac.market.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

	@Value("${mailSender.host}")
	private String host;
	@Value("${mailSender.port}")
	private Integer port;
	@Value("${mailSender.username}")
	private String username;
	@Value("${mailSender.password}")
	private String password;
	@Value("${mailSender.mail.transport.protocol}")
	private String mailTransportProtocol;
	@Value("${mailSender.mail.smtp.auth}")
	private String mailSmtpAuth;
	@Value("${mailSender.mail.smtp.starttls.enable}")
	private String mailSmtpStarttlsEnable;
	@Value("${mailSender.mail.debug}")
	private String mailDebug;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);

		Properties javaMailProperties = new Properties();
		javaMailProperties
				.put("mail.transport.protocol", mailTransportProtocol);
		javaMailProperties.put("mail.smtp.auth", mailSmtpAuth);
		javaMailProperties.put("mail.smtp.starttls.enable",
				mailSmtpStarttlsEnable);
		javaMailProperties.put("mail.debug", mailDebug);
		
		javaMailSender.setJavaMailProperties(javaMailProperties);

		return javaMailSender;
	}

	// @Bean
	// public ClassLoaderTemplateResolver emailTemplateResolver(){
	// ClassLoaderTemplateResolver emailTemplateResolver = new
	// ClassLoaderTemplateResolver();
	// emailTemplateResolver.setPrefix("/mails/");
	// emailTemplateResolver.setSuffix(".html");
	// emailTemplateResolver.setTemplateMode("HTML5");
	// emailTemplateResolver.setCharacterEncoding("UTF-8");
	// emailTemplateResolver.setOrder(1);
	//
	// return emailTemplateResolver;
	// }

}
