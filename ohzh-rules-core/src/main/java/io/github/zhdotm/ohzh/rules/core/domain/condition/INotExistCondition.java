package io.github.zhdotm.ohzh.rules.core.domain.condition;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ConditionTypeEnum;
import org.jeasy.rules.api.Facts;

import java.util.List;

/**
 * NotExist条件
 *
 * @author zhihao.mao
 */

public interface INotExistCondition extends ISingleCondition {

    /**
     * 获取NotExist条件字段名称
     *
     * @return NotExist条件字段名称
     */
    String getNotExistFieldName();

    /**
     * 获取NotExist条件目标值
     *
     * @return 目标值
     */
    List<String> getNotExistTargetValues();

    @Override
    default boolean evaluate(Facts facts) {
        String notExistFieldName = getNotExistFieldName();
        List<String> notExistTargetValues = getNotExistTargetValues();
        String currentValue = StrUtil.toString(facts.get(notExistFieldName));

        return !CollectionUtil.contains(notExistTargetValues, currentValue);
    }

    @Override
    default String getConditionTypeCode() {

        return ConditionTypeEnum.NOT_EXIST.getCode();
    }
}
