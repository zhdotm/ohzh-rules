package io.github.zhdotm.ohzh.rules.core.domain.action;

import org.jeasy.rules.api.Action;

/**
 * 动作
 *
 * @author zhihao.mao
 */

public interface IAction extends Action, Comparable<IAction> {

    int DEFAULT_PRIORITY = Integer.MAX_VALUE - 1;

    /**
     * 获取动作条件
     *
     * @return 动作条件
     */
    String getActionTypeCode();

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
    default int compareTo(final IAction action) {
        if (getPriority() < action.getPriority()) {
            return -1;
        } else if (getPriority() > action.getPriority()) {
            return 1;
        } else {
            return getActionTypeCode().compareTo(action.getActionTypeCode());
        }
    }

}
