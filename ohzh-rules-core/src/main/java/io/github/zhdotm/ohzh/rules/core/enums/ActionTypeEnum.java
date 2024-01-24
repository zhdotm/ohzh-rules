package io.github.zhdotm.ohzh.rules.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 动作类型枚举
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public enum ActionTypeEnum {

    /**
     * 动作类型枚举
     */
    ALL_EXECUTE("all_execute_action", "全执行"),
    FIRST_EXECUTE("first_execute_action", "首个执行"),
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
