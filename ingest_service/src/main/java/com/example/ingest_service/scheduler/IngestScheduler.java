package com.example.ingest_service.scheduler;

import com.example.commondto.dto.SatRecord;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableScheduling
public class IngestScheduler {

    private final RestTemplate rt = new RestTemplate();
    private final AmqpTemplate amqp;

    @Value("${sat.api-url}")
    private String apiUrl;

    public IngestScheduler(AmqpTemplate amqp) {
        this.amqp = amqp;
    }

    @Scheduled(fixedDelay = 30_000)
    public void pollAndPublish() {
        SatRecord[] recs = rt.getForObject(apiUrl, SatRecord[].class);
        if (recs != null) {
            for (SatRecord r : recs) {
                amqp.convertAndSend("sat.records", r);
            }
        }
    }
}
