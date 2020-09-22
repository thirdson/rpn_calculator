package com.aaron.rpn.math;

import com.aaron.rpn.calculator.CalculatorContext;
import com.aaron.rpn.exception.MathException;

/**
 * a very thin function wrapper, why always OO?
 */
@FunctionalInterface
public interface Action {
  void act(final CalculatorContext context) throws MathException;
}
