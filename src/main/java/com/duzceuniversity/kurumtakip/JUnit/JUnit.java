package com.duzceuniversity.kurumtakip.JUnit;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class JUnit {

    @Test
    public void testFindMax() {
        assertEquals(4, Calculation.findMax(new int[]{1, 3, 4, 2}));
        assertEquals(-1, Calculation.findMax(new int[]{-12, -1, -3, -4, -2}));
    }
}