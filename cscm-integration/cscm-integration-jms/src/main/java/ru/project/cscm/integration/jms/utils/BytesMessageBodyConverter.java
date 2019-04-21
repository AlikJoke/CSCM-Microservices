package ru.project.cscm.integration.jms.utils;

import java.util.function.Function;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@BytesMessageConverter
public class BytesMessageBodyConverter implements Function<Message, byte[]> {

    @Override
    @NotNull
    public byte[] apply(@NotNull final Message message) {

        if (message instanceof BytesMessage) {

            final BytesMessage bytesMessage = (BytesMessage) message;
            return convert(bytesMessage);
        } else {

            throw new IllegalArgumentException();
        }
    }

    private byte[] convert(final BytesMessage message) {

        try {

            final byte[] body = new byte[(int) message.getBodyLength()];
            message.readBytes(body);

            return body;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
