package ru.noobgam.exdeath;

import io.prometheus.client.Gauge;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Random;

@Component
public class DataCollector {

    private static final Logger logger = LoggerFactory.getLogger(DataCollector.class);
    private static final Random R = new Random();
    private static final Gauge CONNECTIVITY_GAUGE = Gauge.build()
            .name("google_connectivity")
            .help("Connectivity gauge to different urls")
            .labelNames("url")
            .register();

    @Scheduled(fixedDelay = 500)
    @SneakyThrows
    public void collectData() {
        logger.info("Collecting data...");
        var url = "www.google.com";
        boolean connected = checkUrl(url);
        CONNECTIVITY_GAUGE.labels(url).set(connected ? 1 : 0);
    }

    @SneakyThrows
    public static boolean checkUrl(String url) {
        Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 " + url);
        int returnVal = p1.waitFor();
        return returnVal == 0;
    }
}
