package io.alet.telebirr_request.dto.callback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionCallbackDTO {
    String conversationId;
    String resultCode;
    String resultDesc;
    String resultType;
}
