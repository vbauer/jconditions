package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.core.junit.ConditionRule;
import org.junit.Rule;

/**
 * @author Vladislav Bauer
 */

public class RuleAnnotationsTest extends AbstractAnnotationsTest {

    @Rule
    public final ConditionRule rule = new ConditionRule();

}
