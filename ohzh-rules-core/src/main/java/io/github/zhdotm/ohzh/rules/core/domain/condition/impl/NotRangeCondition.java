package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.INotRangeCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 不在范围内条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class NotRangeCondition<T> extends AbstractSingleCondition implements INotRangeCondition<T> {

    @Getter
    private final String notRangeFieldName;

    @Getter
    private final T leftBoundaryTargetValue;

    @Getter
    private final T rightBoundaryTargetValue;

}
