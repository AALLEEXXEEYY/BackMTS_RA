package api.models;

import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@ToString
public class GetStatusOrder {
    private String orderStatus;
}