package ubung7Gruppe.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Array2dIterator<T> implements Iterator<T> {

    private final List<T> args;
    private int count;

    public Array2dIterator(T[][] arg) {
        this.args= Arrays.stream(arg).flatMap(Arrays :: stream).collect(Collectors.toList());
        this.count=-1;
    }

    public boolean hasNext(){
        return count < args.size()-1;
    }

    public T next() throws NoSuchElementException {
        if(!hasNext()){
            throw new NoSuchElementException("ne");
        }
        else count+=1;
        return args.get(count);
    }

    public List<T> getArgs() {
        return args;
    }
}
