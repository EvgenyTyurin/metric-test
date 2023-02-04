package org.example;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Демонстрация механизма преобразования метрик микрометра
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * Добавление тегов в метрику
     */
    @Bean
    public MeterFilter metricTagsAddition() {
        return new MeterFilter() {
            @Override
            public Meter.Id map(Meter.Id id) {
                if (id.getName().equals("http.server.requests"))
                    return id.withTags(Tags.concat(Tags.of("key1", "value1"), id.getTagsAsIterable()));
                else
                    return id;
            }
        };
    }

    /**
     * Переименование тегов метрики
     */
    @Bean
    public MeterFilter metricTagsRenaming() {
        return MeterFilter
                .renameTag("http.server.requests", "method", "method_name");
    }
}