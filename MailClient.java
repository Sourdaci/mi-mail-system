
/**
 * Write a description of class MailClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailClient
{
    // Servidor de correo asociado al cliente
    private MailServer server;
    // Nombre de usuario del cliente
    private String user;
    // Almacena el ultimo MailItem descargado del servidor
    private MailItem lastMail = null;

    /**
     * Constructor for objects of class MailClient
     */
    public MailClient(String user, MailServer server)
    {
        this.user = user;
        this.server = server;
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
        MailItem temp = server.getNextMailItem(user);
        if(temp != null){
            lastMail = temp;
        }
        return temp;
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
            lastMail = mailTemporal;
            mailTemporal.printMailItem();
        }else{
            System.out.println("No hay nuevos Mensajes :(");
        }
    }
    
    /**
     * Muestra el ultimo mensaje que has descargado del servidor
     */
    public void printLastMail(){
        if(lastMail != null){
            lastMail.printMailItem();
        }else{
            System.out.println("No hay ultimo mensaje recibido");
        }
    }
    
    /**
     * Crea un nuevo mensaje (MailItem) y lo envia al servidor
     */
    public void sendMailItem(String para, String asunto, String mensaje)
    {
        MailItem correoNuevo = new MailItem(user, para, asunto, mensaje);
        server.post(correoNuevo);
    }
    
    /**
     * Pregunta al servidor cuantos mails tiene el usuario por leer
     */
    public void getUnreadMessages()
    {
        System.out.println(user + ", tienes " + server.howManyMailItems(user) + " mensajes nuevos");
    }
    
    /**
     * Recibe del servidor el siguiente mensaje (si existe) y responde automaticamente
     * No devuelve ni imprime el mail recibido
     */
    public void getNextMailItemAndAutorespond()
    {
        MailItem tempReceived = server.getNextMailItem(user);
        if (tempReceived != null){
            lastMail = tempReceived;
            String to = tempReceived.getFrom();
            String subject = "RE: " + tempReceived.getSubject();
            String message = "Mensaje automatico.\nTardare en responder, estoy de vacaciones.\n\nMensaje que me enviaste:\n" + tempReceived.getMessage();
            MailItem correoNuevo = new MailItem(user, to, subject, message);
            server.post(correoNuevo);
        }
    }
}
