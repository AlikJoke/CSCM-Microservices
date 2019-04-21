package ru.project.cscm.integration.jms;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import ru.project.cscm.integration.jms.config.JmsConfiguration;
import ru.project.cscm.integration.model.DeliveryReport;

@Component
@Lazy
public class DeliveryReportMessageSender {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryReportMessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessagePostProcessor deliveryReportPostProcessor;

    public void send(@NotNull final DeliveryReport report) {

        Objects.requireNonNull(report);

        logger.debug("Send message = {}", report);

        this.jmsTemplate.convertAndSend(JmsConfiguration.CSCM_INTEGRATION_QUEUE, report, this.deliveryReportPostProcessor);
    }
}
