package com.github.vbauer.jconditions.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vladislav Bauer
 */

public class InOutUtilsTest {

    @Test
    public void testCloseNegative() {
        Assert.assertFalse(InOutUtils.closeQuietly(null));
    }

}
