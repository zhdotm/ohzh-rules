package io.github.zhdotm.ohzh.rules.core.domain.condition;

import org.jeasy.rules.api.Condition;

/**
 * 条件
 *
 * @author zhihao.mao
 */

public interface ICondition extends Condition {

    /**
     * 获取条件编码
     *
     * @return 条件编码
     */
    String getConditionCode();

    /**
     * 优先级
     *
     * @return 优先级
     */
    default int getPriority() {

        return Integer.MAX_VALUE - 1;
    }
}
