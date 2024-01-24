package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.ILessThanOrEqualToCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 小于等于条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class LessThanOrEqualToCondition<T> extends AbstractSingleCondition implements ILessThanOrEqualToCondition<T> {

    @Getter
    private final String lessThanOrEqualToFieldName;

    @Getter
    private final T lessThanOrEqualToTargetValue;

    @Override
    public String getConditionTypeCode() {

        return ConditionTypeEnum.LESS_THAN_OR_EQUAL_TO.getCode();
    }

}
