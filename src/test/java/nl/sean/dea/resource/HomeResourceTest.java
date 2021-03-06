package nl.sean.dea.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeResourceTest {

    private HomeResource sut;

    @BeforeEach
    void setUp() {
        sut = new HomeResource();
    }

    @Test
    void loadsHomepage() {
        Response actualResult = sut.getHomePage();
        assertEquals("Hello world!", actualResult.getEntity());
    }
}