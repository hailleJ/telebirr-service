package io.alet.telebirr_request.xml.query;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MandateInfo {
    public String PayerReferenceNumber;
    public int AgreedTC;
    public String FirstPaymentDate;
    public int Frequency;
    public String MandateID;
    public String ExpiryDate;
    public String PayeeAccountName;
    public String PayerAccountName;
}
