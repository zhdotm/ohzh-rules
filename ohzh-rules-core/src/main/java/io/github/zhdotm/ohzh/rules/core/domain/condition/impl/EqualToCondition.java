package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.IEqualToCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 等于条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class EqualToCondition<T> extends AbstractSingleCondition implements IEqualToCondition<T> {

    @Getter
    private final String equalToFieldName;

    @Getter
    private final T equalToTargetValue;

    @Override
    public String getConditionTypeCode() {

        return ConditionTypeEnum.EQUAL_TO.getCode();
    }
}
