package com.github.vbauer.jconditions;

import com.github.vbauer.jconditions.core.junit.ConditionIgnoreRule;
import org.junit.Rule;

/**
 * @author Vladislav Bauer
 */

public class RuleAnnotationsTest extends AbstractAnnotationsTest {

    @Rule
    public final ConditionIgnoreRule rule = new ConditionIgnoreRule();

}
