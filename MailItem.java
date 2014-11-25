
/**
 * Write a description of class MailItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailItem
{
    // Contenido del mail
    private String message;
    // Quien envia el mail y quien lo recibe
    private String from, to;

    /**
     * Constructor for objects of class MailItem
     */
    public MailItem(String enviadoPor, String enviarA, String contenido)
    {
       from = enviadoPor;
       to = enviarA;
       message = contenido;
    }

    /**
     * Devuelve quien ha creado el mail
     */
    public String getFrom()
    {
        return from;
    }
    
    /**
     * Devuelve para quien es el mail
     */
    public String getTo()
    {
        return to;
    }
    
    /**
     * Devuelve el mensaje contenido en el mail
     */
    public String getMessage()
    {
        return message;
    }
    
    /**
     * Imprime por pantalla el mensaje
     */
    public void printMailItem()
    {
        System.out.println(from + ", has recibido un mensaje de: " + to);
        System.out.println(message);
    }
}
