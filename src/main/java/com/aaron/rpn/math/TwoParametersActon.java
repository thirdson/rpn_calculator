package com.aaron.rpn.math;

import com.aaron.rpn.calculator.CalculatorContext;
import com.aaron.rpn.exception.MathException;

import java.math.BigDecimal;

@FunctionalInterface
public interface TwoParametersActon extends Action {

  @Override
  default void act(final CalculatorContext context) throws MathException {
    context.ensureSizeOfNumberInStack(2);
    final BigDecimal num2 = context.pop();
    final BigDecimal num1 = context.pop();
    final BigDecimal result = act2(num1, num2);
    context.push(result).pushBackup(num2).pushBackup(num1);
  }

  BigDecimal act2(BigDecimal first, BigDecimal second) throws MathException;
}
