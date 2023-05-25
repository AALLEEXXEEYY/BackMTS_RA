package api.models;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@ToString
public class GetTariffsResponse {
    private List<Tariff> tariffs;
}