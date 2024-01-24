package io.github.zhdotm.ohzh.rules.core.domain.condition.impl;

import lombok.Getter;
import org.jeasy.rules.api.Facts;

import java.util.function.Function;

/**
 * 单个条件
 *
 * @author zhihao.mao
 */

public class SingleCondition extends AbstractSingleCondition {

    @Getter
    private final String conditionTypeCode;
    @Getter
    private final Function<Facts, Boolean> doEvaluateFunction;

    public SingleCondition(String conditionTypeCode, Function<Facts, Boolean> doEvaluateFunction) {
        this.conditionTypeCode = conditionTypeCode;
        this.doEvaluateFunction = doEvaluateFunction;
    }

    @Override
    public boolean evaluate(Facts facts) {

        return doEvaluateFunction.apply(facts);
    }

}
