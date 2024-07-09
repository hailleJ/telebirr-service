package io.alet.telebirr_request.xml.query;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class Body {
    public int ResultType;
    public int ResultCode;
    public String ResultDesc;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "QueryDirectDebitMandateResult")
    QueryDirectDebitMandateResult queryDirectDebitMandateResult;
}
