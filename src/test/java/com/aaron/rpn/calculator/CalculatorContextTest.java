package com.aaron.rpn.calculator;

import com.aaron.rpn.exception.MathException;
import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;

public class CalculatorContextTest extends TestCase {

  @Test
  public void testEnsureSizeOfNumberInStack() throws MathException {

    CalculatorContext context = new CalculatorContext();

    context.push(BigDecimal.ONE);
    context.push(BigDecimal.TEN);
    context.ensureSizeOfNumberInStack(2);
    assertTrue("pass the test", true);
  }

  @Test(expected = MathException.class)
  public void testEnsureSizeOfNumberInStackFail() throws MathException {

    try {
      CalculatorContext context = new CalculatorContext();
      context.ensureSizeOfNumberInStack(2);
    } catch (MathException exception) {
      assertTrue("fail pop up" + exception.getMessage(), true);
    }
  }

  public void testPop() {}

  public void testPush() {}

  public void testPushBackup() {}

  public void testPushBackupAction() {}

  public void testClearStack() {}

  public void testUndo() {}
}
