import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Server3 {
    public static void main(String[] args) throws Exception {
        int countClient = 0;
        var server = new ServerSocket(5000);
        System.out.println("Server started");
        System.out.println("Waiting for Client to Connect...");

        while (true) {
            var socket = server.accept();
            // Client Connected
            System.out.println("-- Client : " + ++countClient + " Connected --");

            ClientHandler ch = new ClientHandler(socket, countClient);
            Thread t = new Thread(ch);
            t.start();
        }
    }
}

class ClientHandler implements Runnable {
    final Socket SOCKET;
    final int CLIENT_ID;

    ClientHandler(Socket socket, int countClient) {
        this.SOCKET = socket;
        this.CLIENT_ID = countClient;
    }

    @Override
    public void run() {
        try {
            // Initialize input and output stream
            var dis = new DataInputStream(SOCKET.getInputStream());
            var dos = new DataOutputStream(SOCKET.getOutputStream());
            Scanner scn = new Scanner(System.in);

            String msg = "";

            while (!msg.equals("quit")) {
                // Receive message from client
                msg = dis.readUTF();
                System.out.println("Client " + CLIENT_ID + " : " + msg);

                // Send message to client
                if (!msg.equals("quit")) {
                    System.out.print("> ");
                    msg = scn.nextLine();
                }
                dos.writeUTF(msg);
            }

            System.out.println("-- Client : " + CLIENT_ID + " Disconnected --");

            dis.close();
            dos.close();
            SOCKET.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}