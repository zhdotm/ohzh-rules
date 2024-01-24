package io.github.zhdotm.ohzh.rules.core.domain.action.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.ICompositeAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.ISingleAction;
import lombok.Getter;

import java.util.*;

/**
 * 复合动作（抽象）
 *
 * @author zhihao.mao
 */

public abstract class AbstractCompositeAction implements ICompositeAction {

    @Getter
    private final Map<String, Object> executeReturnMap;

    @Getter
    private final Collection<IAction> actions;

    public AbstractCompositeAction(IAction... actions) {
        this.actions = new ArrayList<IAction>() {
            {
                sort(Comparator.comparingInt(IAction::getPriority));
            }
        };
        if (ArrayUtil.isNotEmpty(actions)) {
            this.actions.addAll(Arrays.asList(actions));
        }

        this.executeReturnMap = new HashMap<>();
    }

    public void putActionExecuteReturn(IAction action) {
        if (action instanceof ISingleAction) {
            ISingleAction singleAction = (ISingleAction) action;
            String returnFieldName = singleAction.getReturnFieldName();
            Object executeReturn = singleAction.getExecuteReturn();
            if (StrUtil.isNotBlank(returnFieldName)) {
                getExecuteReturnMap().put(returnFieldName, executeReturn);
            }
        }
        if (action instanceof ICompositeAction) {
            ICompositeAction compositeAction = (ICompositeAction) action;
            Map<String, Object> actionExecuteReturnMap = compositeAction.getExecuteReturnMap();
            if (CollectionUtil.isNotEmpty(actionExecuteReturnMap)) {
                getExecuteReturnMap().putAll(actionExecuteReturnMap);
            }
        }
    }

    @Override
    public Boolean addAction(IAction action) {
        if (ObjectUtil.isEmpty(action)) {

            return Boolean.TRUE;
        }

        return this.actions.add(action);
    }

    @Override
    public Boolean addActions(Collection<IAction> actions) {
        if (CollectionUtil.isEmpty(actions)) {

            return Boolean.TRUE;
        }

        return this.actions.addAll(actions);
    }

}
