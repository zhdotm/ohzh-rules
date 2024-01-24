package io.github.zhdotm.ohzh.rules.core.domain.rule.impl;

import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICondition;

/**
 * 单个规则
 *
 * @author zhihao.mao
 */

public class SingleRule extends AbstractSingleRule {

    public SingleRule(String ruleTypeCode,
                      String description,
                      ICondition condition,
                      IAction action) {
        super(ruleTypeCode, description, condition, action);
    }

}
