package io.github.zhdotm.ohzh.rules.core.domain.rule.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.ICompositeAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.ISingleAction;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICondition;
import io.github.zhdotm.ohzh.rules.core.domain.rule.ISingleRule;
import lombok.Getter;
import lombok.Setter;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

import java.util.Map;
import java.util.Optional;

/**
 * 单个规则（抽象）
 *
 * @author zhihao.mao
 */

public abstract class AbstractSingleRule implements ISingleRule {

    @Getter
    private final String ruleTypeCode;
    @Getter
    private final ICondition condition;
    @Getter
    private final IAction action;
    @Getter
    private final String description;
    @Getter
    @Setter
    private int priority = IAction.DEFAULT_PRIORITY;

    @Getter
    private Map<String, Object> executeReturnMap;

    public AbstractSingleRule(String ruleTypeCode,
                              String description,
                              ICondition condition,
                              IAction action) {
        this.ruleTypeCode = ruleTypeCode;
        this.description = Optional.ofNullable(description).orElse(Rule.DEFAULT_DESCRIPTION);
        this.condition = condition;
        this.action = action;
    }

    public void putActionExecuteReturn(IAction action) {
        if (action instanceof ISingleAction) {
            ISingleAction singleAction = (ISingleAction) action;
            String returnFieldName = singleAction.getReturnFieldName();
            Object executeReturn = singleAction.getExecuteReturn();
            if (StrUtil.isNotBlank(returnFieldName)) {
                this.executeReturnMap = MapUtil.newHashMap();
                this.executeReturnMap.put(returnFieldName, executeReturn);
            }
        }
        if (action instanceof ICompositeAction) {
            ICompositeAction compositeAction = (ICompositeAction) action;
            this.executeReturnMap = compositeAction.getExecuteReturnMap();
        }
    }

    @Override
    public void execute(Facts facts) throws Exception {
        IAction action = getAction();
        action.execute(facts);
        putActionExecuteReturn(action);
    }

}
