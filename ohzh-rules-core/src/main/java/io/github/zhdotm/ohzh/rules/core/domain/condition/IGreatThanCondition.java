package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.jeasy.rules.api.Facts;

import java.math.BigDecimal;

/**
 * 大于条件
 *
 * @author zhihao.mao
 */

public interface IGreatThanCondition<T> extends ICondition {

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

        return NumberUtil.isGreater(new BigDecimal(StrUtil.toString(currentValue)), new BigDecimal(StrUtil.toString(greatThanTargetValue)));
    }

}
