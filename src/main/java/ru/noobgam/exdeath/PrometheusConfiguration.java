package ru.noobgam.exdeath;

import io.prometheus.client.exporter.HTTPServer;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class PrometheusConfiguration {
    @Bean
    public HTTPServer httpServer() throws IOException {
        return new HTTPServer(5000);
    }
}
