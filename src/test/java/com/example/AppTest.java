package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {

    @Test
    public void testAppStatus() {
        App app = new App();
        assertNotNull(app);
        assertEquals("UP", app.getStatus(), "App status should be UP");
    }

    @Test
    public void testAddition() {
        App app = new App();
        assertEquals(15, app.add(5, 10), "5 + 10 should equal 15");
        assertEquals(0, app.add(-5, 5), "-5 + 5 should equal 0");
    }
}
