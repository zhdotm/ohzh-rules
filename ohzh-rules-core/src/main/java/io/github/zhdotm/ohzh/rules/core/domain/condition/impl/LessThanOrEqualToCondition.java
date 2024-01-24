package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.ILessThanOrEqualToCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 小于等于条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class LessThanOrEqualToCondition<T> implements ILessThanOrEqualToCondition<T> {

    @Getter
    private final String lessThanOrEqualToFieldName;

    @Getter
    private final T lessThanOrEqualToTargetValue;

    @Override
    public String getConditionCode() {

        return ConditionEnum.LESS_THAN_OR_EQUAL_TO.getCode();
    }

}
