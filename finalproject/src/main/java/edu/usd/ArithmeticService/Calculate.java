package edu.usd.ArithmeticService;

import java.util.Stack;
import java.util.concurrent.BrokenBarrierException;

public class Calculate {
    public static double calculate(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operator = new Stack<>();



        for(int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
                continue;
            }
            boolean digit = Character.isDigit(tokens[i]);
            if (digit || tokens[i] == '.') {
                StringBuilder newExpression = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    newExpression.append(tokens[i++]);

                }
                numbers.push(Double.parseDouble(newExpression.toString()));
                i--;
            } else if (Character.isLetter(tokens[i])) {
                StringBuilder funct_name = new StringBuilder();
                while (i < tokens.length && tokens[i] != '(') {
                    funct_name.append(tokens[i++]);
                }
                if (i < tokens.length && tokens[i] == '(') {
                    i++;
                    StringBuilder num = new StringBuilder();
                    while (i < tokens.length && tokens[i] != ')') {
                        num.append(tokens[i++]);
                    }
                    if (tokens[i] == ')') {

                        i++;
                    }
                    //double numValue = 0;
                       /* if(num.length() >= 2){
                            System.out.println(numValue);
                            numValue = calculate(num.toString());
                        }*/
                    double numValue = Double.parseDouble(num.toString());
                    operator.push(funct_name.toString().charAt(0));
                    numbers.push(numValue); //uncomment and 2 lines above
                } else {
                    throw new IllegalArgumentException("Invalid function");
                }
                i--;
            }else if (tokens[i] == '(') {
                operator.push(tokens[i]);
            }else if (tokens[i] == ')') {
                while (!operator.isEmpty() && operator.peek() != '(') {
                    char old_op = operator.pop();
                    if (old_op == 's' || old_op == 'c' || old_op == 't') {
                        numbers.push(finishOperation(old_op, numbers.pop()));
                    } else {
                        numbers.push(finishOperation(old_op, numbers.pop(), numbers.pop()));
                    }
                }
                operator.pop();

            } if (isOperator(tokens[i])) {
                while (!operator.isEmpty() && Precedence(tokens[i], operator.peek())) {
                    char old_op = operator.pop();
                    if (old_op == 's' || old_op == 'c' || old_op == 't') {
                        numbers.push(finishOperation(old_op, numbers.pop()));
                    } else {
                        numbers.push(finishOperation(old_op, numbers.pop(), numbers.pop()));
                    }
                }
                operator.push(tokens[i]);

            }

        }
        while(!operator.isEmpty()) {
            char old_op = operator.pop();
            if (old_op == 's' || old_op == 'c' || old_op == 't') {
                numbers.push(finishOperation(old_op, numbers.pop()));
            } else {
                numbers.push(finishOperation(old_op, numbers.pop(), numbers.pop()));
            }
        }
        double final_val = numbers.pop();
        return final_val;
    }

    public static boolean Precedence(char firstOperator, char secondOperator)
    {
        if(secondOperator == '(' || secondOperator == ')'){
            return false;
        }
        if(firstOperator == '^'){
            return false;
        }
        else if ((firstOperator == '*' || firstOperator == '/' || firstOperator =='s' || firstOperator == 'c' || firstOperator == 't') && (secondOperator == '+' || secondOperator == '-')) {
            return false;
        }
        return true;


    }
    public static double finishOperation(char operator, double number)
    {
        double finalValue = 0;
        double value = 0;
        switch(operator){
            case 's':
                value = calculate(Double.toString(number));
                System.out.println("value: " + value);
                finalValue = Math.sin(value);
                break;
            case 'c':
                finalValue = Math.cos(number);
                break;
            case 't':
                finalValue =  Math.tan(number);
                break;
        }
        System.out.println(finalValue);
        return finalValue;
    }

    public static double finishOperation(char operator, double firstNum, double secondNum)
    {
        double value = 0;
        switch(operator)
        {
            case '+':
                System.out.println(secondNum + "+" + firstNum);
                value = secondNum + firstNum;
                break;
            case '-':
                System.out.println(secondNum + "-" + firstNum);

                value = secondNum - firstNum;
                break;
            case '*':
                System.out.println(secondNum + "*" + firstNum);
                value = secondNum * firstNum;
                break;
            case '/':
                if(secondNum == 0)
                {
                    throw new ArithmeticException("Cannot divide by 0");
                }
                System.out.println(secondNum + "/" + firstNum);

                value = secondNum / firstNum;
                break;
            case '^':
                value = Math.pow(secondNum, firstNum);
                break;
        }

        System.out.println(value);
        return value;

    }
    public static boolean isOperator(Character token)
    {
        Character[] operators = new Character[]{'+', '-', '/', '*', '^', 's', 'c','t'};
        for(int i = 0; i<operators.length; i++){
            if(token.equals(operators[i]))
            {
                return true;
            }
        }
        return false;
    }
}
