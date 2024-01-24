package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.rules.core.enums.ConditionTypeEnum;
import org.jeasy.rules.api.Facts;

import java.math.BigDecimal;

/**
 * 不在范围内条件（不包含边界）
 *
 * @author zhihao.mao
 */

public interface INotRangeCondition<T> extends ISingleCondition {

    /**
     * 获取不在范围内条件字段名称
     *
     * @return 不在范围内条件字段名称
     */
    String getNotRangeFieldName();

    /**
     * 获取左边界条件目标值
     *
     * @return 目标值
     */
    T getLeftBoundaryTargetValue();

    /**
     * 获取右边界条件目标值
     *
     * @return 目标值
     */
    T getRightBoundaryTargetValue();

    @Override
    default boolean evaluate(Facts facts) {
        BigDecimal leftBoundaryTargetValue = new BigDecimal(StrUtil.toString(getLeftBoundaryTargetValue()));
        BigDecimal rightBoundaryTargetValue = new BigDecimal(StrUtil.toString(getRightBoundaryTargetValue()));
        String notRangeFieldName = getNotRangeFieldName();
        if (ObjectUtil.isEmpty(facts.get(notRangeFieldName))) {

            return Boolean.FALSE;
        }
        BigDecimal currentValue = new BigDecimal(StrUtil.toString(facts.get(notRangeFieldName)));

        return !(NumberUtil.isGreaterOrEqual(currentValue, leftBoundaryTargetValue)
                && NumberUtil.isLessOrEqual(currentValue, rightBoundaryTargetValue));
    }

    @Override
    default String getConditionTypeCode() {

        return ConditionTypeEnum.NOT_RANGE.getCode();
    }
}
