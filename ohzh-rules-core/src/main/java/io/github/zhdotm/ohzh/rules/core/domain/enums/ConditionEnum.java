package io.github.zhdotm.ohzh.rules.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 条件枚举
 *
 * @author zhihao.mao
 */

@AllArgsConstructor
public enum ConditionEnum {

    /**
     * 条件枚举
     */
    ALL_EVALUATE("ALL_EVALUATE_CONDITION", "全匹配"),
    ANY_EVALUATE("ANY_EVALUATE_CONDITION", "任意匹配"),
    EQUAL_TO("equal_to_condition", "等于"),
    NOT_EQUAL_TO("not_equal_to_condition", "不等于"),
    EXIST("exist_condition", "存在"),
    NOT_EXIST("not_exist_condition", "不存在"),
    GREAT_THAN("great_than_condition", "大于"),
    GREAT_THAN_OR_EQUAL_TO("great_than_or_equal_to_condition", "大于等于"),
    LESS_THAN("less_than_condition", "小于"),
    LESS_THAN_OR_EQUAL_TO("less_than_or_equal_to_condition", "小于等于"),
    RANGE("range_condition", "范围内（包含范围边界）"),
    NOT_RANGE("not_range_condition", "范围外（不包含范围边界）"),
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
