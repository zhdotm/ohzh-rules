package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.IGreatThanOrEqualToCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 大于等于条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class GreatThanOrEqualToCondition<T> extends AbstractSingleCondition implements IGreatThanOrEqualToCondition<T> {

    @Getter
    private final String greatThanOrEqualToFieldName;

    @Getter
    private final T greatThanOrEqualToTargetValue;

}
