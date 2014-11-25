
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
     * Devuelve el usuario contenido en el MailClient 
     */
    public String getUserName()
    {
        return user;
    }
    
    /**
     * Pide al MailServer configurado que devuelva el siguiente MailItem no leido
     */
    public MailItem getNextMailItem()
    {
        return server.getNextMailItem(user);
    }
    
    /**
     * Pide al MailServer configurado el siguiente MailItem no leido
     * Si existe, lo muestra por pantalla
     * Si no existe, informa de ello al usuario
     */
    public void printNextMailItem()
    {
        MailItem mailTemporal = server.getNextMailItem(user);
        if(mailTemporal != null){
            mailTemporal.printMailItem();
        }else{
            System.out.println("No hay nuevos Mensajes :(");
        }
    }
    
    /**
     * Crea un nuevo mensaje (MailItem) y lo envia al servidor
     */
    public void sendMailItem(String para, String mensaje)
    {
        MailItem correoNuevo = new MailItem(user, para, mensaje);
        server.post(correoNuevo);
    }
}
