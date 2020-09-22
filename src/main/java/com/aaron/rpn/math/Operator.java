package com.aaron.rpn.math;

import com.aaron.rpn.calculator.CalculatorContext;
import com.aaron.rpn.exception.MathException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/** a very simple wrapper of the math logic */
public enum Operator {
  PLUS("+", (TwoParametersActon) (first, second) -> plus(first, second)),
  SUB("-", (TwoParametersActon) (first, second) -> sub(first, second)),
  MUL("*", (TwoParametersActon) (first, second) -> mul(first, second)),

  DIV("/", (TwoParametersActon) (first, second) -> div(first, second)),
  SQRT("sqrt", Operator::sqrt),

  UNDO("undo", Operator::undo),
  CLEAR("clear", Operator::clear),
  HELP("h", Operator::help),
  EXIT("exit", Operator::exit),
  ;

  Operator(String op, Action action) {
    this.op = op;
    this.action = action;
  }

  public static final int PRECISION = 15;
  protected final String op;
  protected final Action action;

  public String getOp() {
    return op;
  }

  public Action getAction() {
    return action;
  }

  public void act(final CalculatorContext context) throws MathException {
    action.act(context);
  }

  protected static final Map<String, Operator> operators = new HashMap<>();

  static {
    for (final Operator operator : Operator.values()) {
      operators.put(operator.op, operator);
    }
  }

  public static Operator map(final String input) {
    return operators.getOrDefault(input, HELP);
  }

  protected static BigDecimal plus(BigDecimal first, BigDecimal second) {
    return first.add(second);
  }

  protected static BigDecimal sub(BigDecimal first, BigDecimal second) {
    return first.subtract(second);
  }

  protected static BigDecimal mul(BigDecimal first, BigDecimal second) {
    return first.multiply(second);
  }

  protected static BigDecimal div(BigDecimal first, BigDecimal second) throws MathException {
    if (second.compareTo(BigDecimal.ZERO) == 0) {
      throw new MathException("try to divided  zero " + first.toString() + " / " + second);
    }
    return first.divide(second);
  }

  protected static BigDecimal sqrt(final CalculatorContext context) throws MathException {
    context.ensureSizeOfNumberInStack(1);
    BigDecimal last = context.pop();
    // 15 decimal places of precision

    // if this is neg?
    if (last.compareTo(BigDecimal.ZERO) < 0) {
      throw new MathException("try to sqrt negative number " + last);
    }

    BigDecimal sqrt = new BigDecimal(1);
    sqrt.setScale(PRECISION, RoundingMode.FLOOR);
    // a quick copy
    BigDecimal store = last.add(BigDecimal.ZERO);
    boolean first = true;
    do {
      if (!first) {
        store = new BigDecimal(sqrt.toString());
      } else first = false;
      store.setScale(PRECISION, RoundingMode.FLOOR);
      sqrt =
          last.divide(store, PRECISION, RoundingMode.FLOOR)
              .add(store)
              .divide(BigDecimal.valueOf(2), PRECISION, RoundingMode.FLOOR);
    } while (!store.equals(sqrt));

    sqrt = sqrt.setScale(PRECISION, RoundingMode.FLOOR);

    context.push(sqrt).pushBackup(last);
    return sqrt;
  }

  private static void undo(final CalculatorContext context) {
    context.undo();
  }

  private static void clear(final CalculatorContext context) {
    context.clearStack();
  }

  private static void help(final CalculatorContext context) {
    System.out.println("RPN Calculator [only!!!] support ops : [+, -, *, /, sqrt, undo, clear]");
  }

  private static void exit(final CalculatorContext context) {
    System.out.println("prepare to exit the game");
    context.print();
    System.exit(1);
  }
}
