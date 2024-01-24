package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.IRangeCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 范围条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class RangeCondition<T> implements IRangeCondition<T> {

    @Getter
    private final String rangeFieldName;

    @Getter
    private final T leftBoundaryTargetValue;

    @Getter
    private final T rightBoundaryTargetValue;

    @Override
    public String getConditionCode() {

        return ConditionEnum.RANGE.getCode();
    }
}
