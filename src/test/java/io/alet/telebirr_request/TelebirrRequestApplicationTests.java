package io.alet.telebirr_request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.alet.telebirr_request.xml.query.*;
import org.json.JSONException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TelebirrRequestApplicationTests {

	@Test
	void contextLoads() {
	}



	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public static String xml =
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><api:Result xmlns:api=\"http://cps.huawei.com/cpsinterface/api_resultmgr\" xmlns:res=\"http://cps.huawei.com/cpsinterface/result\" xmlns:com=\"http://cps.huawei.com/cpsinterface/common\"><Header><Version>1.0</Version><OriginatorConversationID>S_X20130129210987110</OriginatorConversationID><ConversationID>8ZORL6Y2GJ90XQDQK1M1</ConversationID></Header><Body><ResultType>0</ResultType><ResultCode>0</ResultCode><ResultDesc>Process service request successfully.</ResultDesc><QueryDirectDebitMandateResult><DirectDebitMandateInfo><PayeeName>Info Tech Systems</PayeeName><PayerName>ASHENAFI NEGUSSIE TEKLU</PayerName><DirectDebitMandateInfo><com:PayerReferenceNumber>TH_012</com:PayerReferenceNumber><com:AgreedTC>1</com:AgreedTC><com:PayeeAccountName/><com:PayerAccountName/><com:FirstPaymentDate>20240308</com:FirstPaymentDate><com:Frequency>06</com:Frequency><com:MandateID>36602</com:MandateID><com:ExpiryDate>20250205</com:ExpiryDate></DirectDebitMandateInfo></DirectDebitMandateInfo><DirectDebitMandateInfo><PayeeName>Info Tech Systems</PayeeName><PayerName>ASHENAFI NEGUSSIE TEKLU</PayerName><DirectDebitMandateInfo><com:PayerReferenceNumber>INFO_19</com:PayerReferenceNumber><com:AgreedTC>1</com:AgreedTC><com:PayeeAccountName/><com:PayerAccountName/><com:FirstPaymentDate>20240531</com:FirstPaymentDate><com:Frequency>06</com:Frequency><com:MandateID>43413</com:MandateID><com:ExpiryDate>20250205</com:ExpiryDate></DirectDebitMandateInfo></DirectDebitMandateInfo></QueryDirectDebitMandateResult></Body></api:Result></soapenv:Body></soapenv:Envelope>";


	public static void main(String[] args) throws JSONException, JsonProcessingException {

		xml = xml.replace("</soapenv:Body></soapenv:Envelope>","").replace("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body>","").replace(" xmlns:api=\"http://cps.huawei.com/cpsinterface/api_resultmgr\" xmlns:res=\"http://cps.huawei.com/cpsinterface/result\" xmlns:com=\"http://cps.huawei.com/cpsinterface/common\"","")
				.replace("api:","").replace("com:","");
		System.out.println(xml);


		String body = "<Body><ResultType>0</ResultType><ResultCode>0</ResultCode><ResultDesc>Process service request successfully.</ResultDesc><QueryDirectDebitMandateResult><DirectDebitMandateInfo><PayeeName>Info Tech Systems</PayeeName><PayerName>ASHENAFI NEGUSSIE TEKLU</PayerName><DirectDebitMandateInfo><PayerReferenceNumber>TH_012</PayerReferenceNumber><AgreedTC>1</AgreedTC><PayeeAccountName/><PayerAccountName/><FirstPaymentDate>20240308</FirstPaymentDate><Frequency>06</Frequency><MandateID>36602</MandateID><ExpiryDate>20250205</ExpiryDate></DirectDebitMandateInfo></DirectDebitMandateInfo><DirectDebitMandateInfo><PayeeName>Info Tech Systems</PayeeName><PayerName>ASHENAFI NEGUSSIE TEKLU</PayerName><DirectDebitMandateInfo><PayerReferenceNumber>INFO_19</PayerReferenceNumber><AgreedTC>1</AgreedTC><PayeeAccountName/><PayerAccountName/><FirstPaymentDate>20240531</FirstPaymentDate><Frequency>06</Frequency><MandateID>43413</MandateID><ExpiryDate>20250205</ExpiryDate></DirectDebitMandateInfo></DirectDebitMandateInfo></QueryDirectDebitMandateResult></Body>";

		System.out.println(body);
		String header = "<Header><Version>1.0</Version><OriginatorConversationID>S_X20130129210987110</OriginatorConversationID><ConversationID>8ZORL6Y2GJ90XQDQK1M1</ConversationID></Header>";


		XmlMapper xmlMapper = new XmlMapper();
		QueryResult result = xmlMapper.readValue(xml, QueryResult.class);
		System.out.println(result.Body.getQueryDirectDebitMandateResult().getDirectDebitMandateInfos().size());
		System.out.println(result.Body.getQueryDirectDebitMandateResult().getDirectDebitMandateInfos().get(1).getDirectDebitMandateInfo().getMandateID());
	}


}
