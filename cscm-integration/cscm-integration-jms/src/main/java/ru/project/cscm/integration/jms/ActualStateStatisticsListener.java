package ru.project.cscm.integration.jms;

import java.io.ByteArrayInputStream;
import java.util.Objects;
import java.util.function.Function;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import ru.project.cscm.integration.dao.StatisticsDao;
import ru.project.cscm.integration.jms.config.JmsConfiguration;
import ru.project.cscm.integration.jms.utils.BytesMessageConverter;
import ru.project.cscm.integration.jms.utils.MD5Hasher;
import ru.project.cscm.integration.model.DeliveryReport;
import ru.project.cscm.integration.model.ObjectFactory;
import ru.project.cscm.integration.model.Statistics;
import ru.project.cscm.integration.model.enums.StatisticsType;
import ru.project.cscm.integration.validation.XmlValidator;
import ru.project.cscm.integration.validation.XmlValidator.ValidationResult;

@Component
public class ActualStateStatisticsListener {

    private static Logger log = LoggerFactory.getLogger(ActualStateStatisticsListener.class);

    @Autowired
    private StatisticsDao statsDao;

    @Autowired
    private DeliveryReportMessageSender messageSender;

    @Autowired
    private XmlValidator validator;

    @Autowired
    private MD5Hasher hasher;

    @Autowired
    @BytesMessageConverter
    private Function<Message, byte[]> bodyConverter;

    @JmsListener(
            containerFactory = JmsConfiguration.CSCM_INTEGRATION_FACTORY, 
            destination = JmsConfiguration.CSCM_INTEGRATION_QUEUE, 
            selector = "JMSType='" + JmsConfiguration.CSCM_MONITORING_MESSAGE_TYPE + "' "
                    + "OR JMSType='" + JmsConfiguration.CSCM_STATISTICS_MESSAGE_TYPE + "'")
    public void receive(final Message message) throws JMSException {

        Objects.requireNonNull(message);

        log.info("Received message: [" + message.getJMSMessageID() + "]");

        final ChecksResult checksResult = this.makeChecks(message, StatisticsType.value(message.getJMSType()));
        if (checksResult.messageBody != null) {
            this.statsDao.save(new Statistics(StatisticsType.value(message.getJMSType()), checksResult.messageBody));
        }

        this.messageSender.send(checksResult.report);
    }
    
    private ChecksResult makeChecks(final Message message, final StatisticsType statsType) throws JMSException {

        if (!(message instanceof BytesMessage)) {

            return new ChecksResult(createErrorReport("Message must be instanceof " + BytesMessage.class.getCanonicalName()), null);
        }

        final byte[] body = this.bodyConverter.apply(message);

        if (!message.propertyExists("fileMd5")) {

            return new ChecksResult(createErrorReport("Message does not contain MD5 hash property: " + message.getJMSMessageID()), null);
        } else if (!this.hasher.matches(message.getStringProperty("fileMd5"), body)) {
            return new ChecksResult(createErrorReport("Message is corrupted: hash is not matches with body: " + message.getJMSMessageID()),
                    null);
        }

        final ValidationResult validationResult = this.validator.validate(new ByteArrayInputStream(body), statsType.getXsdPath());

        if (validationResult.success()) {

            return new ChecksResult(ObjectFactory.get().createSuccessDeliveryReport(), body);
        }

        return new ChecksResult(createErrorReport(validationResult.addInfo()), null);
    }

    private DeliveryReport createErrorReport(final String info) {
        return ObjectFactory.get().createErrorDeliveryReport(info);
    }

    private static class ChecksResult {

        private final DeliveryReport report;
        private final byte[] messageBody;

        private ChecksResult(DeliveryReport report, byte[] messageBody) {
            this.report = report;
            this.messageBody = messageBody;
        }

    }
}
