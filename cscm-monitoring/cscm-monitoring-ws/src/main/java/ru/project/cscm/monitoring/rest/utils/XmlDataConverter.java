package ru.project.cscm.monitoring.rest.utils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

@Component
public class XmlDataConverter {

    public <T> void convert(final T dataObject, final File outFile) {

        Objects.requireNonNull(dataObject, "Data object must be not null");
        Objects.requireNonNull(outFile, "Output file must be not null");

        try {
            Marshaller jaxbMarshaller = getMarshaller(dataObject.getClass());

            jaxbMarshaller.marshal(dataObject, outFile);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    public <T> void convert(final T dataObject, final OutputStream stream) {

        Objects.requireNonNull(dataObject, "Data object must be not null");
        Objects.requireNonNull(stream, "Output stream must be not null");

        try {
            Marshaller jaxbMarshaller = getMarshaller(dataObject.getClass());

            jaxbMarshaller.marshal(dataObject, stream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    public <T> T convert(InputStream xmlStream, Class<T> dataType) {

        Objects.requireNonNull(dataType, "Data type must be not null");
        Objects.requireNonNull(xmlStream, "Input stream must be not null");

        try {
            final Unmarshaller unmarshaller = getUnmarshaller(dataType);

            @SuppressWarnings("unchecked")
            final T dataObject = (T) unmarshaller.unmarshal(xmlStream);

            return dataObject;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T convert(File xmlFile, Class<T> dataType) {

        Objects.requireNonNull(dataType, "Data type must be not null");
        Objects.requireNonNull(xmlFile, "Input file must be not null");

        try {
            final Unmarshaller unmarshaller = getUnmarshaller(dataType);

            @SuppressWarnings("unchecked")
            final T dataObject = (T) unmarshaller.unmarshal(xmlFile);

            return dataObject;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T convert(URL xmlUrl, Class<T> dataType) {

        Objects.requireNonNull(dataType, "Data type must be not null");
        Objects.requireNonNull(xmlUrl, "File URL must be not null");

        try {
            final Unmarshaller unmarshaller = getUnmarshaller(dataType);

            @SuppressWarnings("unchecked")
            final T dataObject = (T) unmarshaller.unmarshal(xmlUrl);

            return dataObject;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private Marshaller getMarshaller(final Class<?> dataType) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(dataType);
        final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        return jaxbMarshaller;
    }

    @NotNull
    private Unmarshaller getUnmarshaller(final Class<?> dataType) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(dataType);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return unmarshaller;
    }

}