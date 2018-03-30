package om.nwt128.common.bitops;

import static org.junit.jupiter.api.Assertions.*;

import com.nwt128.common.bitops.BitOps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Perform bit operations test")
public class BitOpsTest {
    @DisplayName("Should extract the bits")
    @ParameterizedTest(name = "{index} => number={0}, pos={1}, bits={2}, result={3}")
    @CsvSource({
            "63, -3, 2, 0",
            "63, -2, 2, 0",
            "63, -1, 2, 0",
            "63, 0, 2, 3",
            "63, 4, 2, 3",
            "63, 5, 2, 1",
            "63, 0, 6, 63",
            "63, 0, 7, 63",
            "63, 1, 7, 31",
            "63, 6, 2, 0"
    })
    public void shouldExtractBits(long number, int pos, int bits, long result) {
        assertEquals(result, BitOps.extractBits(number, pos, bits));
    }
}
