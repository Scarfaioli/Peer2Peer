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
        
    }
    
    public Set<ServerThreadThread> getServerThreadThreads() { return serverThreadThreads;}
}
