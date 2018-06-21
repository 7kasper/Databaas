package databaas.util.java;

public class ArrayUtils {

	public static <T> T get(T[] array, int index) {
		if ((index >= 0) && (index < array.length)) {
			return array[index];
		} else {
			return null;
		}
	}

	public static void removeIndex(Object[] array, int index) {
		if ((index >= 0) && (index < array.length)) {
			System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
		}
	}

	public static void clear(Object[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}

}