package ubung7Gruppe.collection;

import java.util.Arrays;

/**
 * A set of elements that does not contain any element twice.
 *
 * @param <E> the  type of all contained elements.
 */
public interface Set<E> extends Iterable<E> {

    /**
     * Returns the number of elements stored in this set.
     *
     * @return the number of elements in this set
     */
    int size();

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements
     */
    boolean isEmpty();

    /**
     * Returns true if this set contains the specified element.
     *
     * @return true if this set contains the specified element.
     */
    boolean contains(Object el);

    /**
     * Returns the union set of this set and the specified set.
     *
     * @param other a set
     * @return the union set of this set and the specified set.
     */
    Set<E> union(Set<E> other);

    /**
     * returns the set resulting from adding the specified element to this set.
     *
     * @param element an element (must not be null)
     * @return the set resulting from adding the specified element to this set.
     */
    Set<E> add(E element);

  /**
   * Returns the intersection of this set and the specified set.
   *
   * @param other a set
   * @return the intersection of this set and the specified set.
   */
  Set<E> intersection(Set<E> other);

  /**
   * Returns true iff all elements of this set are contained in the specified set.
   *
   * @param other a set
   * @return true iff all elements of this set are contained in the specified set.
   */
  boolean subsetOf(Set<?> other);

  /**
   * Returns the empty set.
   *
   * @param <T> the element type of the returned set.
     * @return the empty set.
     */
    @SuppressWarnings("unchecked")
    static <T> Set<T> makeSet() {
        return new EmptySet<>();
    }

    /**
     * Returns the singleton set containing the specified element.
     *
     * @param element an element (must not be null)
     * @param <T>     the element type of the returned set.
     * @return the singleton set containing the specified element.
     */
    static <T> Set<T> makeSet(T element) {
        return new SingletonSet<>(element);
    }

    /**
     * Returns a set containing the specified elements. The specified elements may contain equal elements.
     *
     * @param elems elements of the returned set (may contain equal elements)
     * @param <T>   the element type of the returned set.
     * @return a set containing the specified elements.
     */
    @SuppressWarnings("unchecked")
    static <T> Set<T> makeSet(T... elems) {
        if(elems==null)return new EmptySet<>();
        if(elems.length==1) return new SingletonSet(elems[0]);
        else {
            return new BigSet(elems[0], makeSet(Arrays.copyOfRange(elems, 1, elems.length - 1)));
        }
    }
}