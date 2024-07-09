package io.alet.telebirr_request.controller;


import io.alet.telebirr_request.util.XmlUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

import static io.alet.telebirr_request.constants.TelebirrMessagingValue.QUERY_MANDATE_CALLBACK_EXCHANGE;
import static io.alet.telebirr_request.constants.TelebirrMessagingValue.ROUTING_KEY;

@RestController
@RequestMapping("/api/telebirr/callback/cancel")
@RequiredArgsConstructor
@Slf4j
public class CancelCallbackController {

    private final RabbitTemplate rabbitTemplate;

    @RequestMapping("")
    public void cancel(@RequestBody String xml) {
        log.info("cancel mandate info::{}", mandateInfo(xml));
    }

    Map<String, String> mandateInfo(String xml) {
        log.info("xml::{}", xml);
        try {
            Map<String, String> values = XmlUtil.extract(xml, "res:DirectDebitMandateInfo");
            Map<String, String> header = XmlUtil.extract(xml, "res:Header");
            values.putAll(header);
            log.info("values::{}", values);
            return values;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.info("error::{}", e.getMessage());
        }
        return null;
    }
}
