import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Server2 {
    public static void main(String[] args) throws Exception {
        var server = new ServerSocket(5000);
        var socket = server.accept();
        // Client Connected

        // Initialize input and output stream
        var dis = new DataInputStream(socket.getInputStream());
        var dos = new DataOutputStream(socket.getOutputStream());
        Scanner scn = new Scanner(System.in);

        String msg = "";

        while (!msg.equals("quit")) {
            // Receive message from client
            msg = dis.readUTF();
            System.out.println("Client : " + msg);

            // Send message to client
            if (!msg.equals("quit")) {
                System.out.print("> ");
                msg = scn.nextLine();
            }
            dos.writeUTF(msg);
        }
    }
}