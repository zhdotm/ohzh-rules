package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.INotExistCondition;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 不存在条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class NotExistCondition implements INotExistCondition {

    @Getter
    private final String notExistFieldName;

    @Getter
    private final List<String> notExistTargetValues;

    @Override
    public String getConditionCode() {

        return ConditionEnum.NOT_EXIST.getCode();
    }
}
