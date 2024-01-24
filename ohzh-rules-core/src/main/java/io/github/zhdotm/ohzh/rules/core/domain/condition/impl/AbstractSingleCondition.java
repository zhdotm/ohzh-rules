package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ISingleCondition;
import lombok.Getter;
import lombok.Setter;

/**
 * 单个条件（抽象）
 *
 * @author zhihao.mao
 */

public abstract class AbstractSingleCondition implements ISingleCondition {

    @Getter
    @Setter
    private int priority = IAction.DEFAULT_PRIORITY;

}
