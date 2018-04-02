package com.demo.angularpractice;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

public class UtilTest {

    @Test
    public void testDruidEncryPassword() {
        try {
            System.out.println(ConfigTools.encrypt("root"));
            System.out.println(ConfigTools.decrypt("bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA=="));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
