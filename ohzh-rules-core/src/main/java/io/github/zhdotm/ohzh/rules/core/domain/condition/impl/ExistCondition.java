package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.IExistCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 存在条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class ExistCondition implements IExistCondition {

    @Getter
    private final String existFieldName;

    @Getter
    private final List<String> existTargetValues;

    @Override
    public String getConditionCode() {

        return ConditionEnum.EXIST.getCode();
    }
}
