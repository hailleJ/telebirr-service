package io.alet.telebirr_request.controller;


import io.alet.telebirr_request.dto.callback.SubscriptionCallbackDTO;
import io.alet.telebirr_request.util.XmlUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    public void query(String xml)  {
        rabbitTemplate.convertAndSend(QUERY_MANDATE_CALLBACK_EXCHANGE,ROUTING_KEY,mandateInfo(xml));
    }

    Map<String,String> mandateInfo(String xml) {
        log.info("xml::{}",xml);
        try {
            Map<String, String> values = XmlUtil.extract(xml, "res:DirectDebitMandateInfo");
            Map<String, String> header = XmlUtil.extract(xml, "res:Header");
            String conversationID = header.get("res:conversationid");
            String mandateID = values.get("com:mandateid");
            log.info("{}:: mandateID::{}", conversationID,mandateID);
            values.putAll(header);
            values.forEach((s, s2) -> log.info("{}::{}",s,s2));
            return values;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
