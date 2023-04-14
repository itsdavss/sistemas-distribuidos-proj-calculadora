import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import util.MsqReq;
import util.MsqResp;
import util.Status;

public class Client {
    public static void main(String[] args) {
        int porta = 54321;
        Socket socket;
        ObjectOutputStream out;
        ObjectInputStream in;
        String ip = "127.0.0.1"; //localhost
        Scanner entrada = new Scanner(System.in);
        double value1, value2;
        char oper;

        try {
            // o cliente esta pedindo conexão  com o servidor que está no IP e Porta informados
            socket = new Socket(ip, porta);
            System.out.println("Conectado com o servidor");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        try {

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Digite a operação (+, -, *, /)");
            oper = entrada.nextLine().charAt(0); // 0 = pegar a primeira posição

            System.out.println("Digite o primeiro valor");
            value1 = Double.parseDouble(entrada.nextLine());

            System.out.println("Digite o segundo valor");
            value2 = Double.parseDouble(entrada.nextLine());

            MsqReq request = new MsqReq(oper, value1, value2);

            out.writeObject(request);

            MsqResp response = (MsqResp) in.readObject();

            if(response.getStatus() == Status.SUCESSO){
                System.out.println("Resposta: " + response.getValue());
            }

        } catch (Exception e) {
            System.out.println("Erro na comunicação: " + e.getMessage());
        }


        try {
            System.out.println("Encerrando o cliente");
            socket.close();
            entrada.close();
        } catch (Exception e) {
            
        }
     
    }
}
