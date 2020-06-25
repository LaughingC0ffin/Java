package ubung7Gruppe.collection;

import java.util.Iterator;

public class SingletonSet<E> implements Set<E> {

    public E arg;

    public SingletonSet(E arg) {
        this.arg = arg;
    }

    /**
     * Returns the number of elements stored in this set.
     *
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return 1;
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return arg==null;
    }

    /**
     * Returns true if this set contains the specified element.
     *
     * @param el
     * @return true if this set contains the specified element.
     */
    @Override
    public boolean contains(Object el) {
        if(el instanceof EmptySet)return true;
        return el==arg;
    }

    /**
     * Returns the union set of this set and the specified set.
     *
     * @param other a set
     * @return the union set of this set and the specified set.
     */
    @Override
    public Set<E> union(Set<E> other) {
        return other.add(arg);
    }

    /**
     * returns the set resulting from adding the specified element to this set.
     *
     * @param element an element (must not be null)
     * @return the set resulting from adding the specified element to this set.
     */
    @Override
    public Set<E> add(E element) {
        return new BigSet<>(arg,this);
    }

    /**
     * Returns the intersection of this set and the specified set.
     *
     * @param other a set
     * @return the intersection of this set and the specified set.
     */
    @Override
    public Set<E> intersection(Set<E> other) {
        if(other.contains(arg)) return new SingletonSet<>(arg);
        else return new EmptySet<>();
    }

    /**
     * Returns true iff all elements of this set are contained in the specified set.
     *
     * @param other a set
     * @return true iff all elements of this set are contained in the specified set.
     */
    @Override
    public boolean subsetOf(Set<?> other) {
        if(other instanceof BigSet){
            return false;
        }
        if(other instanceof SingletonSet){
            if(other.contains(arg))return true;
            else return false;
        }
        return false;
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
        return "SingletonSet" + arg.toString();
    }

    public boolean equals(Object input){
        if(input instanceof SingletonSet ){
            return ((SingletonSet) input).arg==this.arg;
        }
        return false;
    }

    public int hashCode(){
        return arg.hashCode();
    }
}
