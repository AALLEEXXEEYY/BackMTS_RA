package api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteOrderRequest {
    private Long userId;
    private String orderId;
}
