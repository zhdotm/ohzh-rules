package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import org.jeasy.rules.api.Facts;

import java.math.BigDecimal;

/**
 * 小于等于条件
 *
 * @author zhihao.mao
 */

public interface ILessThanOrEqualToCondition<T> extends ISingleCondition {

    /**
     * 获取小于等于条件字段名称
     *
     * @return 小于等于条件字段名称
     */
    String getLessThanOrEqualToFieldName();

    /**
     * 获取小于等于条件目标值
     *
     * @return 目标值
     */
    T getLessThanOrEqualToTargetValue();

    @Override
    default boolean evaluate(Facts facts) {
        String lessThanOrEqualToFieldName = getLessThanOrEqualToFieldName();
        Object lessThanOrEqualToTargetValue = getLessThanOrEqualToTargetValue();
        Object currentValue = facts.get(lessThanOrEqualToFieldName);
        if (ObjectUtil.isEmpty(currentValue)) {

            return Boolean.FALSE;
        }

        return NumberUtil.isLessOrEqual(new BigDecimal(StrUtil.toString(currentValue)), new BigDecimal(StrUtil.toString(lessThanOrEqualToTargetValue)));
    }

    @Override
    default String getConditionTypeCode() {

        return ConditionTypeEnum.LESS_THAN_OR_EQUAL_TO.getCode();
    }
}
