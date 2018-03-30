package com.net128.common;

public class BitOps {
    private final static int maxBits=Long.bitCount(Long.MAX_VALUE);

    public static long extractBits(long value, int pos, int numOfBits) {
        return (((1 << numOfBits) - 1) & (value >>> pos));
    }

    public static long reverseBits(long value, int numOfBits) {
        return Long.reverse(value) >>> (maxBits-numOfBits+1);
    }

    public static long rotateBits(long value, int numOfBits, int offset) {
        if(offset<0) {
            offset=numOfBits+offset;
        }
        long result = (value >>> offset) | (value << (numOfBits - offset));
        return extractBits(result, 0, numOfBits);
    }

    public static long rotateBitsRight(long value, int numOfBits, int offset) {
        return rotateBits(value, numOfBits, offset);
    }

    public static long rotateBitsLeft(long value, int numOfBits, int offset) {
        return rotateBitsRight(value, numOfBits, offset);
    }
}
