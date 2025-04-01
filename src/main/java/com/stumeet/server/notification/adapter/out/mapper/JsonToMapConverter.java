package com.stumeet.server.notification.adapter.out.mapper;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stumeet.server.common.exception.model.FormatConversionException;
import com.stumeet.server.common.response.ErrorCode;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class JsonToMapConverter implements AttributeConverter<Map<String, String>, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new FormatConversionException(e, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            throw new FormatConversionException(e, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
