package stack;

import operators.LinkedListStack;
import operators.Stack;
import operators.Underflow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StackTest {

	Stack<String> stack;

	@BeforeEach
	public void createStack() {
		stack = new LinkedListStack<>();
	}

	/**
	 * simple tests
	 */
	@Test
	public void testPush1() {
		stack.push("a");
		assertEquals("a", stack.toString());
	}

	@Test
	public void testPush2() {
		stack.push("a");
		stack.push("b1");
		assertEquals("a-b1", stack.toString());
	}

	@Test
	public void testPop() throws Underflow {
		stack.push("a");
		stack.push("b");
		stack.pop();
		assertEquals("a", stack.toString());
	}
	@Test
	public void testPop2() throws Underflow {
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.pop();
		assertEquals("a-b", stack.toString());
	}
	/**
	 * Axiom 1: isEmpty(new Stack()) = true
	 */
	@Test
	public void axiom1() {
		assertEquals(true, stack.isEmpty());
	}

	/**
	 * Axiom 2: isEmpty(push(q,x)) = false
	 */
	@Test
	public void axiom2() {
		stack.push("A");
		assertEquals(false, stack.isEmpty());
	}

	/**
	 * Axiom 3: pop(new Stack()) = error
	 *
	 */

	public void axiom3() {
		assertThrows(Underflow.class, stack::pop);
	}

	/**
	 * Axiom 4: getFront(new Stack()) = error
	 */
	@Test
	public void axiom4() throws Underflow {
		Underflow underflow = assertThrows(
				Underflow.class,
				() -> stack.top());
		;
		//assertTrue(underflow.getMessage().contains("empty"));
	}





	/**
	 * Axiom 5: pop(push(s,x)) = x
	 */
	@Test
	public void axiom5OnEmptyStack() throws Underflow {
		String before = stack.toString();
		stack.push("huhu");
		stack.pop();
		assertEquals(before, stack.toString());
	}

	@Test
	public void axiom5OnEmptyNonEmpty() throws Underflow {
		stack.push("a");
		stack.push("b");
		String before = stack.toString();
		stack.push("huhu");
		stack.pop();
		assertEquals(before, stack.toString());
	}

	/**
	 * Axiom 6: top(push(s,x)) = x
	 * 
	 * @throws Underflow
	 */
	@Test
	public void axiom6OnEmptyStack() throws Underflow {
		String x = "huhu";
		stack.push(x);
		assertEquals(x, stack.top());
	}

	@Test
	public void axiom6() throws Underflow {
		stack.push("a");
		stack.push("b");

		String x = "huhu";
		stack.push(x);
		assertEquals(x, stack.top());
	}

}
