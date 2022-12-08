import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client3 {
    public static void main(String[] args) throws Exception {
        var socket = new Socket("localhost", 5000);
        // Connected to Server

        // Initialize input and output stream
        var dis = new DataInputStream(socket.getInputStream());
        var dos = new DataOutputStream(socket.getOutputStream());
        Scanner scn = new Scanner(System.in);

        String msg = "";

        while (!msg.equals("quit")) {
            // Send message to server
            System.out.print("> ");
            msg = scn.nextLine();
            dos.writeUTF(msg);

            // Receive message from server
            msg = dis.readUTF();
            System.out.println("Server : " + msg);
        }
    }
}