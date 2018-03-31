package com.net128.common;

import java.math.BigInteger;
import java.util.Arrays;

public class BitOpsBigInteger implements BitOps {
    public long setBitRange(long value, long rangeValue, int pos, int numOfBits) {
        return setBitRange(BigInteger.valueOf(value), BigInteger.valueOf(rangeValue), pos, numOfBits).longValue();
    }
    public BigInteger setBitRange(BigInteger value, BigInteger rangeValue, int pos, int numOfBits) {
        BigInteger mask = bits(numOfBits);
        return (value.and(mask.shiftLeft(pos).not())).or((rangeValue.and(mask)).shiftLeft(pos));
    }

    public long getBitRange(long value, int pos, int numOfBits) {
        return getBitRange(BigInteger.valueOf(value), pos, numOfBits).longValue();
    }
    public BigInteger getBitRange(BigInteger value, int pos, int numOfBits) {
        return (bits(numOfBits).and(value.shiftRight(pos)));
    }

    public long reverseBits(long value, int numOfBits) {
        return reverseBits(BigInteger.valueOf(value), numOfBits).longValue();
    }
    public BigInteger reverseBits(BigInteger value, int numOfBits) {
        int offset=8-(numOfBits%8);
        value=getBitRange(value, 0, numOfBits);
        value=value.shiftLeft(offset);
        byte [] bytes=bigIntegerToBytes(value);
        byte [] revbytes=reverseByteArrayBits(bytes);
        value=bigIntegerFromBytes(revbytes);
        return getBitRange(value, 0, numOfBits);
    }

    public long rotateBits(long value, int numOfBits, int offset) {
        return rotateBits(BigInteger.valueOf(value), numOfBits, offset).longValue();
    }
    public BigInteger rotateBits(BigInteger value, int numOfBits, int offset) {
        if(offset<0) {
            offset=numOfBits+offset;
        }
        BigInteger result = value.shiftRight(offset).or(value.shiftLeft(numOfBits - offset));
        return getBitRange(result, 0, numOfBits);
    }

    public long rotateBitsRight(long value, int numOfBits, int offset) {
        return rotateBitsRight(BigInteger.valueOf(value), numOfBits, offset).longValue();
    }
    public BigInteger rotateBitsRight(BigInteger value, int numOfBits, int offset) {
        return rotateBits(value, numOfBits, offset);
    }

    public long rotateBitsLeft(long value, int numOfBits, int offset) {
        return rotateBitsLeft(BigInteger.valueOf(value), numOfBits, offset).longValue();
    }
    public BigInteger rotateBitsLeft(BigInteger value, int numOfBits, int offset) {
        return rotateBits(value, numOfBits, -offset);
    }

    private BigInteger bits(int numBits) {
        return BigInteger.ONE.shiftLeft(numBits).subtract(BigInteger.ONE);
    }

    private static BigInteger bigIntegerFromBytes(final byte[] bytes) {
        return new BigInteger(1, bytes);
    }

    private static byte[] bigIntegerToBytes(final BigInteger bigInteger) {
        byte[] bytes = bigInteger.toByteArray();
        if (bytes[0] == 0) {
            return Arrays.copyOfRange(bytes, 1, bytes.length);
        }
        return bytes;
    }
}
