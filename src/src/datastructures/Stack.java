/*
 * 
 */
package src.datastructures;

import java.util.EmptyStackException;

/**
 *
 * @author user
 * @param <E>: type of stack elements
 */
public class Stack<E> {
    private Node<E> _top = null;
    
    public void push(E value)
    {
        _top = new Node<>(value, _top);
    }
    
    public E pop()
    {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        Node<E> node = _top;
        E temp = _top.value;        
        _top = _top.nextNode();
        try {
            node.unlink();
        } catch (SelfLinkException ex) {
            //  Nah
        }
        return temp;
    }
    
    public E top()
    {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return _top.value;
    }
    
    public boolean isEmpty()
    {
        return _top == null;
    }
}
