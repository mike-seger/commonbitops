package com.net128.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Perform bit operations test")
public class BitOpsTest {
    @DisplayName("Should extract bits")
    @ParameterizedTest(name = "{index} => binNumber={0}, pos={1}, bits={2}, result={3}")
    @CsvSource({
            "111111, -3, 2, 0",
            "111111, -2, 2, 0",
            "111111, -1, 2, 0",
            "111111, 0, 2, 11",
            "111111, 4, 2, 11",
            "111111, 5, 2, 1",
            "111111, 0, 6, 111111",
            "111111, 0, 7, 111111",
            "1111110, 1, 7, 111111",
            "11111100, 2, 7, 111111",
            "111111000, 3, 7, 111111",
            "1111110000, 4, 7, 111111",
            "1111110000, 4, 8, 111111",
            "110111, 2, 3, 101",
            "101111111111111111111111111111111111111111111111111111111111111, 60, 3, 101",
            "111111111111111111111111111111111111111111111111111111111111111, 60, 3, 111",
            "111111111111111111111111111111111111111111111111111111111111111, 61, 3, 11",
            "111111111111111111111111111111111111111111111111111111111111111, 62, 3, 1",
            "111111111111111111111111111111111111111111111111111111111111111, 63, 3, 0",
            "111111111111111111111111111111111111111111111111111111111111111, 63, 1, 0",
            "111111111111111111111111111111111111111111111111111111111111111, 64, 3, 111",
            "111111, 6, 2, 0"
    })
    public void shouldExtractBits(String binNumber, int pos, int bits, String result) {
        assertEquals(result,
            Long.toBinaryString(BitOps.extractBits(Long.parseLong(binNumber,2), pos, bits)));
    }

    @DisplayName("Should reverse all bits")
    @ParameterizedTest(name = "{index} => binNumber={0}, result={1}")
    @CsvSource({
        "11010010,  100101100000000000000000000000000000000000000000000000000000000",
        "11010011, 1100101100000000000000000000000000000000000000000000000000000000"
    })
    public void shouldReverseBits(String binNumber, String result) {
        assertEquals(result,
            Long.toBinaryString(Long.reverse(Long.parseLong(binNumber,2))));
    }

    @DisplayName("Should reverse bits")
    @ParameterizedTest(name = "{index} => binNumber={0}, bits={1}, result={2}")
    @CsvSource({
            "110111, 1, 1",
            "110111, 4, 1110",
            "110111, 6, 111011",
            "110111, 7, 1110110",
            "110111, 8, 11101100",
            "11011101111, 11, 11110111011",
            "101111000000000000000000000000000000000000000000000000000000000, 63, 111101",
            "101111000000000000000000000000000000000000000000000000000000001, 63, 100000000000000000000000000000000000000000000000000000000111101",
            "101111000000000000000000000000000000000000000000000000000000000, 64, 1111010",
            "1101, 4, 1011"
    })
    public void shouldReverseBits(String binNumber, int bits, String result) {
        assertEquals(result,
            Long.toBinaryString(BitOps.reverseBits(Long.parseLong(binNumber,2), bits)));
    }

    @DisplayName("Should rotate bits")
    @ParameterizedTest(name = "{index} => binNumber={0}, bits={1}, offset={2}, result={3}")
    @CsvSource({
            "11111, 6, 2, 110111",
            "11111, 6, 4, 111101",
            "110111, 6, -2, 11111",
            "111101, 6, -4, 11111",
            "111110, 6, 2, 101111"
    })
    public void shouldRotateBits(String binNumber, int bits, int offset, String result) {
        assertEquals(result,
                Long.toBinaryString(BitOps.rotateBitsRight(Long.parseLong(binNumber,2), bits, offset)));
        assertEquals(binNumber,
                Long.toBinaryString(BitOps.rotateBitsLeft(Long.parseLong(result,2), bits, -offset)));
    }
}
