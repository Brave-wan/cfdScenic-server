import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;


/**
 * Ognl工具类，主要是为了在ognl表达式访问静态方法时可以减少长长的类名称编写 Ognl访问静态方法的表达式为: @class@method(args)
 * 
 * 示例使用:
 * 
 * <pre>
 * 	&lt;if test=&quot;@Ognl@isNotEmpty(userId)&quot;&gt;
 * 	and user_id = #{userId}
 * &lt;/if&gt;
 * </pre>
 * 
 * @author badqiu
 * 
 */
public class Ognl {

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String) {
			String str = (String) o;
			if (str.length() == 0) {
				return true;
			}
		}
		if (o instanceof Collection) {
			Collection col = (Collection) o;
			if (col.isEmpty())
				return true;
		}
		if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		}
		if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	public static boolean isNotBlank(Object o) {
		return !isBlank(o);
	}

	public static boolean isBlank(Object o) {
		if (o == null)
			return true;
		if (o instanceof String) {
			String str = (String) o;
			return isBlank(str);
		}
		if (o instanceof Collection || o.getClass().isArray() || o instanceof Map) {
			return isEmpty(o);
		}
		return false;
	}

	public static boolean isBlank(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	public static boolean isEqui(long one,long two) {
		if(one == two){
			return true;
		}
		return false;
	}
	public static boolean isNotEqui(long one,long two) {
		return !isEqui(one,two);
	}
}
