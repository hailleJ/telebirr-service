package io.alet.telebirr_request.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallbackResponse {

    String number;
    String conversationID;
    int status;
    String  application_key;
    String message;
    String password;


}
