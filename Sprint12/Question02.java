/*
 * Suppose, you have class from task1 ParallelCalculator that is able to execute an operation in separate thread.
 * It uses an implementation of Runnable for this.
 * Constructor of ParallelCalculator takes 3 parameters:
 * BinaryOperator<Integer> to define an operation that will be executed,
 * int operand1 and int operand2.
 * ParallelCalculator class has not private int result field where the result of the operation
 *  is written when operation is executed.
 * You need to create Accountant class with a static method sum that takes 2 int parameters
 *  and returns their sum. The sum must be evaluated in a separate thread
 *  (please, do not call run() method of ParallelCalculator. Use start() on Thread object).
 *  Use ParallelCalculator for this. Method sum doesn't throw any checked exceptions.
 * Using Thread.sleep() is unwelcomed in this task.
 */

import java.util.function.BinaryOperator;

class ParallelCalculator implements Runnable {
    private BinaryOperator<Integer> operator;
    private int operand1;
    private int operand2;
    int result;

    public ParallelCalculator(BinaryOperator<Integer> operator, int operand1, int operand2) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void run() {
        result = operator.apply(operand1, operand2);
    }
}

class Accountant {

    public static int sum(int x, int y) {
        ParallelCalculator task = new ParallelCalculator(Integer::sum, x, y);
        Thread thread = new Thread(task);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            // nothing
        }
        return task.result;
    }
}
