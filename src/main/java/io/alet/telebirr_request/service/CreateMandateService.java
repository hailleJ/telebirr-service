package io.alet.telebirr_request.service;

import io.alet.telebirr_request.client.MandateClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static io.alet.telebirr_request.constants.TelebirrConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateMandateService {

    private final MandateClient mandateClient;
    public void create(Map<String, String> properties) {
        String mandateRequest = mandateRequest(properties);
        log.info("RequestBody::{}", mandateRequest);
        mandateClient.send(mandateRequest);
    }

    public String mandateRequest(Map<String, String> properties) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date());
        String referenceNumber =  properties.get(MSISDN).substring(3);
        if (Objects.equals(properties.get(MSISDN), "251925780292")) {
            referenceNumber = "STAGING_001";
        } else if (Objects.equals(properties.get(MSISDN), "251911239079")) {
            referenceNumber = "TH_012";
        } else if (Objects.equals(properties.get(MSISDN), "251911216238")) {
            referenceNumber = "TH_016";
        }



        log.info("referenceNumber::{}", referenceNumber);
        return "<soapenv:Envelope\n" +
                "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "    xmlns:api=\"http://cps.huawei.com/cpsinterface/api_requestmgr\"\n" +
                "    xmlns:req=\"http://cps.huawei.com/cpsinterface/request\"\n" +
                "    xmlns:com=\"http://cps.huawei.com/cpsinterface/common\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "        <api:Request>\n" +
                "            <req:Header>\n" +
                "                <req:Version>1.0</req:Version>\n" +
                "                <req:CommandID>CreateDirectDebitMandateByCustomer</req:CommandID>\n" +
                "                <req:OriginatorConversationID>S_X2013012921001</req:OriginatorConversationID>\n" +
                "                <req:ConversationID>" + properties.get(CONVERSATION_ID) + "</req:ConversationID>\n" +
                "                <req:Caller>\n" +
                "                    <req:CallerType>2</req:CallerType>\n" +
                "                    <req:ThirdPartyID>" + properties.get(THIRD_PARTY_ID) + "</req:ThirdPartyID>\n" +
                "                    <req:Password>" + properties.get(THIRD_PARTY_PASSWORD)+ "</req:Password>\n" +
                "                    <req:ResultURL>" + properties.get(TELEBIRR_PROXY_URL)+ "/callback/subscription</req:ResultURL>\n" +
                "                </req:Caller>\n" +
                "                <req:KeyOwner>1</req:KeyOwner>\n" +
                "                <req:Timestamp>"+timestamp+"</req:Timestamp>\n" +
                "            </req:Header>\n" +
                "            <req:Body>\n" +
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
                "                <req:CreateDirectDebitMandateByPayerRequest>\n" +
                "                    <req:Payee>\n" +
                "                        <com:IdentifierType>4</com:IdentifierType>\n" +
                "                        <com:IdentifierValue>" + properties.get(IDENTIFIER4_VALUE) + "</com:IdentifierValue>\n" +
                "                    </req:Payee>\n" +
                "                    <req:DirectDebitMandateInfo>\n" +
                "                        <com:PayerReferenceNumber>" + referenceNumber + "</com:PayerReferenceNumber>\n" +
                "                        <com:AgreedTC>1</com:AgreedTC>\n" +
                "                        <com:FirstPaymentDate>" + properties.get(FIRST_PAYMENT_DATE) + "</com:FirstPaymentDate>\n" +
                "                        <com:Frequency>06</com:Frequency>\n" +
                "                        <com:ExpiryDate>" + LocalDate.now().plusYears(10).toString().replace("-", "") + "</com:ExpiryDate>\n" +
                "                </req:DirectDebitMandateInfo>\n" +
                "                </req:CreateDirectDebitMandateByPayerRequest>\n" +
                "            </req:Body>\n" +
                "        </api:Request>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }


}
