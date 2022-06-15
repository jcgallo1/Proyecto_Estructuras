
package TDAS;

import java.io.Serializable;

public class EmptyListException extends RuntimeException implements Serializable {
    
    public EmptyListException(){
        super("La lista está vacía.");
    }
    
}
