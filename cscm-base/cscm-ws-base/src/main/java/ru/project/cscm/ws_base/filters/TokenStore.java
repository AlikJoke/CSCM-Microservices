package ru.project.cscm.ws_base.filters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class TokenStore {

    private final Map<String, Long> tokens = new ConcurrentHashMap<>();

    protected void addToken(final String token) {
        this.tokens.put(token, System.currentTimeMillis());
    }

    protected boolean isTokenExists(final String token) {
        return this.tokens.containsKey(token);
    }

    @Scheduled(fixedDelay = 1_000_000)
    public void invalidate() {

        this.tokens.entrySet().stream().filter(e -> System.currentTimeMillis() - 10_000_000 > e.getValue())
                .forEach(e -> this.tokens.remove(e.getKey()));
    }
}
