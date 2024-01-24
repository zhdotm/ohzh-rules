package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.ILessThanOrEqualToCondition;
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

}
