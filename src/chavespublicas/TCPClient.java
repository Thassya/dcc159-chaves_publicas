
import chavespublicas.RSA;
import java.io.*;
import java.net.*;

class TCPClient {

    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        RSA rsa = new RSA();

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("172.18.21.23", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence = inFromUser.readLine();

        String msgEncriptada = rsa.rsa_encrypt(sentence);
       
        outToServer.writeBytes(msgEncriptada + '\n');

        modifiedSentence = inFromServer.readLine();

        String msgDecriptada = rsa.rsa_decrypt(modifiedSentence);
       
        System.out.println("FROM SERVER: " + msgDecriptada);

        clientSocket.close();

    }
}
