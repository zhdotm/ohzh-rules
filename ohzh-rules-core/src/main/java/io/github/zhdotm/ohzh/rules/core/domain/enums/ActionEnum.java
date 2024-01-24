package io.github.zhdotm.ohzh.rules.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 条件枚举
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public enum ActionEnum {

    /**
     * 条件枚举
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
