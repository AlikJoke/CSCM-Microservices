package ru.project.cscm.integration.jms.config;

import java.io.IOException;
import java.util.Arrays;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import ru.project.cscm.integration.jms.JmsErrorHandler;
import ru.project.cscm.integration.model.enums.StatisticsType;

@Configuration
@EnableJms
public class JmsConfiguration {

    public static final String CSCM_INTEGRATION_FACTORY = "cscm-integration-factory";
    public static final String CSCM_INTEGRATION_QUEUE = "cscm-integration-queue";
    public static final String CSCM_STATISTICS_MESSAGE_TYPE = "AtmStatistics";
    public static final String CSCM_MONITORING_MESSAGE_TYPE = "AtmMonitoringStatistics";
    public static final String CSCM_DELIVERY_REPORT_MESSAGE_TYPE = "DeliveryReport";
    
    public static final String MD5_HASH_PROPERTY = "fileMd5";

    @Autowired
    private JmsErrorHandler errorHandler;
    
    @Bean(CSCM_INTEGRATION_FACTORY)
    public DefaultJmsListenerContainerFactory integrationFactory(ConnectionFactory connectionFactory,
            MessageConverter xmlJmsMessageConverter, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setAutoStartup(true);
        factory.setMessageConverter(xmlJmsMessageConverter);
        factory.setErrorHandler(this.errorHandler);
        configurer.configure(factory, connectionFactory);

        return factory;
    }
    
    @Bean
    public ConnectionFactory connectionFactory(final Environment env) {
        final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(env.getProperty("spring.activemq.broker-url"));
        connectionFactory.setCloseTimeout(env.getProperty("spring.activemq.close-timeout", Integer.class));
        connectionFactory.setNonBlockingRedelivery(true);
        connectionFactory.setUserName(env.getProperty("spring.activemq.user"));
        connectionFactory.setPassword(env.getProperty("spring.activemq.password"));
        connectionFactory.setSendTimeout((env.getProperty("spring.activemq.send-timeout", Integer.class)));
        connectionFactory.setTrustAllPackages(false);
        connectionFactory.setTrustedPackages(Arrays.asList("ru.project.cscm.integration"));

        return connectionFactory;
    }
    
    @Bean
    public MessageConverter xmlJmsMessageConverter(Jaxb2Marshaller marshaller) {
        MarshallingMessageConverter converter = new MarshallingMessageConverter();
        converter.setTargetType(MessageType.BYTES);
        converter.setMarshaller(marshaller);
        converter.setUnmarshaller(marshaller);

        return converter;
    }

    @Bean
    public Jaxb2Marshaller marshller() throws IOException {
        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setPackagesToScan("ru.project.cscm.integration.model");

        final ClassPathResource xsdFile = new ClassPathResource("xsd/AtmActualStateStatistics.xsd");
        final ClassPathResource reportFile = new ClassPathResource("xsd/DeliveryReport.xsd");

        marshaller.setSchemas(reportFile, xsdFile);

        return marshaller;
    }
}
