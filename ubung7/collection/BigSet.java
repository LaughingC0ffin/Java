package ubung7Gruppe.collection;

import java.util.Iterator;

public class BigSet<E> implements Set<E> {

    private E arg;
    private  Set<E> argList;

    public BigSet(E first, Set<E> rest){
        this.arg=first;
        this.argList=rest;

    }

    /**
     * Returns the number of elements stored in this set.
     *
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return 1+ argList.size();
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        if(arg == null&& argList instanceof EmptySet) return true;
        return false;
    }

    /**
     * Returns true if this set contains the specified element.
     *
     * @param el
     * @return true if this set contains the specified element.
     */
    @Override
    public boolean contains(Object el) {
        if(el instanceof SingletonSet){
            return arg == ((SingletonSet) el).arg || argList.contains(el);
        }
        if(el instanceof BigSet){

        }
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
        if(other instanceof SingletonSet) return new BigSet<>(((SingletonSet<E>) other).arg,this);
        if(other instanceof BigSet) {
            //return new BigSet<>(((BigSet<Object>) other).arg,union());
        }
        return new EmptySet<>();
    }

    /**
     * returns the set resulting from adding the specified element to this set.
     *
     * @param element an element (must not be null)
     * @return the set resulting from adding the specified element to this set.
     */
    @Override
    public Set<E> add(E element) {
        return new BigSet<>(element,this);
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
        return arg + argList.toString();
    }

    public boolean equals(Set<E> input){
        return false;
    }

    public int hashCode(){
        return arg.hashCode()+argList.hashCode();
    }
}
