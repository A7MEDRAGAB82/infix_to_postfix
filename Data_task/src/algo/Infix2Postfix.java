package algo;

import java.util.Stack;

public class Infix2Postfix {

    // Method to determine operator precedence
    private static int precedence(char op) {
        switch (op) {
            case '^': return 3;  // Highest precedence for exponentiation
            case '*':
            case '/': return 2;   // Medium precedence for multiplication/division
            case '+':
            case '-': return 1;   // Lowest precedence for addition/subtraction
            default:  return 0;    // Return 0 for non-operators
        }
    }

    // Method to convert infix expression to postfix notation
    public static String in2post(String infix) {
        Stack<Character> stack = new Stack<>();  // Stack to hold operators
        String postfix = "";                    // Result string
        int i = 0;                               // Index for traversing infix string

        while (i < infix.length()) {
            char c = infix.charAt(i);             // Get current character

            // Skip whitespace
            if (c == ' ') {
                i++;
                continue;
            }

            // Handle numbers (including decimals)
            if (Character.isDigit(c) || c == '.') {
                String num = "";
                // Collect all consecutive digits and decimal points
                while (i < infix.length() &&
                        (Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '.')) {
                    num += infix.charAt(i++);
                }
                postfix += num + " ";  // Add number to output with space
                continue;
            }

            // Handle opening parenthesis
            if (c == '(') {
                stack.push(c);  // Push to stack
                i++;
                continue;
            }

            // Handle closing parenthesis
            if (c == ')') {
                // Pop all operators until opening parenthesis is found
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix += stack.pop() + " ";
                }
                stack.pop(); // Remove '(' from stack
                i++;
                continue;
            }

            // Handle operators (anything with precedence > 0)
            if (precedence(c) > 0) {
                // Pop operators with higher or equal precedence
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix += stack.pop() + " ";
                }
                stack.push(c);  // Push current operator to stack
                i++;
                continue;
            }

            i++; // Skip other characters
        }

        // Pop all remaining operators from stack
        while (!stack.isEmpty()) {
            postfix += stack.pop() + " ";
        }

        return postfix.trim();  // Return result without trailing space
    }

    // Method to evaluate postfix expression
    public static double post_eval(String s) {
        Stack<Double> stack = new Stack<>();  // Stack to hold operands
        int i = 0;                            // Index for traversing postfix string

        while (i < s.length()) {
            char c = s.charAt(i++);          // Get current character
            if (c == ' ') continue;           // Skip spaces

            // If it's a digit or decimal point
            if (c >= '0' && c <= '9' || c == '.') {
                double num = 0;
                double frac = 0.1;            // For decimal places
                boolean isDecimal = false;    // Flag for decimal point

                // Build the number
                do {
                    if (c == '.') {
                        isDecimal = true;     // Found decimal point
                    } else if (!isDecimal) {
                        num = num * 10 + (c - '0');  // Calculate whole number part
                    } else {
                        num += (c - '0') * frac;     // Calculate decimal part
                        frac *= 0.1;                 // Move to next decimal place
                    }
                    if (i >= s.length()) break;
                    c = s.charAt(i++);        // Get next character
                } while (c >= '0' && c <= '9' || c == '.');
                stack.push(num);  // Push number to stack
            }
            // If it's an operator
            else {
                double b = stack.pop();  // Second operand
                double a = stack.pop();  // First operand

                switch (c) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                    case '^':
                        stack.push(Math.pow(a, b));
                        break;
                }
            }
        }
        return stack.pop();  // Final result is the only remaining number in stack
    }
}