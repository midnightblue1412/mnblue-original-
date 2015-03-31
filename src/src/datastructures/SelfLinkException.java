/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.datastructures;

/**
 *
 * @author user
 */
public class SelfLinkException extends Exception{
    public SelfLinkException()
    {
        super("Node cannot be linked to itself!");
    }
}
