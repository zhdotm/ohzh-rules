package io.github.zhdotm.ohzh.rules.core.domain.rule.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.rule.ICompositeRule;
import io.github.zhdotm.ohzh.rules.core.domain.rule.IRule;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

/**
 * 复合规则（抽象）
 *
 * @author zhihao.mao
 */

public abstract class AbstractCompositeRule implements ICompositeRule {

    @Getter
    private final Map<String, Object> executeReturnMap;

    @Getter
    private final Collection<IRule> rules;

    @Getter
    @Setter
    private int priority = IAction.DEFAULT_PRIORITY;

    public AbstractCompositeRule(IRule... rules) {
        this.rules = new TreeSet<>();
        if (ArrayUtil.isNotEmpty(rules)) {
            this.rules.addAll(Arrays.asList(rules));
        }

        this.executeReturnMap = MapUtil.newHashMap();
    }

    public void putRuleExecuteReturn(IRule rule) {
        Map<String, Object> ruleExecuteReturnMap = rule.getExecuteReturnMap();
        if (CollectionUtil.isNotEmpty(ruleExecuteReturnMap)) {

            executeReturnMap.putAll(ruleExecuteReturnMap);
        }
    }

    /**
     * 添加规则
     *
     * @param rule 规则
     * @return 成功/失败
     */
    @Override
    public Boolean addRule(IRule rule) {
        if (ObjectUtil.isEmpty(rule)) {

            return Boolean.TRUE;
        }

        return this.rules.add(rule);
    }

    /**
     * 添加规则
     *
     * @param rules 规则
     * @return 成功/失败
     */
    @Override
    public Boolean addRules(Collection<IRule> rules) {
        if (CollectionUtil.isEmpty(rules)) {

            return Boolean.TRUE;
        }

        return this.rules.addAll(rules);
    }

}
