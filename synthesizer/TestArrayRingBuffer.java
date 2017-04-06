package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);

        ArrayRingBuffer<Double> x = new ArrayRingBuffer<Double>(4);
        x.enqueue(33.1); // 33.1    0    0    0
        assertEquals(33.1, x.peek(), 0.01);
        x.enqueue(44.8); // 33.1 44.8    0    0
        assertEquals(33.1, x.peek(), 0.01);
        x.enqueue(62.3); // 33.1 44.8 62.3    0
        assertEquals(33.1, x.peek(), 0.01);
        x.enqueue(-3.4); // 33.1 44.8 62.3 -3.4

        /*
        System.out.println("Elements: ");
        for (double d : x) {
            System.out.println(d);
        }

        assertEquals(33.1, x.peek(), 0.01);
        assertEquals(33.1, x.dequeue(), 0.01); // 44.8 62.3 -3.4    0 (returns 33.1)
        assertEquals(44.8, x.peek(), 0.01);
        x.enqueue(10.5);
        assertEquals(44.8, x.peek(), 0.01);
        assertEquals(44.8, x.dequeue(), 0.01);
        assertEquals(62.3, x.dequeue(), 0.01);
        assertEquals(-3.4, x.dequeue(), 0.01);
        assertEquals(10.5, x.dequeue(), 0.01);
        */
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
