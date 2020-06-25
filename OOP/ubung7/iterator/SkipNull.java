package ubung7Gruppe.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SkipNull<T> implements Iterator<T> {

    private Iterator<T> iterator;

    public SkipNull(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        //müssen wir schauen ob der der iterator noch ein element ungleich null hat
        Iterator<T> iterator2= iterator;
        while(iterator2.hasNext()){
            if(iterator2.next()!=null) return true;
        }
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws java.util.NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() {
        if(hasNext()){
            while(iterator.hasNext()){
                T tmp1= iterator.next();
                if(tmp1!=null)return tmp1;
            }
        }
        throw new NoSuchElementException("ne");
    }
}
