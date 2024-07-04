package io.alet.telebirr_request.consumer;

import io.alet.telebirr_request.service.CancelMandateService;
import io.alet.telebirr_request.service.QueryMandateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

import static io.alet.telebirr_request.constants.TelebirrMessagingValue.CREATE_MANDATE_REQUEST_QUEUE;
import static io.alet.telebirr_request.constants.TelebirrMessagingValue.QUERY_MANDATE_CALLBACK_QUEUE;

@Component
@Slf4j
@RequiredArgsConstructor
public class QueryMandateConsumer {

    private final QueryMandateService queryMandateService;
    @RabbitListener(queues = QUERY_MANDATE_CALLBACK_QUEUE, concurrency = "100")
    public void consume(Map<String,String> properties) {
        queryMandateService.query(properties);
    }
}
