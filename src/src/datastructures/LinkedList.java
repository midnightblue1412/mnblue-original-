/*
 * 
 */
package src.datastructures;

import java.util.Iterator;

/**
 *
 * @author midnightblue1412
 * @param <E>: type of elements to be stored in collection
 */
public class LinkedList<E> implements Iterable<E>{
    private Node<E> first;
    private Node<E> last;
    private int size;
    
/*
 *  Constructor
 */
    public LinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }

/*
 *  Accessors
 */
    public E getFirstElement()
    {
        return first.value;
    }
    
    public E getLastElement()
    {
        return last.value;
    }
    
    public E elementAt(int index)
    {
        Enumerator enumerator = iterator();
        enumerator.traverse(index);
        return enumerator.next();
    }
    
/*
 *  Methods
 */    
    public boolean isEmpty()
    {
        return first == null;
    }
    
    //  Adds a new node to the end of the list
    //  @param(value): value to be held by the new node
    public void add(E value)
    {
        if(isEmpty()){
            first = new Node<>(value);
            last = first;
        }else{
            last = new Node<>(last, value);
        }
        size++;
    }
    
    //  insert a new item at spcified index
    public void insert(E value, int index)    
    {
        if(index == size){
            add(value);
            return;
        }
        
        Enumerator enumerator = iterator();
        enumerator.traverse(index);
        try {
            enumerator.current.insertBefore(new Node<>(value));
        } catch (SelfLinkException ex) {
            //  Nah.. no going here...
        }
        
        size++;
    }
    
    //  Returns the value at the specified index and removes it from the list
    public E popItem(int index)
    {
        E temp = elementAt(index);
        remove(index);
        return temp;
    }
    
    //  Move an item to another location in the list
    public void moveItemFrom(int itemLocation, int destination)
    {
        if(itemLocation < 0 || destination < 0 ||
           itemLocation >= size || destination >= size){
            throw new IndexOutOfBoundsException();           
        }else if(itemLocation == destination){
            return;
        }
        
        Enumerator enumerator = iterator();      
        enumerator.traverse(itemLocation);
        Node<E> temp = enumerator.current;  
        enumerator.remove();
                
        if(destination == 0){
            try {
                first.insertBefore(temp);   
                first = first.previousNode();
                size++;
            } catch (SelfLinkException ex) {
                //  nah....
            }
        }else if(destination == size){
            try {
                last.insertAfter(temp);   
                last = last.nextNode();
                size++;
            } catch (SelfLinkException ex) {
                //  nah....
            }
        }if(itemLocation > destination){
            //  from higher index to lower index
            enumerator.traverse(destination);
            try {
                enumerator.current.insertBefore(temp);   
                size++;
            } catch (SelfLinkException ex) {
                //  nah....
            }
        }else if(itemLocation < destination){
            //  from lower index to higher index
            enumerator.traverse(destination - 1);
            try {
                enumerator.current.insertAfter(temp);
                size++;
            } catch (SelfLinkException ex) {
                //  Nah nah nah...
            }
        }
    }
    
    //  Returns the index of param(value) in the list
    //  If value is not found, then return -1
    public int indexOf(E value)
    {
        Enumerator enumerator = iterator();
        for(int i = 0; i< size; i++)
        {
            if(enumerator.current.value.equals(value)){
                return i;
            }
            enumerator.next();
        }
        
        return -1;
    }
    
    //  Removes the first of occurence of param(value) from the collection
    public void remove(E value)
    {
        //  TODO: add implementation
        int index = indexOf(value);
        if(index >= 0){
            remove(index);
            size--;
        }        
    }
    
    //  Removes element at specified index
    public void remove(int index)
    {        
        Enumerator enumerator = iterator();
        enumerator.traverse(index);   
        enumerator.remove();
    }
    
    public Enumerator iterator()
    {
        return new Enumerator();
    }

    public int getSize() {
        return size;
    }
    
/*
 *  Inner class for this collection's iterator/enumerator
 */
    public final class Enumerator implements Iterator<E>
    {
        private Node<E> current;
        
        public Enumerator()
        {
            begin();
        }
        
        public void begin()
        {
            current = first;
        }
        
        public void end()
        {
            current = last;
        }
        
        public E previous()
        {
            E temp = current.value;
            current = current.previousNode();
            return temp;
        }
        
        public E next()
        {
            E temp = current.value;
            current = current.nextNode();
            return temp;
        }
        
        public boolean hasNext()
        {
            return current != null;
        }
        
        public void remove()
        {
            Node<E> tmp = current;
            
            if(current == first){
                first = first.nextNode();
            }else if(current == last){
                last = last.previousNode();
            }else{
                current = current.nextNode();
            }
            
            
            try {            
                tmp.unlink();
            } catch (SelfLinkException ex) {
                //  Nah.... not going here...
            }
            size--;
        }
        
        public void traverse(int index)
        {
            if(index < 0 || index >= size){
                throw new IndexOutOfBoundsException("for index" + index);
            }else if(index > size / 2){
                //  Not so sure about this yet...
                end();
                for(int i = 0; i < (size - 1) - index; i++)
                {
                    previous();
                }
            }else{
                begin();
                for(int i = 0; i < index; i++){
                    next();
                }
            }            
        }       
    }
}
