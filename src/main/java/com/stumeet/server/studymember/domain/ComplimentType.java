package com.stumeet.server.studymember.domain;

import com.stumeet.server.common.exception.model.EnumValidationException;
import com.stumeet.server.common.response.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ComplimentType {
    PASSIONATE("\uD83D\uDD25 열정이 돋보이는 활동이에요"),
    DILIGENT("✨ 성실하게 참여했어요"),
    OUTSTANDING("\uD83D\uDC4D 결과물이 훌륭해요");

    private final String description;

    public static ComplimentType safeValueOf(String name) {
        try {
            return ComplimentType.valueOf(name);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new EnumValidationException(ErrorCode.INVALID_COMPLIMENT_TYPE_EXCEPTION, ComplimentType.class, name);
        }
    }
}
