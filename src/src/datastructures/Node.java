/*
 * 
 */
package src.datastructures;


/**
 *
 * @author midnightblue1412
 * @param <T>: type to be contained by node instance
 */
public final class Node<T> {
    public T value;
    private Node<T> previous;
    private Node<T> next;
    
/*     
 *  Constructors
 */
    public Node(T value)
    {
        this.value = value;
    }
    
    //  @param(node): node to be linked as next to this node
    public Node(T value, Node<T> node)
    {
        this.value = value;
        try {
            linkNext(node);
        } catch (SelfLinkException ex) {
            //  Can't possibly go here...
        }
    }
    
    //  @param(node): node to be linked as previous to this node
    public Node(Node<T> node, T value)
    {
        this.value = value;
        try {
            linkPrevious(node);
        } catch (SelfLinkException ex) {
            //  Can't possibly go here...
        }
    }
    
    //  @param(node1): node to be linked as next to this node
    //  @param(node2): node to be linked as previous to this node
    public Node(Node<T> node1, T value, Node<T> node2)
    {
        this.value = value;
        try {
            linkNext(node2);
            linkPrevious(node1);
        } catch (SelfLinkException ex) {
            //  Can't possibly go here...
        }
    }

/*
 *  Accessors
 */
    
    public Node<T> nextNode()
    {
        return next;
    }
    
    public Node<T> previousNode()
    {
        return previous;
    }
    
    
/*
 *  Methods
 */
    public boolean hasNext()
    {
        return next != null;
    }
    
    public boolean hasPrevious()
    {
        return previous != null;
    }
    
    // @param (node): node to link as this node's next
    public void linkNext(Node<T> node)
        throws SelfLinkException
    {
        if(node == this){
            throw new SelfLinkException();
        }else if(node != null){
            node.previous = this;
        }
        
        next = node;
    }
    
    // @param (node): node to link as this node's previous
    public void linkPrevious(Node<T> node)
        throws SelfLinkException
    {
        if(node == this){
            throw new SelfLinkException();
        }else if(node != null){
            node.next = this;
        }
        
        previous = node;
    }
    
    //  Links this node's previous and next nodes
    //  unlinking itself to both in the process
    public void unlink()
        throws SelfLinkException{
        if(hasPrevious()){
            previous.linkNext(next);
        }else if(hasNext()){
            next.previous = null;
        }
        
        previous = null;
        next = null;
    }
    
    //  insert a node between this node
    //  and it's previous
    public void insertBefore(Node<T> node)
        throws SelfLinkException
    {
        //  If param (node) to be inserted is null,
        //  then no linking happens
        if(node == null){
            return;
        }else if(hasPrevious()){
            previous.linkNext(node);
        }
        
        node.linkNext(this);
    }
    
    //  insert a node between this node
    //  and it's next
    public void insertAfter(Node<T> node)
        throws SelfLinkException
    {
        //  If param (node) to be inserted is null,
        //  then no linking happens
        if(node == null){
            return;
        }else if(hasNext()){
            next.linkPrevious(node);
        }
        
        linkNext(node);
    }
}
