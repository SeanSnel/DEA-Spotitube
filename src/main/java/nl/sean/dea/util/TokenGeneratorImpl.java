package nl.sean.dea.util;

import java.util.UUID;

public class TokenGeneratorImpl implements TokenGenerator {

    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
