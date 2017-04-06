package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;    // protected: only subclasses of AbstractBoundedQueue
    protected int capacity;     // can access these vars

    public int capacity()
    {
        return capacity;
    }
    public int fillCount()
    {
        return fillCount;
    }


}