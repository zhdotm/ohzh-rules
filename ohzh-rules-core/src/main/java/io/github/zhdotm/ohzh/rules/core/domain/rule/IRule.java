package io.github.zhdotm.ohzh.rules.core.domain.rule;

import org.jeasy.rules.api.Rule;

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
    String getRuleCode();

    @Override
    default String getName() {

        return getRuleCode();
    }

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
