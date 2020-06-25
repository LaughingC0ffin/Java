package ubung7Gruppe.collection;

import java.util.Iterator;

public class EmptySet<E> implements Set<E> {

    public EmptySet() {
    }

    /**
     * Returns the number of elements stored in this set.
     *
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * Returns true if this set contains the specified element.
     *
     * @param el
     * @return true if this set contains the specified element.
     */
    @Override
    public boolean contains(Object el) {
        return false;
    }

    /**
     * Returns the union set of this set and the specified set.
     *
     * @param other a set
     * @return the union set of this set and the specified set.
     */
    @Override
    public Set<E> union(Set<E> other) {
        return other;
    }

    /**
     * returns the set resulting from adding the specified element to this set.
     *
     * @param element an element (must not be null)
     * @return the set resulting from adding the specified element to this set.
     */
    @Override
    public Set<E> add(E element) {
        return null;
    }

    /**
     * Returns the intersection of this set and the specified set.
     *
     * @param other a set
     * @return the intersection of this set and the specified set.
     */
    @Override
    public Set<E> intersection(Set<E> other) {
        return null;
    }

    /**
     * Returns true iff all elements of this set are contained in the specified set.
     *
     * @param other a set
     * @return true iff all elements of this set are contained in the specified set.
     */
    @Override
    public boolean subsetOf(Set<?> other) {
        return true;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public String toString() {
        return "EmptySet";
    }

    @Override
    public boolean equals(Object input){
        if(this == input) return false;
        if(input instanceof EmptySet) return true;
        return false;
    }

    @Override
    public int hashCode(){
        return 0;
    }
}
