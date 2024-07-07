package io.alet.telebirr_request.consumer;

import io.alet.telebirr_request.service.CancelMandateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

import static io.alet.telebirr_request.constants.TelebirrMessagingValue.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class CancelMandateConsumer {

    private final CancelMandateService cancelMandateService;

    @RabbitListener(queues = CANCEL_MANDATE_REQUEST_QUEUE, concurrency = "100")
    public void consume(Map<String,String> properties) {
        cancelMandateService.cancel(properties);
    }
}
