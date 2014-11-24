
/**
 * Write a description of class MailClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailClient
{
    // instance variables - replace the example below with your own
    private MailServer server;
    private String user;

    /**
     * Constructor for objects of class MailClient
     */
    public MailClient(String nuevoUser, MailServer nuevoServer)
    {
        user = nuevoUser;
        server = nuevoServer;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getUserName()
    {
        return user;
    }
    
    public MailItem getNextMailItem()
    {
        return server.getNextMailItem(user);
    }
    
    public void printNextMailItem()
    {
        MailItem mailTemporal = server.getNextMailItem(user);
        if(mailTemporal != null){
            System.out.println("Has recibido un mensaje de " + mailTemporal.getFrom());
            System.out.println(mailTemporal.getMessage());
        }else{
            System.out.println("No hay nuevos Mensajes");
        }
    }
}
