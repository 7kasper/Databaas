/*
 * Copyright 2018 Kasper Müller. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License v3.0 only, as
 * published by the Free Software Foundation.
 */

package databaas.util.object;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import databaas.util.java.ArrayUtils;

/***
 * <p>
 * Array based implementation of the {@link Map} interface. This map is similar
 * to HashMap but sacrifices hashcode-order optimisation for an insertion-order
 * based array table. Note that insertion order is not affected by value update
 * using put, but if a key is removed and re-added, it will be reflected in its
 * new order. The values and keys can be retrieved based on order and
 * vice-versa. Keys can be searched by value, just as efficiently as values by
 * keys, namely not so efficient. Getting by index, however, is about as fast as
 * using a traditional array.
 * </p>
 * 
 * <p>
 * This map can be created with another map in the constructor. This copies over
 * the order in the different map as the insertion order for this map.
 * </p>
 * 
 * <p>
 * This class provides all of the optional {@link Map} operations, and permits
 * null elements. Like {@link HashMap}, it provides constant-time performance
 * for the basic operations (<tt>add</tt> and <tt>contains</tt>), but not for
 * other operations such as <tt>remove</tt>, its execution time will increase as
 * the map grows bigger, as the value is physically shifted from the array.
 * Performance is likely to be below that of {@link HashMap}, due to the looping
 * behaviour needed to get values. But getting values by index should be just
 * slightly below native array operations. Iterating should be faster than
 * {@link HashMap}, as the iteration is just walking through the array. Also
 * removing during iteration is simpler, but removing takes more time as the
 * internal array is shifted to keep a correct record.
 * </p>
 * 
 * <p>
 * A ArrayMap has two parameters that affect its performance: <i>initial
 * capacity</i> and <i>load factor</i>. They are defined precisely as for
 * {@link HashMap}.
 * </p>
 * 
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access a ArrayMap concurrently, and at least one of the
 * threads modifies the map structurally, it <i>must</i> be synchronized
 * externally. (A structural modification is any operation that adds or deletes
 * one or more mappings; merely changing the value associated with a key that an
 * instance already contains is not a structural modification.) This is
 * typically accomplished by synchronizing on some object that naturally
 * encapsulates the map. <br/>
 * If no such object exists, the map should be "wrapped" using the
 * {@link Collections#synchronizedMap Collections.synchronizedMap} method. This
 * is best done at creation time, to prevent accidental unsynchronized access to
 * the map:
 * 
 * <pre>
 *   Map m = Collections.synchronizedMap(new ArrayMap(...));
 * </pre>
 * 
 * <p>
 * The iterators returned by the <tt>iterator</tt> method of the collections
 * returned by all of this class's collection view methods are
 * <em>fail-fast</em>: if the map is structurally modified at any time after the
 * iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> method, the iterator will throw a
 * {@link ConcurrentModificationException}. Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 * </p>
 * 
 * <p>
 * Note that the fail-fast behavior of an iterator cannot be guaranteed as it
 * is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification. Fail-fast iterators throw
 * <tt>ConcurrentModificationException</tt> on a best-effort basis. Therefore,
 * it would be wrong to write a program that depended on this exception for its
 * correctness: <i>the fail-fast behavior of iterators should be used only to
 * detect bugs.</i>
 * </p>
 * 
 * @param <K>
 *            the type of keys maintained by this map
 * @param <V>
 *            the type of mapped values
 * 
 * @author Kasper Müller
 * @see HashMap
 * @see Map
 */
