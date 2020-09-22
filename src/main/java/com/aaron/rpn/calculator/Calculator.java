package com.aaron.rpn.calculator;

import com.aaron.rpn.exception.MathException;
import com.aaron.rpn.math.Operator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/** this is the engine's entr point */
public class Calculator {

  static final Pattern SPACE_SPLITTER = Pattern.compile(" ", Pattern.LITERAL);

  protected final CalculatorContext context = new CalculatorContext();

  public void consume(final String input) {
    // The calculator waits for user input and expects to receive strings containing whitespace
    // separated lists of numbers and
    // operators.

    final String[] inputs = SPACE_SPLITTER.split(input);
    for (final String each : inputs) {
      //
      if (isNumber(each)) {

        context.push(new BigDecimal(each));

      } else {

        final Operator operator = Operator.map(each);
        try {

          operator.act(context);
          context.pushBackupAction(operator.getOp());

        } catch (MathException e) {
          System.out.println(
              "Fail handle action "
                  + operator.getOp()
                  + " exception "
                  + e.getMessage()
                  + " current stack ");
          context.print();
        }
      }
    }
    context.print();
  }

  protected static boolean isNumber(String str) {
    if (str == null || str.length() == 0) {
      return false;
    }

    for (int i = str.length(); --i >= 0; ) {
      char val = str.charAt(i);
      if (!Character.isDigit(val) || val == '.') {
        return false;
      }
    }
    return true;
  }
}
