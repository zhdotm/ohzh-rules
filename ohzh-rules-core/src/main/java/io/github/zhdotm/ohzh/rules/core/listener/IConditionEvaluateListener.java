package io.github.zhdotm.ohzh.rules.core.listener;

import io.github.zhdotm.ohzh.rules.core.domain.rule.IRule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.RuleListener;

/**
 * 条件判断监听器
 *
 * @author zhihao.mao
 */

public interface IConditionEvaluateListener extends RuleListener {

    boolean doBeforeEvaluate(IRule rule, Facts facts);

    /**
     * 该方法在执行Condition判断之前执行。
     * 该方法返回false则不执行条件的判断，直接跳过该当前rule。
     *
     * @param rule  规则
     * @param facts 参数
     * @return 成功/失败
     */
    @Override
    default boolean beforeEvaluate(Rule rule, Facts facts) {
        if (rule instanceof IRule) {

            return doBeforeEvaluate((IRule) rule, facts);
        }

        return Boolean.TRUE;
    }

    void doAfterEvaluate(IRule rule, Facts facts, boolean evaluationResult);

    /**
     * 该方法在执行Condition判断方法之后执行。
     *
     * @param rule             规则
     * @param facts            参数
     * @param evaluationResult 判断结果
     */
    @Override
    default void afterEvaluate(Rule rule, Facts facts, boolean evaluationResult) {
        if (rule instanceof IRule) {

            doAfterEvaluate((IRule) rule, facts, evaluationResult);
        }
    }

    void doOnEvaluationError(IRule rule, Facts facts, Exception exception);

    /**
     * 该方法在执行Condition判断方法发生异常之后执行
     *
     * @param rule      规则
     * @param facts     参数
     * @param exception 异常
     */
    @Override
    default void onEvaluationError(Rule rule, Facts facts, Exception exception) {
        if (rule instanceof IRule) {

            doOnEvaluationError((IRule) rule, facts, exception);
        }
    }

}
