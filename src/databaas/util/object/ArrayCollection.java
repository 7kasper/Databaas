package databaas.util.object;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import databaas.util.java.ArrayUtils;

public class ArrayCollection<T> {

	private final int offset;
	private final Object[] array;

	public ArrayCollection(int size) {
		this.array = new Object[size];
		this.offset = 0;
	}

	public ArrayCollection(Collection<Entry<T>> entries) {
		int minKey = entries.stream().min(Comparator.comparingInt(e -> e.key)).get().key;
		int maxKey = entries.stream().max(Comparator.comparingInt(e -> e.key)).get().key;
		this.offset = -minKey;
		this.array = new Object[(maxKey - minKey) + 1];
		entries.stream().forEach(entry -> put(entry.key, entry.value));
	}

	@SafeVarargs
	public ArrayCollection(Entry<T>... entries) {
		this(Arrays.asList(entries));
	}

	public int getMinKey() {
		return -offset;
	}

	public int getMaxKey() {
		return array.length + offset;
	}

	@SuppressWarnings("unchecked")
	public T get(int key) {
		return (T) ArrayUtils.get(array, key + offset);
	}

	public void put(int key, T value) {
		int aindex = key + offset;
		if ((aindex >= array.length) || (aindex < 0)) {
			throw new IllegalArgumentException(MessageFormat.format("Cant fit key {0} in size {1} and offset {2}", key, array.length, offset));
		}
		array[aindex] = value;
	}

	public void clear() {
		Arrays.fill(array, null);
	}

	public static class Entry<T> {
		private final int key;
		private final T value;
		public Entry(int key, T value) {
			this.key = key;
			this.value = value;
		}
	}

}