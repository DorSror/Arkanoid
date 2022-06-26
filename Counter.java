/**
 * @author Dor Sror
 * A simple counter class, used to count an integer amount.
 * Can increase, decrease and return the current count.
 */
public class Counter {
    private int counter;

    /**
     * Increases the current count by a given number.
     * @param number the given number we wish to increase the count by.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Decreases the current count by a given number.
     * @param number the given number we wish to decrease the count by.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Returns the current count.
     * @return current count.
     */
    public int getValue() {
        return this.counter;
    }
}