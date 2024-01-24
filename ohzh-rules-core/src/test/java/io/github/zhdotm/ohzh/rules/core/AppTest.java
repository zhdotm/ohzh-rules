package io.github.zhdotm.ohzh.rules.core;


import cn.hutool.json.JSONUtil;
import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.impl.AllExecuteCompositeAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.impl.SingleAction;
import io.github.zhdotm.ohzh.rules.core.domain.condition.impl.*;
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
        LessThanCondition<Integer> inventoryQuantityLessThanCondition = new LessThanCondition<>("inventoryQuantity", 500);
        GreatThanCondition<Integer> inventoryQuantityGreatThanCondition = new GreatThanCondition<>("inventoryQuantity", 100);
        AllEvaluateCompositeCondition leftAllMatchCompositeCondition = new AllEvaluateCompositeCondition(inventoryQuantityLessThanCondition, inventoryQuantityGreatThanCondition);
        EqualToCondition<String> travelCompanyEqualToCondition = new EqualToCondition<>("companyType", "travelCompany");
        AnyEvaluateCompositeCondition compositeCondition = new AnyEvaluateCompositeCondition(leftAllMatchCompositeCondition, travelCompanyEqualToCondition);

        //动作 计算出 下个季度库存: nextSeasonInventoryQuantity
        IAction nextSeasonInventoryQuantityAction = new SingleAction<>("nextSeasonInventoryQuantity", "calculateNextSeasonInventoryQuantity", facts -> ((Long) facts.get("inventoryQuantity")) * 2);

        //动作 评估 是否需要扩张规模: needScaleUp
        IAction needScaleUpAction = new SingleAction<>("needScaleUp", "judgeNeedScaleUp", facts -> facts.get("companyType").equals("travelCompany"));
        AllExecuteCompositeAction compositeAction = new AllExecuteCompositeAction(nextSeasonInventoryQuantityAction, needScaleUpAction);

        //规则
        SingleRule rule1 = new SingleRule("rule1", "规则1", 1, compositeCondition, compositeAction);
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