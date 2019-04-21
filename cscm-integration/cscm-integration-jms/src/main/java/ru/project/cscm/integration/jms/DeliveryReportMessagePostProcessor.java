package ru.project.cscm.integration.jms;

import java.util.Objects;
import java.util.function.Function;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import ru.project.cscm.integration.jms.config.JmsConfiguration;
import ru.project.cscm.integration.jms.utils.BytesMessageConverter;
import ru.project.cscm.integration.jms.utils.MD5Hasher;

@Component
public class DeliveryReportMessagePostProcessor implements MessagePostProcessor {

    @Autowired
    private MD5Hasher hasher;

    @Autowired
    @BytesMessageConverter
    private Function<Message, byte[]> bodyConverter;

    @Override
    public Message postProcessMessage(Message message) throws JMSException {

        Objects.requireNonNull(message);

        message.setJMSType(JmsConfiguration.CSCM_DELIVERY_REPORT_MESSAGE_TYPE);
        message.setStringProperty(JmsConfiguration.MD5_HASH_PROPERTY, this.hasher.hash(this.bodyConverter.apply(message)).toLowerCase());

        return message;
    }
}
