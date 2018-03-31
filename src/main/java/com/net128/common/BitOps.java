package com.net128.common;

public interface BitOps {
    long setBitRange(long value, long rangeValue, int pos, int numOfBits);
    long getBitRange(long value, int pos, int numOfBits);
    long reverseBits(long value, int numOfBits);
    long rotateBits(long value, int numOfBits, int offset);
    long rotateBitsRight(long value, int numOfBits, int offset);
    long rotateBitsLeft(long value, int numOfBits, int offset);
    default int getBitPrecisionOfDecimal(int digits) {
        return (int)Math.ceil(Math.log(Math.pow(10, digits))/Math.log(2));
    }
}
