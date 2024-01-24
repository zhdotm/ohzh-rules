package io.github.zhdotm.ohzh.rules.core.domain.action;

/**
 * 单个动作
 *
 * @author zhihao.mao
 */

public interface ISingleAction extends IAction {

    /**
     * 获取返回字段名称
     *
     * @return 返回字段名称
     */
    String getReturnFieldName();

    /**
     * 获取执行返回
     *
     * @return 执行返回
     */
    Object getExecuteReturn();

}
