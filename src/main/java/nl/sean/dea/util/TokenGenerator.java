package nl.sean.dea.util;

import javax.enterprise.inject.Default;

@Default
public interface TokenGenerator {
    String generateToken();
}
