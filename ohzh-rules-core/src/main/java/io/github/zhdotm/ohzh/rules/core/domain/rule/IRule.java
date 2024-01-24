package io.github.zhdotm.ohzh.rules.core.domain.rule;

import org.jeasy.rules.api.Rule;

import java.util.Map;

/**
 * 规则
 *
 * @author zhihao.mao
 */

public interface IRule extends Rule {

    /**
     * 获取规则编码
     *
     * @return 规则编码
     */
    String getRuleTypeCode();

    @Override
    default String getName() {

        return getRuleTypeCode();
    }

    /**
     * 获取规则应用执行返回
     *
     * @return 规则应用执行返回
     */
    Map<String, Object> getExecuteReturnMap();

    /**
     * 优先级
     *
     * @return 优先级
     */
    @Override
    default int getPriority() {

        return DEFAULT_PRIORITY;
    }

    /**
     * 设置优先级
     *
     * @param priority 优先级
     */
    void setPriority(int priority);

    @Override
    default int compareTo(final Rule rule) {
        if (getPriority() < rule.getPriority()) {
            return -1;
        } else if (getPriority() > rule.getPriority()) {
            return 1;
        } else {
            return getName().compareTo(rule.getName());
        }
    }
}
