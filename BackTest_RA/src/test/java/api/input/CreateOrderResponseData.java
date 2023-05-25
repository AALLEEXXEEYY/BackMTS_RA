package api.input;

import api.models.CreateOrderResponse;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@ToString
public class CreateOrderResponseData {
    private CreateOrderResponse data;
}