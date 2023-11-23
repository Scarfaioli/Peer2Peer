import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    }
}
