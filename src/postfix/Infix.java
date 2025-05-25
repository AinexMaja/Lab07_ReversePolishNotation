package postfix;

import java.util.Stack;

public class Infix {

    public static String toPostfix(String infix)  {
        //stack to store operators
        Stack<Character> operators = new Stack<>();
        //string to build the postfix expression
        String postfix = "";

        for(int i = 0; i < infix.length(); i++) {
            char currentSymbol = infix.charAt(i);
            //append numbers directly into the postfix expression
            if (isNumber(currentSymbol)) postfix += currentSymbol;
            //push opening clamp onto the stack
            else if (currentSymbol == '(') operators.push(currentSymbol);
            else if (currentSymbol == ')') {
                //put all operators to the postfix until a closing clamp is found
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix += operators.pop();
                }
                //remove clamp
                if (!operators.isEmpty() && operators.peek() == '(') operators.pop();

            //process operators
            } else {
                // Pop operators with higher or equal precedence from the stack
                while (!operators.isEmpty() && getPrecedence(currentSymbol) <= getPrecedence(operators.peek())) {
                    postfix += operators.pop();
                }
                //push current operator onto the stack
                operators.push(currentSymbol);
            }
        }

        //append remaining operators from the stack
        while (!operators.isEmpty()) {
            postfix += operators.pop();
        }

        return postfix;
    }

    //return precendence of a operator
    public static int getPrecedence(char symbol){
        if(symbol == '^') return 3;
        if(symbol == '/' || symbol == '*') return 2;
        if(symbol == '+' || symbol == '-') return 1;
        return -1;
    }

    //return true if input is a digit
    public static boolean isNumber(char symbol){
        return Character.isDigit(symbol);
    }

    public static void main(String[] args) {
        System.out.println(toPostfix("1+2*3"));
        System.out.println(toPostfix("(1+2)*3"));
        System.out.println(toPostfix("1+(2*3)-4"));
    }
}