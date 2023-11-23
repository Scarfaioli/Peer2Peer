import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PeerThread extends Thread{
    private BufferedReader bufferedReader;
    
    /**
     * O construtor da classe fornece a variavel do buffer a entrada do socket
     * @param socket
     * @throws IOException
     */
    public PeerThread(Socket socket) throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
