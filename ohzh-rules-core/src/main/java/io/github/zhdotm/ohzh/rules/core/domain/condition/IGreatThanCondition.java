package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import org.jeasy.rules.api.Facts;

import java.math.BigDecimal;

/**
 * 大于条件
 *
 * @author zhihao.mao
 */

public interface IGreatThanCondition<T> extends ISingleCondition {

    /**
     * 获取大于条件字段名称
     *
     * @return 大于条件字段名称
     */
    String getGreatThanFieldName();

    /**
     * 获取大于条件目标值
     *
     * @return 目标值
     */
    T getGreatThanTargetValue();

    @Override
    default boolean evaluate(Facts facts) {
        String greatThanFieldName = getGreatThanFieldName();
        Object greatThanTargetValue = getGreatThanTargetValue();
        Object currentValue = facts.get(greatThanFieldName);
        if (ObjectUtil.isEmpty(currentValue)) {

            return Boolean.FALSE;
        }

        return NumberUtil.isGreater(new BigDecimal(StrUtil.toString(currentValue)), new BigDecimal(StrUtil.toString(greatThanTargetValue)));
    }

    @Override
    default String getConditionTypeCode() {

        return ConditionTypeEnum.GREAT_THAN.getCode();
    }

}
