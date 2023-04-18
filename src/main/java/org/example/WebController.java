package org.example;

import io.micrometer.core.instrument.Metrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Как использовать Gauge
 */

@RestController
public class WebController {

    /**
     * Завести переменную состояния
     */
    private final AtomicInteger gauge = new AtomicInteger();

    public WebController() {
        // Завести метрику
        Metrics.gauge("my_gauge", gauge);
    }

    @GetMapping("/gauge")
    public void gauge() {
        // Обновлять метрику через переменную
        gauge.set(1);
    }
}
