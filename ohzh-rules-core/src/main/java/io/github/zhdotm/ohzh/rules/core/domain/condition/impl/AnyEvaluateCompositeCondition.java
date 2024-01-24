package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import org.jeasy.rules.api.Facts;

import java.util.Collection;

/**
 * 任意条件评估
 *
 * @author zhihao.mao
 */

public class AnyEvaluateCompositeCondition extends AbstractCompositeCondition {

    public AnyEvaluateCompositeCondition(ICondition... conditions) {
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
                .anyMatch(condition -> condition.evaluate(facts));
    }

    @Override
    public String getConditionTypeCode() {

        return ConditionTypeEnum.ANY_EVALUATE.getCode();
    }

}
