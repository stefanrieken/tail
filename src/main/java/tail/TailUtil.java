package tail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

public class TailUtil {
	public static int getStackDepth() {
		Exception ex = new Exception();
		ex.fillInStackTrace();
		return ex.getStackTrace().length;
	}

	public static Object inRecurseWrapper(Method method, Object obj, Object ... args) throws IllegalAccessException, IllegalArgumentException {
		while(true) {
			try {
				return method.invoke(obj, args);
			} catch(InvocationTargetException e) {
				Recurse recurse = (Recurse) e.getCause();
				args = recurse.args;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <TYPE, RET> RET inRecurseWrapper(Function<TYPE, RET> f, Object ... args) throws IllegalAccessException, IllegalArgumentException {
		while(true) {
			try {
				return (RET) f.apply((TYPE) args[0]);
			} catch(Recurse recurse) {
				args = recurse.args;
			}
		}
	}
}
