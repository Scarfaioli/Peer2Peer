import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.json.Json;
import javax.json.JsonObject;

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
    @Override
    public void run() {
        boolean flag = true;
        //Quando disparada essa thread ela executa em loop ifinito recebendo e interpretando Json dos demais peers
        //até a chamada do metodo stop ou alguma falha no try que altere a flag no catch, interrompendo a thread.
        while (flag) {
            try {
                JsonObject jsonObject = Json.createReader(bufferedReader).readObject();
                if (jsonObject.containsKey("username")) {
                    System.out.println("[" + jsonObject.getString("username") + "]: " + jsonObject.getString("message"));
                }
            } catch (Exception e) {
                flag = false;
                interrupt();
            }
        }
    }
}
