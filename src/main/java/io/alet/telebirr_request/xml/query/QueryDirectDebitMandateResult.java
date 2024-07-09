package io.alet.telebirr_request.xml.query;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class QueryDirectDebitMandateResult {

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "DirectDebitMandateInfo")
    public List<DirectDebitMandateInfo> DirectDebitMandateInfos;

}