package io.github.zhdotm.ohzh.rules.core;


import cn.hutool.json.JSONUtil;
import io.github.zhdotm.ohzh.rules.core.domain.action.IAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.impl.FirstExecuteCompositeAction;
import io.github.zhdotm.ohzh.rules.core.domain.action.impl.SingleAction;
import io.github.zhdotm.ohzh.rules.core.domain.condition.ICondition;
import io.github.zhdotm.ohzh.rules.core.domain.condition.impl.*;
import io.github.zhdotm.ohzh.rules.core.domain.rule.IRule;
import io.github.zhdotm.ohzh.rules.core.domain.rule.impl.AllEvaluatePassAllExecuteCompositeRule;
import io.github.zhdotm.ohzh.rules.core.domain.rule.impl.AnyEvaluatePassAllExecuteCompositeRule;
import io.github.zhdotm.ohzh.rules.core.domain.rule.impl.SingleRule;
import io.github.zhdotm.ohzh.rules.core.domain.rule.impl.WhichEvaluatePassWhichExecuteCompositeRule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.Map;

public class AppTest {

    public static void main1(String[] args) {
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

    public static void main2(String[] args) {
        IRule rule1 = new SingleRule("rule1", "规则1", new GreatThanCondition<>("fieldName001", 100), new SingleAction<>("doNothingFieldName001", "doNothing", facts -> (Integer) facts.get("fieldName001")));
        rule1.setPriority(1);
        IRule rule2 = new SingleRule("rule2", "规则2", new GreatThanCondition<>("fieldName001", 200), new SingleAction<>("doubleFieldName001", "doubleAction", facts -> ((Integer) facts.get("fieldName001")) * 2));
        rule2.setPriority(2);
        IRule rule3 = new SingleRule("rule3", "规则3", new GreatThanCondition<>("fieldName001", 300), new SingleAction<>("tripleFieldName001", "tripleAction", facts -> ((Integer) facts.get("fieldName001")) * 3));
        rule2.setPriority(3);

        AnyEvaluatePassAllExecuteCompositeRule anyEvaluatePassAllExecuteCompositeRule = new AnyEvaluatePassAllExecuteCompositeRule(rule1, rule2, rule3);

        DefaultRulesEngine defaultRulesEngine = new DefaultRulesEngine();

        Rules rules = new Rules(rule1, rule2, rule3);
        Facts facts = new Facts();
        facts.put("fieldName001", 101);
        Map<Rule, Boolean> check = defaultRulesEngine.check(rules, facts);
        check.forEach((rule, aBoolean) -> {
            System.out.println(rule.getName() + ": " + aBoolean);
        });

//        defaultRulesEngine.fire(rules, facts);
//        System.out.println(JSONUtil.toJsonStr(rule1.getExecuteReturnMap()));
//        System.out.println(JSONUtil.toJsonStr(rule2.getExecuteReturnMap()));
//        System.out.println(JSONUtil.toJsonStr(rule3.getExecuteReturnMap()));

        //一个条件匹配，所有动作执行
        rules.clear();
        rules.register(anyEvaluatePassAllExecuteCompositeRule);
        defaultRulesEngine.fire(rules, facts);
        System.out.println(JSONUtil.toJsonStr(anyEvaluatePassAllExecuteCompositeRule.getExecuteReturnMap()));

        //哪个条件匹配，哪个动作执行
        WhichEvaluatePassWhichExecuteCompositeRule whichEvaluatePassWhichExecuteCompositeRule = new WhichEvaluatePassWhichExecuteCompositeRule(rule1, rule2, rule3);
        rules.clear();
        rules.register(whichEvaluatePassWhichExecuteCompositeRule);
        defaultRulesEngine.fire(rules, facts);
        System.out.println(JSONUtil.toJsonStr(whichEvaluatePassWhichExecuteCompositeRule.getExecuteReturnMap()));

        //所有条件匹配，所有动作执行
        AllEvaluatePassAllExecuteCompositeRule allEvaluatePassAllExecuteCompositeRule = new AllEvaluatePassAllExecuteCompositeRule(rule1, rule2, rule3);
        rules.clear();
        rules.register(allEvaluatePassAllExecuteCompositeRule);
        defaultRulesEngine.fire(rules, facts);
        System.out.println(JSONUtil.toJsonStr(allEvaluatePassAllExecuteCompositeRule.getExecuteReturnMap()));
        facts.put("fieldName001", 1000);
        defaultRulesEngine.fire(rules, facts);
        System.out.println(JSONUtil.toJsonStr(allEvaluatePassAllExecuteCompositeRule.getExecuteReturnMap()));

    }

    public static void main(String[] args) {
        IRule rule1 = new SingleRule("rule1", "规则1", new GreatThanCondition<>("fieldName001", 100), new SingleAction<>("doNothingFieldName001", "doNothing", facts -> (Integer) facts.get("fieldName001")));
        rule1.setPriority(1);
        IRule rule2 = new SingleRule("rule2", "规则2", new GreatThanCondition<>("fieldName001", 200), new SingleAction<>("doubleFieldName001", "doubleAction", facts -> ((Integer) facts.get("fieldName001")) * 2));
        rule2.setPriority(2);
        IRule rule3 = new SingleRule("rule3", "规则3", new GreatThanCondition<>("fieldName001", 300), new SingleAction<>("tripleFieldName001", "tripleAction", facts -> ((Integer) facts.get("fieldName001")) * 3));
        rule2.setPriority(3);

        //复合规则内所有条件都通过，所有规则动作都执行
        AllEvaluatePassAllExecuteCompositeRule allEvaluatePassAllExecuteCompositeRule = new AllEvaluatePassAllExecuteCompositeRule(rule1, rule2, rule3);

        DefaultRulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Rules rules = new Rules(allEvaluatePassAllExecuteCompositeRule);
        Facts facts = new Facts();
        // 301全通过
        facts.put("fieldName001", 301);

        defaultRulesEngine.fire(rules, facts);

        //打印规则执行输出
        System.out.println(JSONUtil.toJsonStr(allEvaluatePassAllExecuteCompositeRule.getExecuteReturnMap()));
    }

}