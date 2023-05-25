package api.input;

import api.models.GetTariffsResponse;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@ToString
public class TariffsData {
    private GetTariffsResponse data;
}