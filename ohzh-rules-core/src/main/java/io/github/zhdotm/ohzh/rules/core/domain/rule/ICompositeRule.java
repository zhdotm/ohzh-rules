package io.github.zhdotm.ohzh.rules.core.domain.rule;

import java.util.Collection;

/**
 * 复合规则
 *
 * @author zhihao.mao
 */

public interface ICompositeRule extends IRule {

    /**
     * 获取规则
     *
     * @return 规则
     */
    Collection<IRule> getRules();

    /**
     * 添加规则
     *
     * @param rule 规则
     * @return 成功/失败
     */
    Boolean addRule(IRule rule);

    /**
     * 添加规则
     *
     * @param rules 规则
     * @return 成功/失败
     */
    Boolean addRules(Collection<IRule> rules);
}
