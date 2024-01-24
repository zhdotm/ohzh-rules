package io.github.zhdotm.ohzh.rules.core.domain.action;

import org.jeasy.rules.api.Action;

/**
 * 动作
 *
 * @author zhihao.mao
 */

public interface IAction extends Action {

    /**
     * 获取动作条件
     *
     * @return 动作条件
     */
    String getActionCode();

    /**
     * 优先级
     *
     * @return 优先级
     */
    default int getPriority() {

        return Integer.MAX_VALUE - 1;
    }

}
