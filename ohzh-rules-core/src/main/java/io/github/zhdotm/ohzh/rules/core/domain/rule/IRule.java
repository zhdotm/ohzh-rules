package io.github.zhdotm.ohzh.rules.core.domain.rule;

import io.github.zhdotm.ohzh.rules.core.domain.action.ICompositeAction;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICompositeCondition;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

import java.util.Map;

/**
 * 规则
 *
 * @author zhihao.mao
 */

public interface IRule extends Rule {

    /**
     * 获取复合条件
     *
     * @return 复合条件
     */
    ICompositeCondition getCompositeCondition();

    /**
     * 获取复合动作
     *
     * @return 复合动作
     */
    ICompositeAction getCompositeAction();

    /**
     * 获取规则编码
     *
     * @return 规则编码
     */
    String getRuleCode();

    @Override
    default String getName() {

        return getRuleCode();
    }

    /**
     * 获取执行返回
     *
     * @return 执行返回
     */
    default Map<String, Object> getExecuteReturnMap() {

        return getCompositeAction().getExecuteReturnMap();
    }

    @Override
    default boolean evaluate(Facts facts) {

        return getCompositeCondition().evaluate(facts);
    }


    @Override
    default void execute(Facts facts) throws Exception {

        getCompositeAction().execute(facts);
    }

    @Override
    default int compareTo(final Rule rule) {
        if (getPriority() < rule.getPriority()) {
            return -1;
        } else if (getPriority() > rule.getPriority()) {
            return 1;
        } else {
            return getName().compareTo(rule.getName());
        }
    }
}
