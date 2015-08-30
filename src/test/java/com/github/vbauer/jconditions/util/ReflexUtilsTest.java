package com.github.vbauer.jconditions.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vladislav Bauer
 */

public class ReflexUtilsTest {

    @Test
    public void testGetFieldNegative() {
        Assert.assertNull(ReflexUtils.getFieldValue(null, null));
    }

}
