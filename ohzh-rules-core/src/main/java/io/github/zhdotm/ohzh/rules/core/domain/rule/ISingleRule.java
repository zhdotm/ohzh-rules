package io.github.zhdotm.ohzh.rules.core.domain.rule;

import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICondition;
import org.jeasy.rules.api.Facts;

/**
 * 规则
 *
 * @author zhihao.mao
 */

public interface ISingleRule extends IRule {

    /**
     * 获取条件
     *
     * @return 条件
     */
    ICondition getCondition();

    /**
     * 获取动作
     *
     * @return 动作
     */
    IAction getAction();

    @Override
    default boolean evaluate(Facts facts) {

        return getCondition().evaluate(facts);
    }

}
