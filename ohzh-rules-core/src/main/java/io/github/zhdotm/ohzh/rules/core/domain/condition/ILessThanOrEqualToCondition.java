package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
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

        return NumberUtil.isLessOrEqual(new BigDecimal(StrUtil.toString(currentValue)), new BigDecimal(StrUtil.toString(lessThanOrEqualToTargetValue)));
    }

}
