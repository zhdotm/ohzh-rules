package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.INotEqualToCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 不等于条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class NotEqualToCondition<T> implements INotEqualToCondition<T> {

    @Getter
    private final String notEqualToFieldName;

    @Getter
    private final T notEqualToTargetValue;

    @Override
    public String getConditionCode() {

        return ConditionEnum.NOT_EQUAL_TO.getCode();
    }
}
