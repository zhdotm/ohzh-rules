package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.IRangeCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 范围条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class RangeCondition<T> extends AbstractSingleCondition implements IRangeCondition<T> {

    @Getter
    private final String rangeFieldName;

    @Getter
    private final T leftBoundaryTargetValue;

    @Getter
    private final T rightBoundaryTargetValue;

}
