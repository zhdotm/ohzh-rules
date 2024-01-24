package io.github.zhdotm.ohzh.rules.core.domain.rule.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.github.zhdotm.ohzh.rules.core.domain.enums.RuleTypeEnum;
import io.github.zhdotm.ohzh.rules.core.domain.rule.IRule;
import org.jeasy.rules.api.Facts;

import java.util.Collection;

/**
 * 全部评估通过，全部执行
 *
 * @author zhihao.mao
 */

public class AllEvaluatePassAllExecuteCompositeRule extends AbstractCompositeRule {


    public AllEvaluatePassAllExecuteCompositeRule(IRule... rules) {
        super(rules);
    }

    @Override
    public String getRuleTypeCode() {

        return RuleTypeEnum.ALL_EVALUATE_PASS_ALL_EXECUTE.getCode();
    }

    @Override
    public boolean evaluate(Facts facts) {
        Collection<IRule> rules = getRules();
        if (CollectionUtil.isEmpty(rules)) {

            return Boolean.FALSE;
        }
        for (IRule rule : rules) {
            if (!rule.evaluate(facts)) {

                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public void execute(Facts facts) throws Exception {
        Collection<IRule> rules = getRules();
        if (CollectionUtil.isNotEmpty(rules)) {
            for (IRule rule : rules) {
                rule.execute(facts);

                putRuleExecuteReturn(rule);
            }
        }
    }

}
