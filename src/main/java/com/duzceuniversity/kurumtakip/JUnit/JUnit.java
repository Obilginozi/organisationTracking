package com.duzceuniversity.kurumtakip.JUnit;

import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class JUnit {
    @Test
    public void testFindMax(List<Staff> staffList) {
        assertEquals(0, Calculation.findMax(staffList));
        Logger logger = LoggerFactory.getLogger(JUnit.class);
        logger.info("Uniq @Test with JUnit is Succesfully Complete.");
    }
}