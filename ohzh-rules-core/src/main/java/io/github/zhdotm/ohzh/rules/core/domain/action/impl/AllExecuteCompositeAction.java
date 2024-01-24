package io.github.zhdotm.ohzh.rules.core.domain.action.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.enums.ActionTypeEnum;
import org.jeasy.rules.api.Facts;

import java.util.Collection;

/**
 * 复合动作（全执行）
 *
 * @author zhihao.mao
 */

public class AllExecuteCompositeAction extends AbstractCompositeAction {

    public AllExecuteCompositeAction(IAction... actions) {
        super(actions);
    }

    @Override
    public void execute(Facts facts) throws Exception {
        Collection<IAction> actions = getActions();
        if (CollectionUtil.isEmpty(actions)) {

            return;
        }
        for (IAction action : actions) {
            action.execute(facts);
            putActionExecuteReturn(action);
        }
    }

    @Override
    public String getActionTypeCode() {

        return ActionTypeEnum.ALL_EXECUTE.getCode();
    }

}
