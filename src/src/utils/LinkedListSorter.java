/*
 * 
 */
package src.utils;

import java.util.Comparator;
import src.datastructures.LinkedList;

/**
 *
 * @author user
 */
public class LinkedListSorter {
    //  Insertion sort for linked list
    @SuppressWarnings("Unchecked")
    public static void sort(LinkedList list, Comparator comparator)
    {
        int size = list.getSize();
        if(size < 2){
            return;
        }
        
        //  Still unsure of this algorithm...
        for(int current = 1; current < size; current++)
        {
            Object o1 = list.elementAt(current);
            for(int backstep = current - 1; backstep >= 0; backstep--)
            {
                int comparison = comparator.compare(o1, list.elementAt(backstep));
                if(comparison >= 0){
                    list.moveItemFrom(current, backstep + 1);
                    break;
                }else if(comparison < 0 && backstep == 0){
                    list.moveItemFrom(current, 0);
                }
            }
        }
    }
}
