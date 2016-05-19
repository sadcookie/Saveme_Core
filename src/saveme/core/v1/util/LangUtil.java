package saveme.core.v1.util;

import java.util.Date;

/**
 * Saveme Language utilities
 * 
 * @author KwangEun An
 * 
 */
public class LangUtil {
	/**
	 * Concatenate two arrays. Arrays must be given in same types.
	 * 
	 * @author KwangEun An
	 * @param left
	 * @param right
	 * @return Concatenated single array;
	 * @throws IllegalAccessError
	 *             if array argument is null.
	 */
	public static <T> T[] concatArray(T[] left, T[] right) {
		if (left == null)
			throw new IllegalAccessError("\'left\' array cannot be null");
		if (right == null)
			throw new IllegalAccessError("\'right\' array cannot be null");
		/* @Nullable */ T[] rn = java.util.Arrays.copyOf(left, left.length + right.length);
		System.arraycopy(right, 0, rn, left.length, right.length);
		@SuppressWarnings("nullness")
		T[] result = rn;
		return result;

	}

	public static Date truncateMilliseconds(Date date) {
		if (date != null) {
			long time = date.getTime();
			return new Date(time - (time % 1000L));
		} else
			return date;
	}
}
