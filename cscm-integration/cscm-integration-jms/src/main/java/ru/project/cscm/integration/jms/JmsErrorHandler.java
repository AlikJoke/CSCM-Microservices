package ru.project.cscm.integration.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import ru.project.cscm.integration.model.DeliveryReport;
import ru.project.cscm.integration.model.ObjectFactory;

@Component
public class JmsErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(JmsErrorHandler.class);

    @Autowired
    private DeliveryReportMessageSender deliveryReportSender;

    @Override
    public void handleError(Throwable t) {

        logger.error("Exception while receiving message: ", t);

        final DeliveryReport report = ObjectFactory.get().createErrorDeliveryReport(t.getMessage());

        this.deliveryReportSender.send(report);
    }

}
