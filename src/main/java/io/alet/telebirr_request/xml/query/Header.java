package io.alet.telebirr_request.xml.query;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "Header")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Header {
    public double Version;
    public String OriginatorConversationID;
    public String ConversationID;
}