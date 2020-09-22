package com.aaron.rpn.calculator;

import com.aaron.rpn.exception.MathException;

import java.math.BigDecimal;
import java.util.Stack;

/** the context of a round calculator*/
public class CalculatorContext {

  public CalculatorContext() {}

  /** in order to keep the precision let the big decimal to hold it */
  private final Stack<BigDecimal> nums = new Stack<>();

  private Stack<BigDecimal> numsBackUp = new Stack<>();

  private Stack<String> operatorBackUp = new Stack<>();

  /**
   * This validation logic suppose abstract to separate module
   *
   * @param size
   */
  public void ensureSizeOfNumberInStack(final int size) throws MathException {
    // operator <operator> (position: <pos>): insufficient parameters
    if (nums.isEmpty() || nums.size() < size) {
      throw new MathException("insufficient parameters");
    }
  }

  public BigDecimal pop() {
    return nums.pop();
  }

  public CalculatorContext push(final BigDecimal data) {
    nums.push(data);
    return this;
  }

  public CalculatorContext pushBackup(final BigDecimal data) {
    numsBackUp.push(data);
    return this;
  }

  public CalculatorContext pushBackupAction(final String action) {
    operatorBackUp.push(action);
    return this;
  }

  public void clearStack() {
    nums.clear();
    numsBackUp.clear();
    operatorBackUp.clear();
  }

  public void undo() {
    if (nums.size() == 0) {
      return;
    }
    nums.pop();
    operatorBackUp.pop();
    nums.push(numsBackUp.pop());
  }

  public void print() {
    if (nums.isEmpty()) {
      System.out.println("stack: empty");
    } else {
      StringBuilder sb = new StringBuilder("stack: ");
      for (final BigDecimal each : nums) {
        sb.append(each.toBigInteger()).append(" ");
      }
      System.out.println(sb.toString());
    }
  }
}
