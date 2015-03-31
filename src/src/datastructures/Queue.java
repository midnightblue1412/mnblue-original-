/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.datastructures;

import java.io.Serializable;

/**
 *
 * @author midnightblue1412
 * @param <E> type of items to queue
 */
public class Queue<E> implements Serializable{
    private LinkedList<E> list;
    
    /*
     *  Constructors
     */
    
    //  Default constructor
    public Queue()
    {
        list = new LinkedList();
    }
    
    /*
     *  Methods
     */
    
    public void qAdd(E item)
    {
        if(item != null){
            list.add(item);
        }
    }
    
    public E qPop()
    {
       return list.popItem(0);
    }
    
    public E qFirstItem()
    {
        return list.getFirstElement();
    }
    
    public E qLastItem()
    {
        return list.getLastElement();
    }
    
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
}
