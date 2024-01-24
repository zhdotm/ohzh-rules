package io.github.zhdotm.ohzh.rules.core.domain.rule.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.github.zhdotm.ohzh.rules.core.domain.enums.RuleTypeEnum;
import io.github.zhdotm.ohzh.rules.core.domain.rule.IRule;
import lombok.Getter;
import org.jeasy.rules.api.Facts;

import java.util.Collection;
import java.util.TreeSet;

/**
 * 哪个评估通过的规则，就执行哪个
 *
 * @author zhihao.mao
 */

public class WhichEvaluatePassWhichExecuteCompositeRule extends AbstractCompositeRule {

    @Getter
    private final Collection<IRule> selectedRules;

    public WhichEvaluatePassWhichExecuteCompositeRule(IRule... rules) {
        super(rules);
        this.selectedRules = new TreeSet<>();
    }

    @Override
    public String getRuleTypeCode() {

        return RuleTypeEnum.WHICH_EVALUATE_PASS_WHICH_EXECUTE.getCode();
    }

    @Override
    public boolean evaluate(Facts facts) {
        Collection<IRule> rules = getRules();
        if (CollectionUtil.isEmpty(rules)) {

            return Boolean.FALSE;
        }
        for (IRule rule : rules) {
            if (rule.evaluate(facts)) {
                selectedRules.add(rule);
            }
        }

        return CollectionUtil.isNotEmpty(selectedRules);
    }

    @Override
    public void execute(Facts facts) throws Exception {
        if (CollectionUtil.isNotEmpty(selectedRules)) {
            for (IRule selectedRule : selectedRules) {
                selectedRule.execute(facts);

                putRuleExecuteReturn(selectedRule);
            }
        }
    }

}
