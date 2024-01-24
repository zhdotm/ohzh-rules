package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import org.jeasy.rules.api.Facts;

import java.util.List;

/**
 * Exist条件
 *
 * @author zhihao.mao
 */

public interface IExistCondition extends ISingleCondition {

    /**
     * 获取Exist条件字段名称
     *
     * @return Exist条件字段名称
     */
    String getExistFieldName();

    /**
     * 获取Exist条件目标值
     *
     * @return 目标值
     */
    List<String> getExistTargetValues();

    @Override
    default boolean evaluate(Facts facts) {
        String existFieldName = getExistFieldName();
        List<String> existTargetValues = getExistTargetValues();
        String currentValue = StrUtil.toString(facts.get(existFieldName));

        return CollectionUtil.contains(existTargetValues, currentValue);
    }

    @Override
    default String getConditionTypeCode() {

        return ConditionTypeEnum.EXIST.getCode();
    }
}
