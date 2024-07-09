package io.alet.telebirr_request.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.alet.telebirr_request.dto.callback.SubscriptionCallbackDTO;
import io.alet.telebirr_request.util.Xml2Java;
import io.alet.telebirr_request.util.XmlUtil;
import io.alet.telebirr_request.xml.query.QueryResult;
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

import static io.alet.telebirr_request.constants.TelebirrMessagingValue.*;

@RestController
@RequestMapping("/api/telebirr/callback/query")
@RequiredArgsConstructor
@Slf4j
public class QueryCallbackController {

    private final RabbitTemplate rabbitTemplate;


    @RequestMapping("")
    public void query(@RequestBody String xml) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(QUERY_MANDATE_CALLBACK_EXCHANGE,ROUTING_KEY,Xml2Java.queryMandateResult(xml));
    }

    Map<String,String> mandateInfo(String xml) {
        log.info("xml::{}",xml);
        try {
            Map<String, String> values = XmlUtil.extract(xml, "res:DirectDebitMandateInfo");
            Map<String, String> header = XmlUtil.extract(xml, "res:Header");
            values.putAll(header);
            log.info("values::{}", values);
            return values;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
