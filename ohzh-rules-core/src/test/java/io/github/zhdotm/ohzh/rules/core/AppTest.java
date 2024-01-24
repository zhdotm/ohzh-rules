package io.github.zhdotm.ohzh.rules.core;


import cn.hutool.json.JSONUtil;
import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.impl.FirstExecuteCompositeAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.impl.SingleAction;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICondition;
import io.github.zhdotm.ohzh.rules.core.domain.condition.impl.*;
import io.github.zhdotm.ohzh.rules.core.domain.rule.IRule;
import io.github.zhdotm.ohzh.rules.core.domain.rule.impl.SingleRule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.Map;

public class AppTest {

    public static void main(String[] args) {
        DefaultRulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Rules rules = new Rules();
        //条件 (inventoryQuantity > 100 && inventoryQuantity < 500) || (companyType == travelCompany)
        ICondition inventoryQuantityLessThanCondition = new LessThanCondition<>("inventoryQuantity", 500);
        inventoryQuantityLessThanCondition.setPriority(2);
        ICondition inventoryQuantityGreatThanCondition = new GreatThanCondition<>("inventoryQuantity", 100);
        inventoryQuantityGreatThanCondition.setPriority(1);
        ICondition leftAllMatchCompositeCondition = new AllEvaluateCompositeCondition(inventoryQuantityLessThanCondition, inventoryQuantityGreatThanCondition);
        leftAllMatchCompositeCondition.setPriority(2);
        ICondition travelCompanyEqualToCondition = new EqualToCondition<>("companyType", "travelCompany");
        travelCompanyEqualToCondition.setPriority(1);
        ICondition compositeCondition = new AnyEvaluateCompositeCondition(leftAllMatchCompositeCondition, travelCompanyEqualToCondition);

        //动作 计算出 下个季度库存: nextSeasonInventoryQuantity
        IAction nextSeasonInventoryQuantityAction = new SingleAction<>("nextSeasonInventoryQuantity", "calculateNextSeasonInventoryQuantity", facts -> ((Long) facts.get("inventoryQuantity")) * 2);
        nextSeasonInventoryQuantityAction.setPriority(1);
        //动作 评估 是否需要扩张规模: needScaleUp
        IAction needScaleUpAction = new SingleAction<>("needScaleUp", "judgeNeedScaleUp", facts -> facts.get("companyType").equals("travelCompany"));
        needScaleUpAction.setPriority(2);
        IAction compositeAction = new FirstExecuteCompositeAction(nextSeasonInventoryQuantityAction, needScaleUpAction);

        //规则
        IRule rule1 = new SingleRule("rule1", "规则1", compositeCondition, compositeAction);
        rules.register(rule1);

        //执行
        Facts facts = new Facts();
        facts.put("inventoryQuantity", 10L);
        facts.put("companyType", "travelCompany");
        defaultRulesEngine.fire(rules, facts);

        //执行结果
        Map<String, Object> executeReturnMap = rule1.getExecuteReturnMap();
        System.out.println(JSONUtil.toJsonPrettyStr(executeReturnMap));
    }
}