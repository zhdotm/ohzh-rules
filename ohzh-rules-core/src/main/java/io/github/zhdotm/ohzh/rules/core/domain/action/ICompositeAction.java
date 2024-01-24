package io.github.zhdotm.ohzh.rules.core.domain.action;

import java.util.Collection;
import java.util.Map;

/**
 * 复合动作
 *
 * @author zhihao.mao
 */

public interface ICompositeAction extends IAction {

    /**
     * 获取执行返回
     *
     * @return 执行返回
     */
    Map<String, Object> getExecuteReturnMap();

    /**
     * 获取动作
     *
     * @return 动作
     */
    Collection<IAction> getActions();

    /**
     * 添加动作
     *
     * @param action 动作
     * @return 成功/失败
     */
    Boolean addAction(IAction action);

    /**
     * 添加动作
     *
     * @param actions 动作
     * @return 成功/失败
     */
    Boolean addActions(Collection<IAction> actions);
}
