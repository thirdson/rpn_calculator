package com.aaron.rpn;

import com.aaron.rpn.calculator.Calculator;

import java.util.Scanner;

public class Application {

  public static void main(String[] args) {
    Calculator rpnCalculator = new Calculator();
    Scanner sc = new Scanner(System.in);
    System.out.println("let's start game>");
    while (true) {
      String input = sc.nextLine();
      if (input == null || input.isEmpty()) {
        System.err.println("please input validate characters!!!!!");
      } else {
        rpnCalculator.consume(input);
      }
    }
  }
}
