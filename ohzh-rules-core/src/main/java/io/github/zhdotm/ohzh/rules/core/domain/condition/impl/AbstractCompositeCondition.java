package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICompositeCondition;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICondition;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * 组合条件
 *
 * @author zhihao.mao
 */

public abstract class AbstractCompositeCondition implements ICompositeCondition {

    @Getter
    private final Collection<ICondition> conditions;

    public AbstractCompositeCondition(ICondition... conditions) {
        this.conditions = new ArrayList<ICondition>() {
            {
                sort(Comparator.comparingInt(ICondition::getPriority));
            }
        };
        if (ArrayUtil.isNotEmpty(conditions)) {
            this.conditions.addAll(Arrays.asList(conditions));
        }
    }

    @Override
    public Boolean addCondition(ICondition condition) {
        if (ObjectUtil.isEmpty(condition)) {

            return Boolean.TRUE;
        }

        return this.conditions.add(condition);
    }

    @Override
    public Boolean addConditions(Collection<ICondition> conditions) {
        if (CollectionUtil.isEmpty(conditions)) {

            return Boolean.TRUE;
        }

        return this.conditions.addAll(conditions);
    }

}
