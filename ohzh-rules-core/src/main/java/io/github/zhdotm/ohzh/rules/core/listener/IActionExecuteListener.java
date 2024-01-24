package io.github.zhdotm.ohzh.rules.core.listener;

import io.github.zhdotm.ohzh.rules.core.domain.rule.ISingleRule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.RuleListener;

/**
 * 动作执行监听器
 *
 * @author zhihao.mao
 */

public interface IActionExecuteListener extends RuleListener {

    void doBeforeExecute(ISingleRule rule, Facts facts);

    /**
     * 执行之前
     *
     * @param rule  规则
     * @param facts 参数
     */
    @Override
    default void beforeExecute(Rule rule, Facts facts) {
        if (rule instanceof ISingleRule) {

            doBeforeExecute((ISingleRule) rule, facts);
        }
    }

    void doOnSuccess(ISingleRule rule, Facts facts);

    /**
     * 执行成功
     *
     * @param rule  规则
     * @param facts 参数
     */
    @Override
    default void onSuccess(Rule rule, Facts facts) {
        if (rule instanceof ISingleRule) {

            doOnSuccess((ISingleRule) rule, facts);
        }
    }

    void doOnFailure(ISingleRule rule, Facts facts, Exception exception);

    /**
     * 执行失败
     *
     * @param rule      规则
     * @param facts     参数
     * @param exception 异常
     */
    @Override
    default void onFailure(Rule rule, Facts facts, Exception exception) {
        if (rule instanceof ISingleRule) {

            doOnFailure((ISingleRule) rule, facts, exception);
        }
    }

}
