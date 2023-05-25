package api.input;

import api.models.GetStatusOrder;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@ToString
public class GetStatusOrderData {
    private GetStatusOrder data;
}