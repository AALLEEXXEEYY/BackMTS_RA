package api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateOrderRequest {
    private Long userId;
    private Integer tariffId;
}
