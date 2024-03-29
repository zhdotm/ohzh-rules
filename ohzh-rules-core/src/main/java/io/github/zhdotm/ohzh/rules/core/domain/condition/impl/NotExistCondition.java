package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.condition.INotExistCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 不存在条件
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public class NotExistCondition extends AbstractSingleCondition implements INotExistCondition {

    @Getter
    private final String notExistFieldName;

    @Getter
    private final List<String> notExistTargetValues;

}
