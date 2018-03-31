package com.net128.common;

import java.math.BigInteger;

public class BitOpsBigInteger implements BitOps {
    public long setBitRange(long value, long rangeValue, int pos, int numOfBits) {
        return setBitRange(BigInteger.valueOf(value), BigInteger.valueOf(rangeValue), pos, numOfBits).longValue();
    }
    public static BigInteger setBitRange(BigInteger value, BigInteger rangeValue, int pos, int numOfBits) {
        BigInteger mask = bits(numOfBits);
        return (value.and(mask.shiftLeft(pos).not())).or((rangeValue.and(mask)).shiftLeft(pos));
    }

    public long getBitRange(long value, int pos, int numOfBits) {
        return getBitRange(BigInteger.valueOf(value), pos, numOfBits).longValue();
    }

    public long reverseBits(long value, int numOfBits) {
        return 0;
    }

    public static BigInteger getBitRange(BigInteger value, int pos, int numOfBits) {
        return (bits(numOfBits).and(value.shiftRight(pos)));
    }

    public long rotateBits(long value, int numOfBits, int offset) {
        return rotateBits(BigInteger.valueOf(value), numOfBits, offset).longValue();
    }
    public static BigInteger rotateBits(BigInteger value, int numOfBits, int offset) {
        if(offset<0) {
            offset=numOfBits+offset;
        }
        BigInteger result = value.shiftRight(offset).or(value.shiftLeft(numOfBits - offset));
        return getBitRange(result, 0, numOfBits);
    }

    public long rotateBitsRight(long value, int numOfBits, int offset) {
        return rotateBits(value, numOfBits, offset);
    }
    public static BigInteger rotateBitsRight(BigInteger value, int numOfBits, int offset) {
        return rotateBits(value, numOfBits, offset);
    }

    public long rotateBitsLeft(long value, int numOfBits, int offset) {
        return rotateBits(value, numOfBits, -offset);
    }
    public static BigInteger rotateBitsLeft(BigInteger value, int numOfBits, int offset) {
        return rotateBits(value, numOfBits, -offset);
    }

    private static BigInteger bits(int numBits) {
        return BigInteger.ONE.shiftLeft(numBits).subtract(BigInteger.ONE);
    }
}
