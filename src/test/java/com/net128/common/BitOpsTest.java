package com.net128.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public abstract class BitOpsTest {
    protected abstract BitOps bo();

    @DisplayName("Should set bit range")
    @ParameterizedTest(name = "{index} => binValue={0}, binRangeValue={1}, pos={2}, numBits={3}, result={4}")
    @CsvSource({
            "1, 0, 0, 1, 0",
            "1, 1, 0, 1, 1",
            "1, 1, 1, 1, 11",
            "1, 1, 3, 1, 1001",
            "1, 11, 5, 2, 1100001",
            "111111, 0, 2, 1, 111011",
            "111111111111111111111111111111111111111111111111111111111111111, 0, 20, 5, 111111111111111111111111111111111111110000011111111111111111111",
            "111111111111111111111111111111111111111111111111111111111111111, 10110, 20, 5, 111111111111111111111111111111111111111011011111111111111111111",
            "111111, 0, 4, 2, 1111",
            "1111111, 0, 4, 2, 1001111"
    })
    public void shouldSetBitRange(String binValue, String binRangeValue, int pos, int numBits, String result) {
        assertEquals(result,
            toBinary(bo().setBitRange(fromBinary(binValue), fromBinary(binRangeValue), pos, numBits)));
    }

    @DisplayName("Should get bit range")
    @ParameterizedTest(name = "{index} => binValue={0}, pos={1}, numBits={2}, result={3}")
    @CsvSource({
        //does not work correctly with long
        //"111111, -3, 4, 1000",
        //"111111, -2, 3, 100",
        //"111111, -1, 2, 10",
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
        //does not work correctly with long
        //"111111111111111111111111111111111111111111111111111111111111111, 64, 3, 0",
        "111111, 6, 2, 0"
    })
    public void shouldGetBitRange(String binValue, int pos, int numBits, String result) {
        assertEquals(result,
            toBinary(bo().getBitRange(fromBinary(binValue), pos, numBits)));
    }

    @DisplayName("Should reverse bits")
    @ParameterizedTest(name = "{index} => binValue={0}, numBits={1}, result={2}")
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
    public void shouldReverseBits(String binValue, int numBits, String result) {
        assertEquals(result,
            toBinary(new BitOpsLong().reverseBits(fromBinary(binValue), numBits)));
    }

    @DisplayName("Should rotate bits")
    @ParameterizedTest(name = "{index} => binValue={0}, numBits={1}, offset={2}, result={3}")
    @CsvSource({
        "1, 1, 0, 1",
        "10, 2, 0, 10",
        "10, 2, 1, 1",
        "11111, 6, 2, 110111",
        "11111, 6, 4, 111101",
        "110111, 6, -2, 11111",
        "111101, 6, -4, 11111",
        "111110, 6, 2, 101111"
    })
    public void shouldRotateBits(String binValue, int numBits, int offset, String result) {
        assertEquals(result,
            toBinary(bo().rotateBits(fromBinary(binValue), numBits, offset)));
        assertEquals(result,
            toBinary(bo().rotateBitsRight(fromBinary(binValue), numBits, offset)));
        assertEquals(binValue,
            toBinary(bo().rotateBitsLeft(fromBinary(result), numBits, offset)));
    }

    @DisplayName("Should get bit precision of decimal")
    @ParameterizedTest(name = "{index} => digits={0}, result={1}")
    @CsvSource({
            "1, 4",
            "8, 27",
            "9, 30",
            "10, 34",
            "19, 64"
    })
    public void shouldGetBitPrecisionOfDecimal(int digits, int result) {
        assertEquals(result, bo().getBitPrecisionOfDecimal(digits));
    }

    private long fromBinary(String s) {
        return Long.parseLong(s, 2);
    }

    private String toBinary(long value) {
        return Long.toBinaryString(value);
    }
}
