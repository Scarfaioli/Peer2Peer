import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread extends Thread{
    //Variaveis onde serão armazenadas o socet do servidor e a lista de
    //servidores que estão conectados, o uso do set ou inves do map se dá ao fato 
    //do Set ser thread-safegarantindo a sincronia
    private ServerSocket serverSocket;
    private Set<ServerThreadThread> serverThreadThreads = new HashSet<>();

    /**
     * Contrutor da classe chamado na main com a porta passada pelo o setupValues 
     * @param portNumb
     * @throws IOException
     */
    public ServerThread(String portNumb) throws IOException {
        serverSocket = new ServerSocket(Integer.valueOf(portNumb));
    }
    @Override
    public void run() {
        //Metodo de execução quando startada a função desta thread é apenas aceitar os socket startando a conexão do
        //server do peer com  os dos peers conectados e inserindo eles no hash
        try{
            while(true) {
                ServerThreadThread serverThreadThread = new ServerThreadThread(serverSocket.accept(), this);
                serverThreadThreads.add(serverThreadThread);
                serverThreadThread.start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    void sendMessage(String message){
        //Este metodo envia a mensagem criada no communicate e envia para cada peer mapeado no hash set
        try {
            serverThreadThreads.forEach(t-> t.getPrintWriter().println(message));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public Set<ServerThreadThread> getServerThreadThreads() { return serverThreadThreads;}
}
