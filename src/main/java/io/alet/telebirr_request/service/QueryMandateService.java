package io.alet.telebirr_request.service;


import io.alet.telebirr_request.client.MandateClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static io.alet.telebirr_request.constants.TelebirrConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueryMandateService {

    private final MandateClient mandateClient;

    String curl = "curl -vvv -H 'Content-Type:text/xml' -k 'http://10.180.70.177:30001/payment/services/APIRequestMgrService' -d ";
    public void query(Map<String, String> properties) {

        String mandateRequest = queryRequest(properties);
        String body = mandateRequest.replace("\n", "").replace("\t", "").replace("\"", "'").replace("            <", "<");
        String mandate = curl + " \"" + body + "\"";
        log.info("RequestBody::{}", mandate);
        mandateClient.send(body);

    }

    private String queryRequest(Map<String, String> properties) {

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date());
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:api=\"http://cps.huawei.com/cpsinterface/api_requestmgr\" xmlns:req=\"http://cps.huawei.com/cpsinterface/request\" xmlns:com=\"http://cps.huawei.com/cpsinterface/common\">\n" +
                "  <soapenv:Header/>\n" +
                "  <soapenv:Body>\n" +
                "    <api:Request>\n" +
                "      <req:Header>\n" +
                "        <req:Version>1.0</req:Version>\n" +
                "        <req:CommandID>QueryDirectDebitMandateByPayer</req:CommandID>\n" +
                "        <req:OriginatorConversationID>S_X20130129210987110</req:OriginatorConversationID>\n" +
                "        <req:ConversationID>" + properties.get(CONVERSATION_ID) + "</req:ConversationID>\n" +
                "                <req:Caller>\n" +
                "                    <req:CallerType>2</req:CallerType>\n" +
                "                    <req:ThirdPartyID>" + properties.get(THIRD_PARTY_ID) + "</req:ThirdPartyID>\n" +
                "                    <req:Password>" + properties.get(THIRD_PARTY_PASSWORD) + "</req:Password>\n" +
                "                    <req:ResultURL>" + properties.get(TELEBIRR_PROXY_URL) + "/api/telebirr/callback/query</req:ResultURL>\n" +
                "                </req:Caller>\n" +
                "        <req:KeyOwner>1</req:KeyOwner>\n" +
                "        <req:Timestamp>" + timestamp + "</req:Timestamp>\n" +
                "      </req:Header>\n" +
                "      <req:Body>\n" +
                "                <req:Identity>\n" +
                "                    <req:Initiator>\n" +
                "                        <req:IdentifierType>14</req:IdentifierType>\n" +
                "                        <req:Identifier>" + properties.get(IDENTIFIER14_VALUE) + "</req:Identifier>\n" +
                "                        <req:SecurityCredential>" + properties.get(IDENTIFIER14_PASSWORD) + "</req:SecurityCredential>\n" +
                "                    </req:Initiator>\n" +
                "                    <req:ReceiverParty>\n" +
                "                        <req:IdentifierType>1</req:IdentifierType>\n" +
                "                        <req:Identifier>" + properties.get(MSISDN) + "</req:Identifier>\n" +
                "                    </req:ReceiverParty>\n" +
                "                </req:Identity>\n" +
                "        <req:QueryDirectDebitMandateByPayerRequest>\n" +
                "          <req:MandateStatus>03</req:MandateStatus>\n" +
                "          <req:MandateStatus>01</req:MandateStatus>\n" +
                "          <req:MandateStatus>02</req:MandateStatus>\n" +
                "          <req:MandateStatus>04</req:MandateStatus>\n" +
                "        </req:QueryDirectDebitMandateByPayerRequest>\n" +
                "      </req:Body>\n" +
                "    </api:Request>\n" +
                "  </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }


}
