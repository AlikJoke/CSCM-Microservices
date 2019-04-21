package ru.project.cscm.integration.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.project.cscm.integration.dao.config.DaoConfiguration;
import ru.project.cscm.integration.jms.config.JmsConfiguration;
import ru.project.cscm.integration.scheduling.config.SchedulingConfiguration;
import ru.project.cscm.integration.ws.config.WsConfiguration;

@Configuration
@Import({ DaoConfiguration.class, JmsConfiguration.class, SchedulingConfiguration.class, WsConfiguration.class })
@ComponentScan("ru.project.cscm")
public class CscmConfiguration {

}
