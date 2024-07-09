package io.alet.telebirr_request.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.alet.telebirr_request.xml.query.QueryResult;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Xml2Java {

    public static QueryResult queryMandateResult(String xml) throws JsonProcessingException {
        xml = optimize(xml);
        XmlMapper xmlMapper = new XmlMapper();
        QueryResult queryResult = xmlMapper.readValue(xml, QueryResult.class);
        log.info("query mandate result :: {}", queryResult);
        return queryResult;

    }
    public static String optimize(String xml) {
        log.info(xml);
        return xml.replace("</soapenv:Body></soapenv:Envelope>","").replace("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body>","").replace(" xmlns:api=\"http://cps.huawei.com/cpsinterface/api_resultmgr\" xmlns:res=\"http://cps.huawei.com/cpsinterface/result\" xmlns:com=\"http://cps.huawei.com/cpsinterface/common\"","")
                .replace("api:","").replace("com:","").replace("res:","");
    }

}
