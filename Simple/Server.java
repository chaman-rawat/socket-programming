import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {
    public static void main(String[] args) throws Exception {
        var server = new ServerSocket(5000);
        var socket = server.accept();
        // Client Connected

        // Initialize input and output stream
        var dis = new DataInputStream(socket.getInputStream());
        var dos = new DataOutputStream(socket.getOutputStream());

        // Receive message from client
        String msg = dis.readUTF();
        System.out.println("Client : " + msg);

        // Send message to client
        msg = "Haan aa rhi hai!";
        dos.writeUTF(msg);
    }
}