package tail;

import java.util.function.Function;

/**
 * Tail call optimization by means of throwing exceptions.
 */
public class Tail {
	public static boolean DO_THROW=false;

	public static void main (String ... args) throws Exception {
		Tail t = new Tail();

		System.out.println("Stack depth during tail call");
		System.out.println("============================\n");
		
		int callDepth = 1000;

		System.out.printf("Normal invocation : instance %5d, static %5d\n",
				t.tail(callDepth),
				Tail.tailStatic(callDepth));

		//DO_THROW = true;

		System.out.printf("Function based    : instance %5d, static %5d\n",
				TailUtil.inRecurseWrapper((Function<Integer, Integer>) t::tail, callDepth),
				TailUtil.inRecurseWrapper((Function<Integer, Integer>) Tail::tailStatic, callDepth));

		System.out.printf("Reflection based  : instance %5d, static %5d\n",
				TailUtil.inRecurseWrapper(Tail.class.getMethod("tail", Integer.class), t, callDepth),
				TailUtil.inRecurseWrapper(Tail.class.getMethod("tailStatic", Integer.class), null, callDepth));
	}

	public int tail(Integer num) {
		if (num == 0) return TailUtil.getStackDepth();

		if (DO_THROW) throw new Recurse(num-1); else
		return tail(num-1);
	}

	public static int tailStatic(Integer num) {
		if (num == 0) return TailUtil.getStackDepth();
		
		if (DO_THROW) throw new Recurse(num-1); else
		return tailStatic(num-1);
	}
}