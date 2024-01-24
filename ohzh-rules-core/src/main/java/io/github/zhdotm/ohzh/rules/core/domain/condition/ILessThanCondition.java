package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.rules.core.enums.ConditionTypeEnum;
import org.jeasy.rules.api.Facts;

import java.math.BigDecimal;

/**
 * 小于条件
 *
 * @author zhihao.mao
 */

public interface ILessThanCondition<T> extends ISingleCondition {

    /**
     * 获取小于条件字段名称
     *
     * @return 小于条件字段名称
     */
    String getLessThanFieldName();

    /**
     * 获取小于条件目标值
     *
     * @return 目标值
     */
    T getLessThanTargetValue();

    @Override
    default boolean evaluate(Facts facts) {
        String lessThanFieldName = getLessThanFieldName();
        Object lessThanTargetValue = getLessThanTargetValue();
        Object currentValue = facts.get(lessThanFieldName);
        if (ObjectUtil.isEmpty(currentValue)) {

            return Boolean.FALSE;
        }

        return NumberUtil.isLess(new BigDecimal(StrUtil.toString(currentValue)), new BigDecimal(StrUtil.toString(lessThanTargetValue)));
    }

    @Override
    default String getConditionTypeCode() {

        return ConditionTypeEnum.LESS_THAN.getCode();
    }

}
