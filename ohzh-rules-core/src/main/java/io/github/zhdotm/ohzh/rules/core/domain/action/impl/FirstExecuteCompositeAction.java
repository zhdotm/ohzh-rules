package io.github.zhdotm.ohzh.rules.core.domain.action.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ActionEnum;
import org.jeasy.rules.api.Facts;

import java.util.Collection;

/**
 * 复合动作（执行第一个）
 *
 * @author zhihao.mao
 */

public class FirstExecuteCompositeAction extends AbstractCompositeAction {

    public FirstExecuteCompositeAction(IAction... actions) {
        super(actions);
    }

    @Override
    public void execute(Facts facts) throws Exception {
        Collection<IAction> actions = getActions();
        if (CollectionUtil.isEmpty(actions)) {

            return;
        }
        IAction action = ListUtil.toList(actions).get(0);
        action.execute(facts);
        putActionExecuteReturn(action);
    }

    @Override
    public String getActionCode() {

        return ActionEnum.FIRST_EXECUTE.getCode();
    }

}
