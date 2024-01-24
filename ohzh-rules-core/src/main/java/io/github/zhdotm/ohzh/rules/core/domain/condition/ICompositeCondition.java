package io.github.zhdotm.ohzh.rules.core.domain.condition;

import java.util.Collection;

/**
 * 复合条件
 *
 * @author zhihao.mao
 */

public interface ICompositeCondition extends ICondition {

    /**
     * 获取条件
     *
     * @return 条件
     */
    Collection<ICondition> getConditions();

    /**
     * 添加条件
     *
     * @param condition 条件
     * @return 成功/失败
     */
    Boolean addCondition(ICondition condition);

    /**
     * 添加条件
     *
     * @param conditions 条件
     * @return 成功/失败
     */
    Boolean addConditions(Collection<ICondition> conditions);

}
