import javax.json.Json;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Socket;

public class Peer {
    public static void main(String[] args) throws Exception {
        //Recebe valores do teclado para nome de usuario e porta para a conexão
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("> insira o nome do user e porta # para este peer:");
        //Remove os espaços visando ter um array de duas posições [0] username e [1] porta da comunicação
        String[] setupValues = bufferedReader.readLine().split(" ");

        //Cria o servidor do peer passando sua parta recebida acima e starta a thread
        ServerThread serverThread = new ServerThread(setupValues[1]);
        serverThread.start();
        //Atualiza a lista de Peers que são escuutados pela classe
        new Peer().updateListenToPeers(bufferedReader, setupValues[0], serverThread);
    }

    /**
     * Função para autalizar a lista de peers conectados
     * @param bufferedReader
     * @param username
     * @param serverThread
     * @throws Exception
     */
    public void updateListenToPeers(BufferedReader bufferedReader, String username, ServerThread serverThread) throws Exception{
        System.out.println("> entre (separando por espaço) host:porta#");
        System.out.println("  peers para receber mensagem (s para pular):");
        String input = bufferedReader.readLine();
        String[] inputValues = input.split(" ");
        
        //Quando esta função for chamada o host e porta passados serão transformados em um socket
        if(!input.equals("s")) for (int i = 0; i < inputValues.length; i++) {
            //separo o input pelo ':' de forma que address = {host, port}
            String[] address = inputValues[i].split(":");
            Socket socket = null;
            //passo address para um objeto socket em si
            try{
                socket = new Socket(address[0], Integer.valueOf(address[1]));
                new PeerThread(socket).start();
                //o socket em questão é do peer que quero me comunicar portanto é iniciada uma 
                //uma thread do peer em questão passando seu socket para comunicação
            } catch(Exception e) {
                if(socket != null){
                    //caso seja digitado qualquer outro formato que não possa ser usado o socket é fechado
                    socket.close();
                }else System.out.println("Entrada inválida. pulado para proximo passo.");
            }
        }
        //realiza a comunicação
        communicate(bufferedReader, username, serverThread);
    }
    /**
     * Este método serve para realizar a comunicação com o servidor do peer atual nele construimos e enviamos
     * as mensagens como Json
     * @param bufferedReader
     * @param username
     * @param serverThread
     */
    public void communicate(BufferedReader bufferedReader, String username, ServerThread serverThread){
        try {
            System.out.println("> Agora voce pode comunicar (e para encerrar, c para mudar)");
            boolean flag = true;
            while (flag){
                String message = bufferedReader.readLine();
                if (message.equals("e")){
                    flag = false;
                    break;
                } else if (message.equals("c")){
                    updateListenToPeers(bufferedReader, username, serverThread);
                } else{
                    StringWriter stringWriter = new StringWriter();
                    Json.createWriter(stringWriter).writeObject(Json.createObjectBuilder()
                            .add("username", username)
                            .add("message", message)
                            .build());
                    serverThread.sendMessage(stringWriter.toString());
                }
            }
            System.exit(0);
        } catch (Exception e){

        }
    }
}
