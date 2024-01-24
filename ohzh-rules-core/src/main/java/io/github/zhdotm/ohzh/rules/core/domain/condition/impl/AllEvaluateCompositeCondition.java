package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionEnum;
import org.jeasy.rules.api.Facts;

import java.util.Collection;

/**
 * 所有条件都评估
 *
 * @author zhihao.mao
 */


public class AllEvaluateCompositeCondition extends AbstractCompositeCondition {

    public AllEvaluateCompositeCondition(ICondition... conditions) {
        super(conditions);
    }

    @Override
    public boolean evaluate(Facts facts) {
        Collection<ICondition> conditions = super.getConditions();
        //没有条件，则默认成功
        if (CollectionUtil.isEmpty(conditions)) {

            return Boolean.TRUE;
        }

        return conditions.stream()
                .allMatch(condition -> condition.evaluate(facts));
    }

    @Override
    public String getConditionCode() {

        return ConditionEnum.ALL_EVALUATE.getCode();
    }

}
