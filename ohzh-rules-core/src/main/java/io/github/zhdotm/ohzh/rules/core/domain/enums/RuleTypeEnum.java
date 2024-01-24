package io.github.zhdotm.ohzh.rules.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 规则类型枚举
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public enum RuleTypeEnum {

    /**
     * 条件枚举
     */
    ALL_EVALUATE_PASS_ALL_EXECUTE("all_evaluate_pass_all_execute", "所有条件都通过，所有动作都执行"),
    ANY_EVALUATE_PASS_ALL_EXECUTE("any_evaluate_pass_all_execute", "任意一个条件通过，所有动作都执行"),
    FIRST_EVALUATE_PASS_FIRST_EXECUTE("first_evaluate_pass_first_execute", "首个通过的条件，首个动作执行，其他不执行"),
    WHICH_EVALUATE_PASS_WHICH_EXECUTE("which_evaluate_pass_which_execute", "哪个条件通过，哪个动作执行"),
    ;

    /**
     * 编码
     */
    @Getter
    private final String code;

    /**
     * 描述
     */
    @Getter
    private final String description;
}
