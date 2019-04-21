package ru.project.cscm.integration.jms.utils;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;

@Component
public class MD5Hasher {

    @NotEmpty
    public String hash(@NotNull final byte[] bytesToHash) {

        Objects.requireNonNull(bytesToHash);

        return Hashing.md5().hashBytes(bytesToHash).toString();
    }

    public boolean matches(@NotEmpty final String expected, @NotNull final byte[] bytesToHash) {

        Objects.requireNonNull(bytesToHash);
        Objects.requireNonNull(expected);

        return hash(bytesToHash).equalsIgnoreCase(expected);
    }
}
