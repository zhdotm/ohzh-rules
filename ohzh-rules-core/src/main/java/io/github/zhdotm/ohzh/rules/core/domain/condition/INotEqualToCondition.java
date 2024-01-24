package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.util.ObjectUtil;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import org.jeasy.rules.api.Facts;

/**
 * 不等于条件
 *
 * @author zhihao.mao
 */

public interface INotEqualToCondition<T> extends ISingleCondition {

    /**
     * 获取不等于条件字段名称
     *
     * @return 不等于条件字段名称
     */
    String getNotEqualToFieldName();

    /**
     * 获取不等于条件目标值
     *
     * @return 目标值
     */
    T getNotEqualToTargetValue();

    @Override
    default boolean evaluate(Facts facts) {
        String notEqualToFieldName = getNotEqualToFieldName();
        Object notEqualToTargetValue = getNotEqualToTargetValue();
        Object currentValue = facts.get(notEqualToFieldName);

        return !ObjectUtil.equal(currentValue, notEqualToTargetValue);
    }

    @Override
    default String getConditionTypeCode() {

        return ConditionTypeEnum.NOT_EQUAL_TO.getCode();
    }
}
