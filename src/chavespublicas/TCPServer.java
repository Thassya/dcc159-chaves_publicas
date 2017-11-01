
import chavespublicas.RSA;
import java.io.*;
import java.net.*;

class TCPServer {

    public static void main(String argv[]) throws Exception {

        RSA rsa = new RSA();
        
        String clientSentence;
        String capitalizedSentence;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {

            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();
            System.out.println("msg recebida: " + clientSentence);
            String msgDecriptada = rsa.rsa_decrypt(clientSentence);
            System.out.println("msg decriptada: " + msgDecriptada);
            
            capitalizedSentence = msgDecriptada.toUpperCase() + '\n';
            System.out.println("msg maiuscula: " + capitalizedSentence);
            
            capitalizedSentence = rsa.rsa_encrypt(capitalizedSentence);
            System.out.println("msg encriptada maiuscula: " + capitalizedSentence);

            outToClient.writeBytes(capitalizedSentence + '\n');
        }
    }
}
