package com.nwt128.common.bitops;

public class BitOps {
    public static long extractBits(long number, int pos, int bits) {
        return (((1 << bits) - 1) & (number >> pos));
    }
}

