import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreadThread extends Thread{
    private ServerThread serverThread;
    private Socket socket;
    private PrintWriter printWriter;
    public ServerThreadThread(Socket socket, ServerThread serverThread){
        this.socket = socket;
        this.serverThread = serverThread;
    }

    @Override
    public void run() {
        //Quando iniciada essa classe é responsavel por receber o input do socket que está comunicando e encaminha-lo
        //ao servidor do peer que irá encaminha-la aos demais peers
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (true) serverThread.sendMessage(bufferedReader.readLine());
        } catch (Exception e) {
            serverThread.getServerThreadThreads().remove(this);
        }
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }
}
