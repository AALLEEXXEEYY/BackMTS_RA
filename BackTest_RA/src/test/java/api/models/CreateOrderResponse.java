package api.models;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@ToString
public class CreateOrderResponse {
    private String orderId;
}