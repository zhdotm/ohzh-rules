package io.github.zhdotm.ohzh.rules.core.domain.action.impl;

import io.github.zhdotm.ohzh.rules.core.domain.action.ISingleAction;
import lombok.Getter;
import org.jeasy.rules.api.Facts;

import java.util.function.Function;

/**
 * 单个动作
 *
 * @author zhihao.mao
 */

public class SingleAction<T> implements ISingleAction {

    @Getter
    private final String returnFieldName;
    @Getter
    private final String actionCode;
    @Getter
    private final Function<Facts, T> doExecuteFunction;
    @Getter
    private T executeReturn;

    public SingleAction(String returnFieldName, String actionCode, Function<Facts, T> doExecuteFunction) {
        this.returnFieldName = returnFieldName;
        this.actionCode = actionCode;
        this.doExecuteFunction = doExecuteFunction;
    }

    @Override
    public void execute(Facts facts) throws Exception {
        executeReturn = doExecuteFunction.apply(facts);
    }

}
