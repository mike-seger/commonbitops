package com.net128.common;

public class BitOpsLong implements BitOps {
    private final static int maxBits=Long.bitCount(Long.MAX_VALUE);

    public long setBitRange(long value, long rangeValue, int pos, int numOfBits) {
        long mask = ((1L << numOfBits) - 1);
        return (value  & ~(mask << pos)) | ((rangeValue & mask) << pos);
    }

    public long getBitRange(long value, int pos, int numOfBits) {
        return (((1 << numOfBits) - 1) & (value >>> pos));
    }

    public long reverseBits(long value, int numOfBits) {
        return Long.reverse(value) >>> (maxBits-numOfBits+1);
    }

    public long rotateBits(long value, int numOfBits, int offset) {
        if(offset<0) {
            offset=numOfBits+offset;
        }
        long result = (value >>> offset) | (value << (numOfBits - offset));
        return getBitRange(result, 0, numOfBits);
    }

    public long rotateBitsRight(long value, int numOfBits, int offset) {
        return rotateBits(value, numOfBits, offset);
    }

    public long rotateBitsLeft(long value, int numOfBits, int offset) {
        return rotateBits(value, numOfBits, -offset);
    }
}
