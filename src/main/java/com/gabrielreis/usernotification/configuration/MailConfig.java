package com.gabrielreis.usernotification.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	public final static String PROPERTIES = "gmail.properties";
	private static Logger LOG = LoggerFactory.getLogger(MailConfig.class);

	@Bean
	JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(getProperties("gmail.user"));
		mailSender.setPassword(getProperties("gmail.password"));

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

  public Properties getEmailProperties() {
    Properties properties = new Properties();
    try (InputStream input = MailConfig.class.getClassLoader().getResourceAsStream(PROPERTIES)) {
      if (input == null) {
        LOG.error("Arquivo de propriedades nao encontrado: " + PROPERTIES);
        return null;
      }
      properties.load(input);
      return properties;
    } catch (IOException e) {
      LOG.error("Erro ao carregar arquivo de propriedades: " + e.getMessage());
    }
    return properties;
  }

  public String getProperties(String properties) {
    return getEmailProperties().getProperty(properties);
  }
}
