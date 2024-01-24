package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.ILessThanCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 小于
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class LessThanCondition<T> implements ILessThanCondition<T> {

    @Getter
    private final String lessThanFieldName;

    @Getter
    private final T lessThanTargetValue;

    @Override
    public String getConditionCode() {

        return ConditionEnum.LESS_THAN.getCode();
    }
}
