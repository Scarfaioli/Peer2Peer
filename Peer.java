import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Peer {
    public static void main(String[] args) throws IOException {
        //Recebe valores do teclado para nome de usuario e porta para a conexão
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("> insira o nome do user e porta # para este peer:");
        //Remove os espaços visando ter um array de duas posições [0] username e [1] porta da comunicação
        String[] stupValues = bufferedReader.readLine().split(" ");
    }
}
