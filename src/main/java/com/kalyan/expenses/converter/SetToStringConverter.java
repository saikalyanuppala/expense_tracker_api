package com.kalyan.expenses.converter;

import java.util.Collections;
import java.util.Set;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SetToStringConverter implements AttributeConverter<Set<String>, String> {

	@Override
	public String convertToDatabaseColumn(Set<String> list) {
		return list != null ? String.join(";", list) : "";
	}

	@Override
	public Set<String> convertToEntityAttribute(String dbData) {
		return dbData != "" ? Set.of(dbData.split(";")) : Collections.emptySet();
	}
}
