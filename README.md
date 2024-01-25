# 简易规则引擎

基于[Easy-Rule](https://github.com/j-easy/easy-rules)做了增强，封装了一些常用条件、动作、规则以及相应的模版方法，拓展了规则执行后的结果输出（原先框架无法获取执行结果）。

## 增强点

### 1、条件

#### 1.1、内置条件

##### 1.1.1、内置单个条件

1. EqualToCondition（值等于条件）
2. NotEqualToCondition（值不等于条件）
3. ExistCondition（值存在条件）
4. NotExistCondition（值不存在条件）
5. GreatThanCondition（值大于条件）
6. GreatThanOrEqualToCondition（值大于等于条件）
7. LessThanCondition（值小于条件）
8. LessThanOrEqualToCondition（值小于等于条件）
9. RangeCondition（值在范围内条件）
10. NotRangeCondition（值不再范围内条件）

##### 1.1.2、内置复合条件

1. 复合条件内的所有条件都为True，则复合条件为True（AllEvaluateCompositeCondition）
2. 复合条件内任意条件为True，则复合条件为True（AnyEvaluateCompositeCondition）

#### 1.2、拓展条件

##### 1.2.1、拓展单个条件

继承AbstractSingleCondition抽象类或实现ISingleCondition接口

##### 1.2.2、拓展复合条件

继承AbstractCompositeCondition抽象类或实现ICompositeCondition接口

### 2、动作

#### 2.1、内置动作

##### 2.1.1、内置单个动作

SingleAction（由创建SingleAction实例时指定动作逻辑以及动作完成后返回的字段和值）。

举个例子

```java
//动作 计算出 下个季度库存: nextSeasonInventoryQuantity
IAction nextSeasonInventoryQuantityAction = new SingleAction<>("nextSeasonInventoryQuantity", "calculateNextSeasonInventoryQuantity", facts -> ((Long) facts.get("inventoryQuantity")) * 2);
```

##### 2.1.2、内置复合动作

1. AllExecuteCompositeAction（复合动作（全执行））
2. FirstExecuteCompositeAction（复合动作（执行第一个））

#### 2.2、拓展动作

##### 2.2.1、拓展单个动作

继承AbstractSingleAction抽象类或实现ISingleAction接口

##### 2.2.2、拓展复合动作

继承AbstractCompositeAction抽象类或实现ICompositeAction接口

### 3、规则

#### 3.1、内置规则

##### 3.1、内置单个规则

SingleRule（由创建实例时绑定规则的条件和动作）

##### 3.2、内置复合规则

1. AllEvaluatePassAllExecuteCompositeRule（全部规则的条件通过，全部规则的动作执行）
2. AnyEvaluatePassAllExecuteCompositeRule（任意规则的条件通过，全部规则的动作执行）
3. FirstEvaluatePassFirstExecuteCompositeRule（首个通过条件的规则的动作执行，其他规则的动作不执行）
4. WhichEvaluatePassWhichExecuteCompositeRule（哪个规则的条件通过了，就执行哪个规则的动作（比如复合规则内有5个规则，5个规则内有3个规则条件通过了，那么这3个规则执行））

#### 3.2、拓展规则

##### 3.2.1、拓展单个规则

继承AbstractSingleRule抽象类或实现ISingleRule接口

##### 3.2.2、拓展复合规则

继承AbstractCompositeRule抽象类或实现ICompositeRule接口

### 4、获取规则执行后的执行结果

在规则引擎调用玩规则后，调用io.github.zhdotm.ohzh.rules.core.domain.rule.IRule#getExecuteReturnMap方法，即可获取该规则的执行结果。

## 使用案例

1、复合规则内规则1、规则2、规则3条件均通过，才可以都执行

在只有一个内部规则通过的情况下

```java
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
    // 101只能通过rule1
    facts.put("fieldName001", 101);

    defaultRulesEngine.fire(rules, facts);

    //打印规则执行输出
    System.out.println(JSONUtil.toJsonStr(allEvaluatePassAllExecuteCompositeRule.getExecuteReturnMap()));
}
```

控制台打印结果

```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
{}

Process finished with exit code 0
```

在通过全部内部规则的情况下

```java
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
```

控制台打印结果

```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
{"doubleAction":602,"doNothing":301,"tripleAction":903}

Process finished with exit code 0
```

