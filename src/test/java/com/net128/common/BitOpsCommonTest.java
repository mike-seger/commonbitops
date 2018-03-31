package com.net128.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BitOpsCommonTest {
    private BitOps bo=new BitOpsLong();

    @DisplayName("Should reverse byte array")
    @ParameterizedTest(name = "{index} => bytes={0}")
    @MethodSource("byteArrayProvider")
    public void shouldReverseByteArray(byte [] bytes) {
        List<Integer> expected = intStream(bytes).boxed().collect(Collectors.toList());
        Collections.reverse(expected);
        bo.reverseByteArray(bytes);
        List<Integer> actual = intStream(bytes).boxed().collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @DisplayName("Should reverse byte bits")
    @ParameterizedTest(name = "{index} => aByte={0}")
    @MethodSource("allByteValues")
    public void shouldReverseByteBits(int aByte) {
        String expected=new StringBuilder(paddedBinaryString((byte)aByte)).reverse().toString();
        byte reverseByte = bo.reverseBitsByte((byte)aByte);
        String actual=paddedBinaryString(reverseByte);
        assertEquals(expected, actual);
    }
    private static Stream<Arguments> byteArrayProvider() {
        return Stream.of(
                Arguments.of(new byte [] {1}),
                Arguments.of(new byte [] {1, 2}),
                Arguments.of(new byte [] {1, 2, 3}),
                Arguments.of(new byte [] {1, 2, 3, 4})
        );
    }

    @DisplayName("Should get binary max value for digits")
    @ParameterizedTest(name = "{index} => bits={0}, result={1}")
    @MethodSource("binaryMaxValueForDecimals")
    public void shouldGetBinaryMaxValueForDecimals(int decimals, long result) {
        assertEquals(result, bo.getBinaryMaxValueForDecimals(decimals));
    }
    private static Stream<Arguments> binaryMaxValueForDecimals() {
        return Stream.of(
            Arguments.of(1, 7L),
            Arguments.of(3, 511L),
            Arguments.of(8, 67108863L)
         );
    }

    private static IntStream intStream(byte[] array) {
        return IntStream.range(0, array.length).map(i -> array[i]);
    }

    private static Stream<Arguments> allByteValues() {
        return IntStream.range(0, 255).mapToObj(i -> Arguments.of(new Integer(i)));
    }

    private static String paddedBinaryString(byte b) {
        return String.format("%8s", Integer.toBinaryString((b & 0xFF))).replace(' ', '0');
    }
}
