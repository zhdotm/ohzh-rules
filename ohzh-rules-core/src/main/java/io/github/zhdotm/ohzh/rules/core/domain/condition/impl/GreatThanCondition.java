package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.IGreatThanCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 大于等于条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class GreatThanCondition<T> implements IGreatThanCondition<T> {

    @Getter
    private final String greatThanFieldName;

    @Getter
    private final T greatThanTargetValue;

    @Override
    public String getConditionCode() {

        return ConditionEnum.GREAT_THAN.getCode();
    }

}
