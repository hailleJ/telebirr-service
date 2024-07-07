package io.alet.telebirr_request.controller;


import io.alet.telebirr_request.dto.callback.SubscriptionCallbackDTO;
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

import static io.alet.telebirr_request.constants.TelebirrMessagingValue.CREATE_MANDATE_CALLBACK_EXCHANGE;
import static io.alet.telebirr_request.constants.TelebirrMessagingValue.ROUTING_KEY;

@RestController
@RequestMapping("/api/telebirr/callback/subscription")
@RequiredArgsConstructor
@Slf4j
public class CreateCallbackController {

    private final RabbitTemplate rabbitTemplate;

    @RequestMapping("")
    public void subCallback(@RequestBody String xml) {
        rabbitTemplate.convertAndSend(CREATE_MANDATE_CALLBACK_EXCHANGE, ROUTING_KEY, getCallbackDTO(xml));
    }

    SubscriptionCallbackDTO getCallbackDTO(String xml) {

        log.info(xml);
        try {
            Map<String, String> header = XmlUtil.extract(xml, "res:Header");
            Map<String, String> body = XmlUtil.extract(xml, "res:Body");
            body.forEach((s, s2) -> log.info("{}/{}", s, s2));
            SubscriptionCallbackDTO dto = new SubscriptionCallbackDTO();
            dto.setConversationId(header.get("res:conversationid"));
            dto.setResultDesc(body.get("res:resultdesc"));
            dto.setResultType(body.get("res:resulttype"));
            dto.setResultCode(body.get("res:resultcode"));
            log.info("{} result code:: {}", dto.getConversationId(), dto.getResultCode());
            log.info("{} result type:: {}", dto.getConversationId(), dto.getResultType());
            log.info("{} result desc:: {}", dto.getConversationId(), dto.getResultDesc());
            return dto;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
