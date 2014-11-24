
/**
 * Write a description of class MailItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailItem
{
    // instance variables - replace the example below with your own
    private String message;
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
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getFrom()
    {
        return from;
    }
    
    public String getTo()
    {
        return to;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void printMailItem()
    {
        System.out.println("Enviado por: " + from);
        System.out.println("Enviado a: " + to);
        System.out.println(message);
    }
}
