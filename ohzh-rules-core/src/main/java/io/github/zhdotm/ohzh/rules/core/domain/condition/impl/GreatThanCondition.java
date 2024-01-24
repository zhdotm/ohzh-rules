package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.IGreatThanCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 大于等于条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class GreatThanCondition<T> extends AbstractSingleCondition implements IGreatThanCondition<T> {

    @Getter
    private final String greatThanFieldName;

    @Getter
    private final T greatThanTargetValue;

    @Override
    public String getConditionTypeCode() {

        return ConditionTypeEnum.GREAT_THAN.getCode();
    }

}
