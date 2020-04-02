import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class Patient {
    private String name;
    private String surname;
    private BigInteger pesel;
    private Double wallet;
}
