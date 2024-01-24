package io.github.zhdotm.ohzh.rules.core.domain.condition;

import org.jeasy.rules.api.Condition;

/**
 * 条件
 *
 * @author zhihao.mao
 */

public interface ICondition extends Condition, Comparable<ICondition> {

    int DEFAULT_PRIORITY = Integer.MAX_VALUE - 1;

    /**
     * 获取条件类型编码
     *
     * @return 条件类型编码
     */
    String getConditionTypeCode();

    /**
     * 优先级
     *
     * @return 优先级
     */
    default int getPriority() {

        return DEFAULT_PRIORITY;
    }

    /**
     * 设置优先级
     *
     * @param priority 优先级
     */
    void setPriority(int priority);

    @Override
    default int compareTo(final ICondition condition) {
        if (getPriority() < condition.getPriority()) {
            return -1;
        } else if (getPriority() > condition.getPriority()) {
            return 1;
        } else {
            return getConditionTypeCode().compareTo(condition.getConditionTypeCode());
        }
    }

}
