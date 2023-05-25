package api.input;
import api.models.Error;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@ToString
public class ErrorError {
    private Error error;
}
