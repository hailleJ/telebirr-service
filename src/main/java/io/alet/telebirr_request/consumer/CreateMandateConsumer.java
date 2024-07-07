package io.alet.telebirr_request.consumer;


import io.alet.telebirr_request.service.CreateMandateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static io.alet.telebirr_request.constants.TelebirrMessagingValue.CREATE_MANDATE_REQUEST_QUEUE;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateMandateConsumer {

    private final CreateMandateService createMandateService;

    @RabbitListener(queues = CREATE_MANDATE_REQUEST_QUEUE, concurrency = "100")
    public void consume(Map<String,String> properties) {
        createMandateService.create(properties);
    }





}
