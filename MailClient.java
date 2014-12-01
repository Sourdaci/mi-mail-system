
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
    // Contadores para estadisticas: enviados, recibidos y spam
    private int enviados = 0, recibidos = 0, sonSpam = 0;
    // Almacena el usuario que te ha enviado el mensaje de mail mas largo
    private String explayador;
    // Almacena el tamaño del mensaje mas largo
    private int longCadena = 0;
    // Almacena el ultimo mensaje de spam recibido
    private MailItem lastSpam = null;

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
            String cad = temp.getMessage();
            if(!spamSearch(cad)){
                lastMail = temp;
            }else{
                lastSpam = temp;
                temp = null;
                sonSpam += 1;
            }
            recibidos += 1;
            if(cad.length() > longCadena){
                longCadena = cad.length();
                explayador = temp.getFrom();
            }
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
        MailItem temp = server.getNextMailItem(user);
        if(temp != null){
            String cad = temp.getMessage();
            if(!spamSearch(cad)){
                lastMail = temp;
                temp.printMailItem();
            }else{
                lastSpam = temp;
                System.out.println("Siento comunicarle que este mail contenia spam y se ha descartado");
                sonSpam += 1;
            }
            recibidos += 1;
            if(cad.length() > longCadena){
                longCadena = cad.length();
                explayador = temp.getFrom();
            }
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
     * Muestra el ultimo mensaje SPAM que has descargado del servidor
     */
    public void printLastSpam(){
        if(lastSpam != null){
            lastSpam.printMailItem();
        }else{
            System.out.println("No hay ultimo SPAM recibido");
        }
    }
    
    /**
     * Crea un nuevo mensaje (MailItem) y lo envia al servidor
     */
    public void sendMailItem(String para, String asunto, String mensaje)
    {
        MailItem correoNuevo = new MailItem(user, para, asunto, mensaje);
        server.post(correoNuevo, mensaje.length());
        enviados += 1;
    }
    
    /**
     * Crea un nuevo mensaje y lo envia simulando errores de transmision
     */
    public void sendMailItemWithTransmissionError(String para, String asunto, String mensaje){
        int longiMens = mensaje.length();
        mensaje = mensaje.replace("a", "#&");
        mensaje = mensaje.replace("e", "$#");
        MailItem correoNuevo = new MailItem(user, para, asunto, mensaje);
        server.post(correoNuevo, longiMens);
        enviados += 1;
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
        MailItem temp = server.getNextMailItem(user);
        if (temp != null){
            lastMail = temp;
            String to = temp.getFrom();
            String subject = "RE: " + temp.getSubject();
            String message = "Mensaje automatico.\nTardare en responder, estoy de vacaciones.\n\nMensaje que me enviaste:\n" + temp.getMessage();
            MailItem correoNuevo = new MailItem(user, to, subject, message);
            server.post(correoNuevo, message.length());
            recibidos += 1;
            String cad = temp.getMessage();
            if(cad.length() > longCadena){
                longCadena = cad.length();
                explayador = temp.getFrom();
            }
            enviados += 1;
            if(spamSearch(cad)){
                sonSpam += 1;
                lastSpam = temp;
            }
        }
    }
    
    /**
     * Busca palabras clave para detectar si el MailItem es spam o no
     * Si tiene "oferta" o "viagra", es spam
     * Si tiene "proyecto", no es spam aunque cumpla la anterior condicion
     */
    private boolean spamSearch(String message){
        Boolean spamFound = false;
        if(!message.toLowerCase().contains("proyecto")){
            if(message.toLowerCase().contains("oferta") || message.toLowerCase().contains("viagra")){
                spamFound = true;
            }
        }
        return spamFound;
    }
    
    /**
     * Muestra las estadisticas de la cuenta de correo
     */
    public void estadisticas(){
        System.out.println(String.format("Has enviado %d mensajes", enviados));
        System.out.println(String.format("Has recibido %d mensajes", recibidos));
        if(recibidos != 0){
            float porceSpam = (((sonSpam * 1.0F) / recibidos) * 100.0F);
            System.out.print(String.format("De ellos, %d son spam (%f", sonSpam, porceSpam));
            System.out.print("%)\n");
        }else{
            System.out.println("De ellos, ninguno ha sido spam (0%)");
        }
        if(explayador != null){
            System.out.println(String.format("El usuario %s te ha enviado el mensaje mas largo", explayador));
        }else{
            System.out.println(String.format("No hay usuario con mensaje mas largo porque no hay mensajes recibidos"));
        }
    }
}
