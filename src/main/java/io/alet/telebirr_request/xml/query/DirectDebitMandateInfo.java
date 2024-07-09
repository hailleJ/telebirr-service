package io.alet.telebirr_request.xml.query;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DirectDebitMandateInfo {
    public String PayeeName;
    public String PayerName;
    public MandateInfo DirectDebitMandateInfo;

}