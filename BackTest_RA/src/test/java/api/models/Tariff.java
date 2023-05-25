package api.models;

import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@ToString
public class Tariff {
    private Integer id;
    private String type;
    private String interestRate;
}
