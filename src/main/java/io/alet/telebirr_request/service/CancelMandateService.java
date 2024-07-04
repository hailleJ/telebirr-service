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
public class CancelMandateService {


    private final MandateClient mandateClient;

    public void cancel(Map<String, String> properties) {
        String mandateRequest = cancelRequest(properties);
        log.info("RequestBody::{}", mandateRequest);
        mandateClient.send(mandateRequest);
    }

    private String cancelRequest(Map<String, String> properties) {


        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date());

        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:api=\"http://cps.huawei.com/cpsinterface/api_requestmgr\" xmlns:req=\"http://cps.huawei.com/cpsinterface/request\" xmlns:com=\"http://cps.huawei.com/cpsinterface/common\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <api:Request>\n" +
                "      <req:Header>\n" +
                "        <req:Version>1.0</req:Version>\n" +
                "        <req:CommandID>CancelCustomerDirectDebitMandateByPayer</req:CommandID>\n" +
                "        <req:OriginatorConversationID>S_X20130129210987110</req:OriginatorConversationID>\n" +
                "        <req:ConversationID>" + properties.get(CONVERSATION_ID) + "</req:ConversationID>\n" +
                "                <req:Caller>\n" +
                "                    <req:CallerType>2</req:CallerType>\n" +
                "                    <req:ThirdPartyID>" + properties.get(THIRD_PARTY_ID) + "</req:ThirdPartyID>\n" +
                "                    <req:Password>" + properties.get(THIRD_PARTY_PASSWORD) + "</req:Password>\n" +
                "                    <req:ResultURL>" + properties.get(TELEBIRR_PROXY_URL) + "/telebirr/cancel</req:ResultURL>\n" +
                "                </req:Caller>\n" +
                "        <req:KeyOwner>1</req:KeyOwner>\n" +
                "        <req:Timestamp>" + timestamp + "</req:Timestamp>\n" +
                "      </req:Header>\n" +
                "         <req:Body>\n" +
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
                "            <req:CancelDirectDebitMandateByPayerRequest>\n" +
                "               <req:MandateID>" + properties.get(MANDATE_ID) + "</req:MandateID>\n" +
                "            </req:CancelDirectDebitMandateByPayerRequest>\n" +
                "         </req:Body>\n" +
                "      </api:Request>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }


}
