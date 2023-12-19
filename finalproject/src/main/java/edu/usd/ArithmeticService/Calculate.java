package edu.usd.ArithmeticService;


import java.util.Stack;
import java.util.concurrent.BrokenBarrierException;
public class Calculate {
    static Stack<Double> numbers = new Stack<>();
    static Stack<Character> operator = new Stack<>();


    public static double calculate(String expression) {
        char[] tokens = expression.toCharArray();
        for(int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
                continue;
            }
            // Check if the current character is a digit or a decimal point
            boolean digit = Character.isDigit(tokens[i]);
            if (digit || tokens[i] == '.' || (tokens[i] == '-' && (i == 0 || !Character.isDigit(tokens[i - 1]) && tokens[i - 1] != ')'))) {
                StringBuilder newExpression = new StringBuilder();
                // Build a number by appending digits, decimal points, and the negative sign
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.' || (tokens[i] == '-' && (i==0 ||!Character.isDigit(tokens[i - 1]))))) {
                    newExpression.append(tokens[i++]);
                }
                numbers.push(Double.parseDouble(newExpression.toString()));
                i--; // Adjust index after building the number
            } else if (Character.isLetter(tokens[i])) {
                // Handle letters (functions)
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
                    double numValue;
                    if(num.length() > 1){
                        numValue = calculate(num.toString());
                    } else {
                        numValue = Double.parseDouble(num.toString());
                    }
                    operator.push(funct_name.toString().charAt(0));
                    numbers.push(numValue);
                } else {
                    throw new IllegalArgumentException("Invalid function");
                }
                i--; // Adjust index after handling the function
            } else if (tokens[i] == '(') {
                // Handle opening parenthesis
                operator.push(tokens[i]);
            } else if (tokens[i] == ')') {
                // Handle closing parenthesis
                while (!operator.isEmpty() && operator.peek() != '(') {
                    char old_op = operator.pop();
                    if (old_op == 's' || old_op == 'c' || old_op == 't') {
                        numbers.push(finishOperation(old_op, numbers.pop()));
                    } else {
                        numbers.push(finishOperation(old_op, numbers.pop(), numbers.pop()));
                    }
                }
                operator.pop(); // Pop the opening parenthesis
            }
            // Handle operators
            if (isOperator(tokens[i])) {
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
        // Perform final calculations
        while(!operator.isEmpty()) {
            char old_op = operator.pop();
            if (old_op == 's' || old_op == 'c' || old_op == 't') {
                numbers.push(finishOperation(old_op, numbers.pop()));
            } else {
                numbers.push(finishOperation(old_op, numbers.pop(), numbers.pop()));
            }
        }
        return finalCalculation(operator, numbers);
    }
    private static double finalCalculation(Stack<Character> operator, Stack<Double> numbers) {
        while(!operator.isEmpty()) {
            char old_op = operator.pop();
            if (old_op == 's' || old_op == 'c' || old_op == 't') {
                numbers.push(finishOperation(old_op, numbers.pop()));
            } else {
                numbers.push(finishOperation(old_op, numbers.pop(), numbers.pop()));
            }
        }
        return numbers.pop();


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
        double value = switch (operator) {
            case '+' -> secondNum + firstNum;
            case '-' -> secondNum - firstNum;
            case '*' -> secondNum * firstNum;
            case '/' -> {
                if (secondNum == 0) {
                    throw new ArithmeticException("Cannot divide by 0");
                }
                yield secondNum / firstNum;
            }
            case '^' ->{
                if(firstNum<0){
                    yield 1/Math.pow(secondNum, Math.abs(firstNum));
                }else{
                    yield Math.pow(secondNum, firstNum);
                }
            }
            default -> 0;
        };
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

