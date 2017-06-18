package nit.algo;

import java.util.Stack;

/**
 * Created by nitendra.thakur on 2017/06/18.
 */
public class StackAndQueue {

    /**
     * A queue implementation using two stacks
     */
    private static class Queue1<E> {
        Stack<E> tempStack = new Stack<E>();
        Stack<E> mainStack = new Stack<E>();

        public Queue1 add(E elem) {
            while(!mainStack.isEmpty()) {
                tempStack.push(mainStack.pop());
            }
            mainStack.push(elem);
            while(!tempStack.isEmpty()) {
                mainStack.push(tempStack.pop());
            }
            return this;
        }

        public void getAll() {
            while(!mainStack.isEmpty()) {
                System.out.println(mainStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        Queue1<Integer> que = new Queue1();
        que.add(1).add(2).add(3).add(4).add(5);
        que.getAll();
    }
}