public class ArrayMap<K, V> extends AbstractMap<K,V> implements Map<K, V>, Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
	public static final int MAXIMUM_CAPACITY = 1 << 30;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

	protected transient ArrayMapEntry<K, V>[] table;
	protected transient int size;

	protected transient Set<Map.Entry<K, V>> entrySet = null;
	protected transient Set<K> keySet = null;
	protected transient Collection<V> values = null;

	protected final float loadFactor;
	protected int threshold;
	protected int startingCapacity;

	protected transient volatile int modCount;

	/***
	 * Constructs an empty {@link ArrayMap} with the specified initial capacity and
	 * load factor.
	 *
	 * @param initialCapacity
	 *            the initial capacity
	 * @param loadFactor
	 *            the load factor
	 * @throws IllegalArgumentException
	 *             if the initial capacity is negative or the load factor is
	 *             nonpositive
	 */
	@SuppressWarnings("unchecked")
	public ArrayMap(int initialCapacity, float loadFactor) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
		if (initialCapacity > MAXIMUM_CAPACITY)
			initialCapacity = MAXIMUM_CAPACITY;
		if (loadFactor <= 0 || Float.isNaN(loadFactor))
			throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
		// Find a power of 2 >= initialCapacity
		int capacity = 1;
		while (capacity < initialCapacity)
			capacity <<= 1;
		this.loadFactor = loadFactor;
		this.startingCapacity = capacity;
		threshold = (int) (capacity * loadFactor);
		this.table = new ArrayMapEntry[capacity];
		init();
	}

	/**
	 * Constructs an empty <tt>HashMap</tt> with the specified initial capacity and
	 * the default load factor (0.75).
	 *
	 * @param initialCapacity
	 *            the initial capacity.
	 * @throws IllegalArgumentException
	 *             if the initial capacity is negative.
	 */
	public ArrayMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty <tt>HashMap</tt> with the default initial capacity (16)
	 * and the default load factor (0.75).
	 */
	public ArrayMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs a new {@link ArrayMap} with the same mappings as the specified
	 * {@link Map}. The {@code ArrayMap} is created with default load factor (0.75)
	 * and an initial capacity sufficient to hold the mappings in the specified
	 * <tt>Map</tt>.
	 *
	 * @param map
	 *            the map whose mappings are to be placed in this map
	 * @throws NullPointerException
	 *             if the specified map is null
	 */
	public ArrayMap(Map<? extends K, ? extends V> map) {
		this(Math.max((int) (map.size() / DEFAULT_LOAD_FACTOR) + 1, DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);
		putAll(map);
	}

	/**
	 * Initialization hook for subclasses. This method is called in all constructors
	 * and pseudo-constructors (clone, readObject) after HashMap has been
	 * initialized but before any entries have been inserted. (In the absence of
	 * this method, readObject would require explicit knowledge of subclasses.)
	 */
	void init() { }

	/**
	 * @return the number of key-value mappings in this map
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return <tt>true</tt> if this map contains no key-value mappings
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @param key
	 *            The key whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map contains a mapping for the specified key.
	 */
	@Override
	public boolean containsKey(Object key) {
		return get(key) != null;
	}

	/**
	 * @param index
	 * @return If the index is contained in the ArrayMap.
	 */
	public boolean containsIndex(int index) {
		return index >= 0 && index <= table.length;
	}

	/**
	 * @param value
	 *            value whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map maps one or more keys to the specified
	 *         value.
	 */
	@Override
	public boolean containsValue(Object value) {
		return getKey(value) != null;
	}

	/**
	 * @return the entry associated with the specified key in the ArrayMap. Returns
	 *         null if the HashMap contains no mapping for the key.
	 */
	public Entry<K, V> getEntry(Object key) {
		for (int i = 0; i < size; i++) {
			if ((Objects.hashCode(table[i].getKey()) == Objects.hashCode(key)) &&
					table[i].getKey().equals(key)) {
				return table[i];
			}
		}
		return null;
	}

	/**
	 * @return the entry associated with the first matching value in the HashMap.
	 *         Returns null if the ArrayMap contains no mapping for the value.
	 */
	public Entry<K, V> getEntryFromValue(Object value) {
		for (int i = 0; i < size; i++) {
			if ((Objects.hashCode(table[i].getValue()) == Objects.hashCode(value)) &&
					table[i].getValue().equals(value)) {
				return table[i];
			}
		}
		return null;
	}

	/**
	 * <p>
	 * Returns the value to which the specified key is mapped, or {@code null} if
	 * this map contains no mapping for the key.
	 * </p>
	 * 
	 * <p>
	 * A return value of {@code null} does not <i>necessarily</i> indicate that the
	 * map contains no mapping for the key; it's also possible that the map
	 * explicitly maps the key to {@code null}. The {@link #containsKey} operation
	 * may be used to distinguish these two cases.
	 * </p>
	 *
	 * @see #get(int)
	 * @see #getKey(Object)
	 * @see #getKey(int)
	 * @see #put(Object, Object)
	 * 
	 * @param key
	 *            the key to get the value.
	 * 
	 * @return The value beloning to key.
	 */
	@Override
	public V get(Object key) {
		Entry<K,V> entry = getEntry(key);
		return entry == null ? null : entry.getValue();
	}

	/**
	 * <p>
	 * Returns the value of the specified index, or {@code null} if this map
	 * contains no value for the index.
	 * </p>
	 * 
	 * <p>
	 * A return value of {@code null} does not <i>necessarily</i> indicate that the
	 * map contains no mapping for the index; it's also possible that the map
	 * explicitly maps the key to {@code null}. The {@link #containsIndex} operation
	 * may be used to distinguish these two cases.
	 * </p>
	 *
	 * @see #get(Object)
	 * @see #getKey(Object)
	 * @see #getKey(int)
	 * @see #put(Object, Object)
	 * 
	 * @param index
	 *            the index from which to get the value
	 * 
	 * @return The value belonging to the index.
	 */
	public V get(int index) {
		ArrayMapEntry<K, V> entry = ArrayUtils.get(table, index);
		return entry == null ? null : entry.getValue();
	}

	/**
	 * <p>
	 * Returns the first key to which the specified value is mapped to, or
	 * {@code null} if this map contains no mapping for the value or the value is
	 * mapped to a null key.
	 * </p>
	 * 
	 * <p>
	 * A return value of {@code null} does not <i>necessarily</i> indicate that the
	 * map contains no mapping for the value; it's also possible that the map
	 * explicitly maps the value to {@code null}. The {@link #containsValue}
	 * operation may be used to distinguish these two cases.
	 * </p>
	 *
	 * @see #get(Object)
	 * @see #get(int)
	 * @see #getKey(int)
	 * @see #put(Object, Object)
	 * 
	 * @param value
	 *            the value to find the first matching key.
	 * 
	 * @return The key belonging to value.
	 */
	public K getKey(Object value) {
		Entry<K,V> entry = getEntryFromValue(value);
		return entry == null ? null : entry.getKey();
	}

	/**
	 * <p>
	 * Returns the key of the specified index, or {@code null} if this map contains
	 * no key for the index.
	 * </p>
	 * 
	 * <p>
	 * A return value of {@code null} does not <i>necessarily</i> indicate that the
	 * map contains no mapping for the index; it's also possible that the map
	 * explicitly maps the index to a key of {@code null}. The
	 * {@link #containsIndex} operation may be used to distinguish these two cases.
	 * </p>
	 *
	 * @see #get(Object)
	 * @see #get(int)
	 * @see #getKey(Object)
	 * @see #put(Object, Object)
	 * 
	 * @param index
	 *            the index from which to get the key.
	 * 
	 * @return The value belonging to the index.
	 */
	public K getKey(int index) {
		ArrayMapEntry<K, V> entry = ArrayUtils.get(table, index);
		return entry == null ? null : entry.getKey();
	}

	/**
	 * @param key
	 * @return the index of key in the map. -1 is returned if the map did not
	 *         contain key.
	 */
	public int indexOfKey(Object key) {
		for (int i = 0; i < size; i++) {
			if ((Objects.hashCode(table[i].getKey()) == Objects.hashCode(key)) &&
					table[i].getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param value
	 * @return the first index of value in the map. -1 is returned if the map did
	 *         not contain value.
	 */
	public int indexOfValue(Object value) {
		for (int i = 0; i < size; i++) {
			if ((Objects.hashCode(table[i].getValue()) == Objects.hashCode(value)) &&
					table[i].getValue().equals(value)) {
				return i;
			}
		}
		return -1;
	}

    /**
	 * Associates the specified value with the specified key in this map. If the map
	 * previously contained a mapping for the key, the old value is replaced and the
	 * order association is kept. Otherwise this association will also be with the
	 * <tt>size</tt> of the {@link ArrayMap} before the operation. In other words,
	 * this insertion can also be retrieved based on its insertion index if no value
	 * is replaced.
	 *
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if
	 *         there was no mapping for <tt>key</tt>. (A <tt>null</tt> return can
	 *         also indicate that the map previously associated <tt>null</tt> with
	 *         <tt>key</tt>.)
	 */
	@Override
	public V put(K key, V value) {
		Entry<K, V> previous = getEntry(key);
		if (previous != null) {
			V previousValue = previous.getValue();
			previous.setValue(value);
			return previousValue;
		}
		table[size] = new ArrayMapEntry<>(key, value);
        if (size++ >= threshold) {
        	resize(table.length * 2);
        }
		return null;
	}

	/**
	 * Removes the mapping for the specified key from this map if present.
	 *
	 * @param key
	 *            key whose mapping is to be removed from the map
	 * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if
	 *         there was no mapping for <tt>key</tt>. (A <tt>null</tt> return can
	 *         also indicate that the map previously associated <tt>null</tt> with
	 *         <tt>key</tt>.)
	 */
	@Override
	public V remove(Object key) {
		for (int i = 0; i < size; i++) {
			if ((Objects.hashCode(table[i].getKey()) == Objects.hashCode(key)) &&
					table[i].getKey().equals(key)) {
				V previous = table[i].getValue();
				ArrayUtils.removeIndex(table, i);
				return previous;
			}
		}
		return null;
	}

	/**
	 * Removes the mapping for the specified index from this map if present.
	 *
	 * @param index
	 *            the index of which to remove the mapping.
	 * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if
	 *         there was no mapping for <tt>key</tt>. (A <tt>null</tt> return can
	 *         also indicate that the map previously associated <tt>null</tt> with
	 *         <tt>key</tt>.)
	 */
	public V remove(int index) {
		ArrayMapEntry<K, V> previousEntry = ArrayUtils.get(table, index);
		ArrayUtils.removeIndex(table, index);
		return previousEntry == null ? null : previousEntry.getValue();
	}

	/**
	 * Removes the first mapping with the specified value.
	 *
	 * @param value
	 *            value whose mapping is to be removed from the map
	 * @return the previous key associated with <tt>value</tt>, or <tt>null</tt> if
	 *         there was no mapping for <tt>value</tt>. (A <tt>null</tt> return can
	 *         also indicate that the map previously associated <tt>null</tt> with
	 *         <tt>key</tt>.) So a preceding check <tt>containsKey(null)</tt> has
	 *         to be executed.
	 */
	public K removeFirstWithValue(Object value) {
		for (int i = 0; i < size; i++) {
			if ((Objects.hashCode(table[i].getValue()) == Objects.hashCode(value)) &&
					table[i].getValue().equals(value)) {
				K previousKey = table[i].getKey();
				ArrayUtils.removeIndex(table, i);
				return previousKey;
			}
		}
		return null;
	}

	/**
	 * Copies all of the mappings from the specified map to this map. These mappings
	 * will replace any mappings that this map had for any of the keys currently in
	 * the specified map.
	 *
	 * @param map
	 *            mappings to be stored in this map
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		int numKeysToBeAdded = map == null ? 0 : map.size();
		if (numKeysToBeAdded == 0)
			return;
		if (numKeysToBeAdded > threshold) {
			int targetCapacity = (int) (numKeysToBeAdded / loadFactor + 1);
			if (targetCapacity > MAXIMUM_CAPACITY)
				targetCapacity = MAXIMUM_CAPACITY;
			int newCapacity = table.length;
			while (newCapacity < targetCapacity)
				newCapacity <<= 1;
			if (newCapacity > table.length)
				resize(newCapacity);
		}
		map.forEach((key, value) -> put(key, value));
	}

	/**
	 * <p>
	 * Returns a view of the portion of this map between the specified
	 * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive. (If
	 * {@code fromIndex} and {@code toIndex} are equal, the returned list is empty.)
	 * The returned map is backed by this map, so non-structural changes in the
	 * returned map are reflected in this map, and vice-versa. The returned map does
	 * not support removing operations, only changes in values are accepted.
	 * </p>
	 *
	 * @param fromIndex
	 *            low endpoint (inclusive) of the subMap
	 * @param toIndex
	 *            high endpoint (exclusive) of the subMap
	 * @return a view of the specified range within this map.
	 * @throws IndexOutOfBoundsException
	 *             - for an illegal endpoint index value (fromIndex < 0 || toIndex >
	 *             size || fromIndex > toIndex)
	 */
	public Map<K, V> subMap(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		ArrayMap<K, V> sub = new ArrayMap<>();
		for (int i = fromIndex; i < toIndex; i++) {
			Entry<K, V> entry = table[i];
			sub.put(entry.getKey(), entry.getValue());
		}
		return sub;
	}

	/**
	 * Removes all entries from index for a length of length.
	 * 
	 * @param index
	 *            from where to start removing (inclusive)
	 * @param length
	 *            how many entries to remove.
	 * @throws IndexOutOfBoundsException
	 *             - for an illegal endpoint index value (index < 0 || length <
	 *             0 || length > size)
	 */
	public void removeFrom(int index, int length) {
		if (index < 0 || length < 0 || length > size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < length; i++) {
			remove(index);
		}
	}

	/**
	 * Removes all of the mappings from this map. The map will be empty after this
	 * call returns and its size will be reset.
	 */
	@Override
	public void clear() {
		resize(startingCapacity);
		ArrayUtils.clear(table);
		size = 0;
	}

	/**
	 * Returns a {@link Set} view of the keys contained in this map. The set is
	 * backed by the map, so changes to the map are reflected in the set, and
	 * vice-versa. If the map is modified while an iteration over the set is in
	 * progress (except through the iterator's own <tt>remove</tt> operation), the
	 * results of the iteration are undefined. The set supports element removal,
	 * which removes the corresponding mapping from the map, via the
	 * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>,
	 * <tt>retainAll</tt>, and <tt>clear</tt> operations. It does not support the
	 * <tt>add</tt> or <tt>addAll</tt> operations.
	 */
	@Override
    public Set<K> keySet() {
        return (keySet != null ? keySet : (keySet = new KeySet()));
    }

	/**
	 * Returns a {@link Collection} view of the values contained in this map. The
	 * collection is backed by the map, so changes to the map are reflected in the
	 * collection, and vice-versa. If the map is modified while an iteration over
	 * the collection is in progress (except through the iterator's own
	 * <tt>remove</tt> operation), the results of the iteration are undefined. The
	 * collection supports element removal, which removes the corresponding mapping
	 * from the map, via the <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>,
	 * <tt>removeAll</tt>, <tt>retainAll</tt> and <tt>clear</tt> operations. It does
	 * not support the <tt>add</tt> or <tt>addAll</tt> operations.
	 */
	@Override
    public Collection<V> values() {
        return (values != null ? values : (values = new Values()));
    }

	/**
	 * Returns a {@link Set} view of the mappings contained in this map. The set is
	 * backed by the map, so changes to the map are reflected in the set, and
	 * vice-versa. If the map is modified while an iteration over the set is in
	 * progress (except through the iterator's own <tt>remove</tt> operation, or
	 * through the <tt>setValue</tt> operation on a map entry returned by the
	 * iterator) the results of the iteration are undefined. The set supports
	 * element removal, which removes the corresponding mapping from the map, via
	 * the <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>,
	 * <tt>retainAll</tt> and <tt>clear</tt> operations. It does not support the
	 * <tt>add</tt> or <tt>addAll</tt> operations.
	 *
	 * @return a set view of the mappings contained in this map
	 */
	@Override
    public Set<Map.Entry<K,V>> entrySet() {
        return entrySet != null ? entrySet : (entrySet = new EntrySet());
    }

	protected static class ArrayMapEntry<K, V> implements Entry<K, V> {

		private final K key;
		private V value;

		public ArrayMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V previous = value;
			this.value = value;
			return previous;
		}

	}

	private void resize(int newCapacity) {
		ArrayMapEntry<K, V>[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        table = Arrays.copyOf(table, newCapacity);
        threshold = (int)(newCapacity * loadFactor);
    }

	private abstract class ArrayMapInterator<E> implements Iterator<E> {
		int expectedModCount; //For fast-fail
		int index; //current slot
		ArrayMapInterator() {
			expectedModCount = modCount;
		}

		public final boolean hasNext() {
			return index < size;
		}

		public final Entry<K, V> nextEntry() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			Entry<K, V> next = ArrayUtils.get(table, index++);
			if (next == null) {
				throw new NoSuchElementException();
			}
			return next;
		}

		public void remove() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			ArrayMap.this.remove(index);
			expectedModCount = modCount;
		}

	}

    private final class ValueIterator extends ArrayMapInterator<V> {
        public V next() {
            return nextEntry().getValue();
        }
    }

    private final class KeyIterator extends ArrayMapInterator<K> {
        public K next() {
            return nextEntry().getKey();
        }
    }

    private final class EntryIterator extends ArrayMapInterator<Map.Entry<K,V>> {
        public Entry<K,V> next() {
            return nextEntry();
        }
    }

	protected Iterator<K> newKeyIterator() {
		return new KeyIterator();
	}

	protected Iterator<V> newValueIterator() {
		return new ValueIterator();
	}

	protected Iterator<Map.Entry<K, V>> newEntryIterator() {
		return new EntryIterator();
	}

	private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

		public Iterator<Map.Entry<K, V>> iterator() {
			return newEntryIterator();
		}

		public boolean contains(Object o) {
			if (!(o instanceof Map.Entry))
				return false;
			@SuppressWarnings("unchecked")
			Map.Entry<K, V> e = (Map.Entry<K, V>) o;
			Entry<K, V> candidate = getEntry(e.getKey());
			return candidate != null && candidate.equals(e);
		}

		public boolean remove(Object o) {
			return removeFirstWithValue(o) != null;
		}

		public int size() {
			return size;
		}

		public void clear() {
			ArrayMap.this.clear();
		}

	}

	private final class KeySet extends AbstractSet<K> {

		public Iterator<K> iterator() {
			return newKeyIterator();
		}

		public int size() {
			return size;
		}

		public boolean contains(Object o) {
			return containsKey(o);
		}

		public boolean remove(Object o) {
			return ArrayMap.this.remove(o) != null;
		}

		public void clear() {
			ArrayMap.this.clear();
		}

	}

	private final class Values extends AbstractCollection<V> {

		public Iterator<V> iterator() {
			return newValueIterator();
		}

		public int size() {
			return size;
		}

		public boolean contains(Object o) {
			return containsValue(o);
		}

		public void clear() {
			ArrayMap.this.clear();
		}

	}

	/**
	 * Save the state of the <tt>ArrayMap</tt> instance to a stream (i.e., serialize
	 * it).
	 *
	 * @serialData The <i>capacity</i> of the HashMap (the length of the bucket
	 *             array) is emitted (int), followed by the <i>size</i> (an int, the
	 *             number of key-value mappings), followed by the key (Object) and
	 *             value (Object) for each key-value mapping. The key-value mappings
	 *             are emitted in insertion order.
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		Iterator<Map.Entry<K, V>> i = (size > 0) ? entrySet().iterator() : null;
		// Write out the threshold, loadfactor, and any hidden stuff
		s.defaultWriteObject();
		// Write out number of buckets
		s.writeInt(table.length);
		// Write out size (number of Mappings)
		s.writeInt(size);
		// Write out keys and values (alternating)
		if (i != null) {
			while (i.hasNext()) {
				Map.Entry<K, V> e = i.next();
				s.writeObject(e.getKey());
				s.writeObject(e.getValue());
			}
		}
	}

	/**
	 * Reconstitute the <tt>ArrayMap</tt> instance from a stream (i.e., deserialize
	 * it).
	 */
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		// Read in the threshold, loadfactor, and any hidden stuff
		s.defaultReadObject();
		// Read in number of buckets and allocate the bucket array;
		int numBuckets = s.readInt();
		table = new ArrayMapEntry[numBuckets];
		init();
		// Read in size (number of Mappings)
		int readSize = s.readInt();
		// Read the keys and values, and put the mappings in the HashMap
		for (int i = 0; i < readSize; i++) {
			K key = (K) s.readObject();
			V value = (V) s.readObject();
			put(key, value);
		}
	}

	/**
	 * Returns a shallow copy of this {@link ArrayMap} instance: the keys and values
	 * themselves are not cloned.
	 *
	 * @return a shallow copy of this map
	 */
	@SuppressWarnings("unchecked")
	public Object clone() {
        ArrayMap<K, V> clone = null;
        try {
        	clone = (ArrayMap<K, V>)super.clone();
        } catch (CloneNotSupportedException e) {
            // assert false;
        }
        clone.table = new ArrayMapEntry[table.length];
        clone.entrySet = null;
        clone.modCount = 0;
        clone.size = 0;
        clone.init();
        clone.putAll(this);
        return clone;
	}

}
