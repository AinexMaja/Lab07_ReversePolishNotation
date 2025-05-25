package postfix;
import operators.Underflow;
import java.util.Stack;


public class Postfix {

	public static double evaluate(String postfix) throws Underflow {
		double result = 0;
		Stack<Double> numbers = new Stack<>();

		for(int i = 0; i < postfix.length(); i++) {
			char currentSymbol = postfix.charAt(i);

			if (isNumber(currentSymbol)) numbers.push((double) (currentSymbol - '0'));
			else{

				double value2 = numbers.pop();
				double value1 = numbers.pop();

				if(currentSymbol == '+') numbers.push((value1 + value2));
				if(currentSymbol == '-') numbers.push((value1 - value2));
				if(currentSymbol == '*') numbers.push((value1 * value2));
				if(currentSymbol == '/') numbers.push(value1 / value2);
				if(currentSymbol == '^') numbers.push(Math.pow(value1, value2));
			}
		}

		return numbers.pop();
	}

	//return true if input is a digit
	public static boolean isNumber(char symbol){
		return Character.isDigit(symbol);
	}

	public static void main(String[] args) throws Underflow {
		System.out.println(evaluate("231*+9-"));
		System.out.println(evaluate("91-2-32*-1-"));
		System.out.println(evaluate("52+83-*4/"));
		System.out.println(evaluate("57+32-/"));
	}
}
