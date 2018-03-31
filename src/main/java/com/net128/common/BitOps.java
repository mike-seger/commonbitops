package com.net128.common;

public interface BitOps {
    long setBitRange(long value, long rangeValue, int pos, int numOfBits);
    long getBitRange(long value, int pos, int numOfBits);
    long reverseBits(long value, int numOfBits);
    long rotateBits(long value, int numOfBits, int offset);
    long rotateBitsRight(long value, int numOfBits, int offset);
    long rotateBitsLeft(long value, int numOfBits, int offset);

    default long getMaxValue(int numOfBits) {
        return (1 << numOfBits)-1;
    }

    default long getBinaryMaxValueForDecimals(int digits) {
        return getMaxValue(getBitPrecisionOfDecimal(digits)-1);
    }

    default int getBitPrecisionOfDecimal(int digits) {
        return (int)Math.ceil(Math.log(Math.pow(10, digits))/Math.log(2));
    }

    default byte [] reverseByteArray(byte[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            byte temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    default byte []  reverseByteArrayBits(byte[] array) {
        array=reverseByteArray(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = (byte)reverseBitsByte(array[i]);
        }
        return array;
    }

    default byte reverseBitsByte(byte x) {
        return (byte)(Integer.reverse(x)>>>24);
    }
}
