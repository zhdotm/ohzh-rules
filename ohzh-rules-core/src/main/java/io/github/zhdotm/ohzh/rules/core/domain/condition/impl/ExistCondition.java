package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.IExistCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 存在条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class ExistCondition extends AbstractSingleCondition implements IExistCondition {

    @Getter
    private final String existFieldName;

    @Getter
    private final List<String> existTargetValues;

    @Override
    public String getConditionTypeCode() {

        return ConditionTypeEnum.EXIST.getCode();
    }
}
