package io.github.zhdotm.ohzh.rules.core.domain.rule.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import io.github.zhdotm.ohzh.rules.core.domain.rule.IRule;
import io.github.zhdotm.ohzh.rules.core.enums.RuleTypeEnum;
import lombok.Getter;
import org.jeasy.rules.api.Facts;

import java.util.Collection;

/**
 * 执行第一个评估通过的规则
 * 触发第一个适用的规则并忽略组中的其他规则（XOR 逻辑）
 *
 * @author zhihao.mao
 * @see org.jeasy.rules.support.composite.ActivationRuleGroup
 */

public class FirstEvaluatePassFirstExecuteCompositeRule extends AbstractCompositeRule {

    @Getter
    private IRule selectedRule;

    public FirstEvaluatePassFirstExecuteCompositeRule(IRule... rules) {
        super(rules);
    }

    @Override
    public String getRuleTypeCode() {

        return RuleTypeEnum.FIRST_EVALUATE_PASS_FIRST_EXECUTE.getCode();
    }

    @Override
    public boolean evaluate(Facts facts) {
        Collection<IRule> rules = getRules();
        if (CollectionUtil.isEmpty(rules)) {

            return Boolean.FALSE;
        }
        for (IRule rule : rules) {
            if (rule.evaluate(facts)) {
                selectedRule = rule;
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(Facts facts) throws Exception {
        if (ObjectUtil.isNotEmpty(selectedRule)) {
            selectedRule.execute(facts);

            putRuleExecuteReturn(selectedRule);
        }
    }

}
