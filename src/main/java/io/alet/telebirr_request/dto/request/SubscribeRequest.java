package io.alet.telebirr_request.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeRequest {
    String phone_number;
    String application_key;
}
