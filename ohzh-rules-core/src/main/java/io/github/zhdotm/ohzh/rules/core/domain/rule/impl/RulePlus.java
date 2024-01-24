package io.github.zhdotm.ohzh.rules.core.domain.rule.impl;

import io.github.zhdotm.ohzh.rules.core.domain.action.ICompositeAction;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICompositeCondition;
import io.github.zhdotm.ohzh.rules.core.domain.rule.IRule;
import lombok.Getter;
import org.jeasy.rules.api.Rule;

import java.util.Optional;

/**
 * 默认规则
 *
 * @author zhihao.mao
 */

public class RulePlus implements IRule {

    @Getter
    private final String ruleCode;

    @Getter
    private final int priority;

    @Getter
    private final ICompositeCondition compositeCondition;

    @Getter
    private final ICompositeAction compositeAction;

    @Getter
    private final String description;

    public RulePlus(String ruleCode,
                    String description,
                    Integer priority,
                    ICompositeCondition compositeCondition,
                    ICompositeAction compositeAction) {
        this.ruleCode = ruleCode;
        this.description = Optional.ofNullable(description).orElse(Rule.DEFAULT_DESCRIPTION);
        this.priority = Optional.ofNullable(priority).orElse(Rule.DEFAULT_PRIORITY);
        this.compositeCondition = compositeCondition;
        this.compositeAction = compositeAction;
    }
}
