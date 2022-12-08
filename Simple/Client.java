import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        var socket = new Socket("localhost", 5000);
        // Connected to Server

        // Initialize input and output stream
        var dis = new DataInputStream(socket.getInputStream());
        var dos = new DataOutputStream(socket.getOutputStream());

        // Send message to server
        String msg = "Awaaj aa rahi hai?";
        dos.writeUTF(msg);

        // Receive message from server
        msg = dis.readUTF();
        System.out.println("Server : " + msg);
    }
}