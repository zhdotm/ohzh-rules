package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.util.ObjectUtil;
import org.jeasy.rules.api.Facts;

/**
 * 等于条件
 *
 * @author zhihao.mao
 */

public interface IEqualToCondition<T> extends ISingleCondition {

    /**
     * 获取等于条件字段名称
     *
     * @return 等于条件字段名称
     */
    String getEqualToFieldName();

    /**
     * 获取等于条件目标值
     *
     * @return 目标值
     */
    T getEqualToTargetValue();

    @Override
    default boolean evaluate(Facts facts) {
        String equalToFieldName = getEqualToFieldName();
        Object equalToTargetValue = getEqualToTargetValue();
        Object currentValue = facts.get(equalToFieldName);

        return ObjectUtil.equal(currentValue, equalToTargetValue);
    }

}
