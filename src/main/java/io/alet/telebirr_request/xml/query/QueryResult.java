package io.alet.telebirr_request.xml.query;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class QueryResult {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Header")
    public Header Header;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Body")
    public Body Body;
}
