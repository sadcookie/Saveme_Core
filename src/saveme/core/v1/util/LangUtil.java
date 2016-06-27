package saveme.core.v1.util;

import java.util.Date;

/**
 * Saveme Language utilities
 * 
 * 
 */
public class LangUtil {
	/**
	 * Concatenate two arrays. Arrays must be given in same types.
	 * @param left  Array
	 * @param right
	 * @return Concatenated single array;
	 * @throws IllegalArgumentException  
	 */
	public static <T> T[] concatArray(T[] left, T[] right) {
		if (left == null)
			throw new IllegalArgumentException("\'left\' array cannot be null");
		if (right == null)
			throw new IllegalArgumentException("\'right\' array cannot be null");
		/* @Nullable */ T[] rn = java.util.Arrays.copyOf(left, left.length + right.length);
		System.arraycopy(right, 0, rn, left.length, right.length);
		T[] result = rn;
		return result;

	}
	
	/**
	 * truncate date milliseconds.
	 * @param date
	 * @return 
	 */
	public static Date truncateMilliseconds(/*@Nullable*/ Date date) {
		if (date != null) {
			long time = date.getTime();
			return new Date(time - (time % 1000L));
		} else
			return date;
	}
	
	
}
