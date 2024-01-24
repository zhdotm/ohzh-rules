package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.INotEqualToCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 不等于条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class NotEqualToCondition<T> extends AbstractSingleCondition implements INotEqualToCondition<T> {

    @Getter
    private final String notEqualToFieldName;

    @Getter
    private final T notEqualToTargetValue;

}
