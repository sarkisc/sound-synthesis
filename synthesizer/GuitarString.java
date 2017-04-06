package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private ArrayRingBuffer<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        //       Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        if(frequency > 0) {
            int capacity = (int) Math.round(SR / frequency);
            if(capacity > 0) {
                this.buffer = new ArrayRingBuffer<Double>(capacity);
                while (!this.buffer.isFull()) {
                    buffer.enqueue(0.0);
                }
            }
        }
    }

    /* returns true if duplicates are found in the double array */
    private boolean duplicatesFound(double[] doubleArray)
        {
            int arrayLength = doubleArray.length;
            for(int i = 0; i < arrayLength; i++)
            {
                for(int j = 0; j < arrayLength; j++)
                {
                    if(doubleArray[i] == doubleArray[j] && i != j)
                        return true;
                }
            }
            return false;
        }

    /* Pluck the guitar string by replacing the buffer with white noise. */
        public void pluck() {
            //       Dequeue everything in the buffer, and replace it with random numbers
            //       between -0.5 and 0.5. You can get such a number by using:
            //       double r = Math.random() - 0.5;
            //
            //       Make sure that your random numbers are different from each other.
            double[] distinctDoubles = new double[buffer.capacity];

            while(duplicatesFound(distinctDoubles)) {
                for (int i = 0; i < buffer.capacity; i++) {
                    distinctDoubles[i] = Math.random() - 0.5;
                }
            }

            while(!buffer.isEmpty())
            {
                buffer.dequeue();
            }

            for(int i = 0; i < buffer.capacity; i++)
            {
                buffer.enqueue(distinctDoubles[i]);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        //       Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double oldFirst = buffer.dequeue();
        double newFirst = buffer.peek();
        double newItem = 0.5 * (oldFirst + newFirst) * DECAY;

        buffer.enqueue(newItem);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        //       Return the correct thing.
        return buffer.peek();
    }
}
