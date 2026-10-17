package com.mallowwww.hearnoevil.util;

import com.mallowwww.hearnoevil.api.Frequency;
import com.mallowwww.hearnoevil.api.FrequencyBandData;

import java.util.HashMap;

public class FrequencyBandUtil {
    private static final HashMap<Character, FrequencyBandData> BAND_DATA = new HashMap<>();
    static {
        BAND_DATA.put('a', new FrequencyBandData(0, 1, 300, 1));
    }
}
