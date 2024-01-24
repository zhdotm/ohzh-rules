package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.IEqualToCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 等于条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class EqualToCondition<T> implements IEqualToCondition<T> {

    @Getter
    private final String equalToFieldName;

    @Getter
    private final T equalToTargetValue;

    @Override
    public String getConditionCode() {

        return ConditionEnum.EQUAL_TO.getCode();
    }
}
