package com.github.tomek39856.hotel.manager.reservation.resolver;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatterResolver implements ParameterResolver {
  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType() == DateTimeFormatter.class;
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd");
  }
}
