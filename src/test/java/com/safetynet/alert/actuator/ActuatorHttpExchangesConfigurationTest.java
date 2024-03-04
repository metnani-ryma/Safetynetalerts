package com.safetynet.alert.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@SpringJUnitConfig
public class ActuatorHttpExchangesConfigurationTest {

    @MockBean
    private HttpExchangeRepository httpExchangeRepository;

    @Test
    public void testHttpTraceRepositoryBeanExists() {
        assertNotNull(httpExchangeRepository);
    }
}
