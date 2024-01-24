package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.jeasy.rules.api.Facts;

import java.math.BigDecimal;

/**
 * 大于等于条件
 *
 * @author zhihao.mao
 */

public interface IGreatThanOrEqualToCondition<T> extends ICondition {

    /**
     * 获取大于等于条件字段名称
     *
     * @return 大于条件字段名称
     */
    String getGreatThanOrEqualToFieldName();

    /**
     * 获取大于等于条件目标值
     *
     * @return 目标值
     */
    T getGreatThanOrEqualToTargetValue();

    @Override
    default boolean evaluate(Facts facts) {
        String greatThanOrEqualToFieldName = getGreatThanOrEqualToFieldName();
        Object greatThanOrEqualToTargetValue = getGreatThanOrEqualToTargetValue();
        Object currentValue = facts.get(greatThanOrEqualToFieldName);

        return NumberUtil.isGreaterOrEqual(new BigDecimal(StrUtil.toString(currentValue)), new BigDecimal(StrUtil.toString(greatThanOrEqualToTargetValue)));
    }

}
