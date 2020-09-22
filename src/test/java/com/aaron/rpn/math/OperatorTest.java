package com.aaron.rpn.math;

import com.aaron.rpn.calculator.CalculatorContext;
import com.aaron.rpn.exception.MathException;
import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;

public class OperatorTest extends TestCase {

  public void testPlus() {}

  public void testSub() {}

  public void testMul() {}

  public void testDiv() {
    //TODO test div by ZERO?
  }

  @Test
  public void testSqrt() throws MathException {

    CalculatorContext context = new CalculatorContext();

    context.push(BigDecimal.valueOf(4));

    BigDecimal result  =  Operator.sqrt(context);

    assertEquals(2d, result.doubleValue(), 0.0001);

    System.out.println(result);


  }
}
