
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
    //Objeto o asunto del mensaje
    private String subject;

    /**
     * Constructor for objects of class MailItem
     */
    public MailItem(String from, String to, String subject, String message)
    {
       this.from = from;
       this.to = to;
       this.subject = subject;
       this.message = message;
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
