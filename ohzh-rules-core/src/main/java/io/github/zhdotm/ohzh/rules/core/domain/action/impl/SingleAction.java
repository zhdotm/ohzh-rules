package io.github.zhdotm.ohzh.rules.core.domain.action.impl;

import org.jeasy.rules.api.Facts;

import java.util.function.Function;

/**
 * 单个动作
 *
 * @author zhihao.mao
 */

public class SingleAction<T> extends AbstractSingleAction<T> {


    public SingleAction(String actionTypeCode, String returnFieldName, Function<Facts, T> doExecuteFunction) {
        super(actionTypeCode, returnFieldName, doExecuteFunction);
    }

}
