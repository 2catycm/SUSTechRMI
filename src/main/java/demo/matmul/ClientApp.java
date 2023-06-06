package demo.matmul;

import java.io.IOException;

import myrmi.exception.NotBoundException;
import myrmi.exception.RemoteException;
import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;

public class ClientApp {
    public static void main(String[] args) throws NotBoundException, IOException {
        Registry registry = LocateRegistry.getRegistry();
        Matmul matmul = (Matmul) registry.lookup("matmul");
        while (true) {
            int processes;
            MatrixSize size;
            System.out.print("How many processes should we use?");
            do {
                String command = System.console().readLine();
                if (command.equals("exit")) {
                    System.exit(0);
                }
                try {
                    processes = Integer.parseInt(command);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print("Try again: ");
                    continue;
                }
                break;
            } while (true);
            
            System.out.print("How large should the matrix be?");
            do {
                String command2 = System.console().readLine();
                if (command2.equals("exit")) {
                    System.exit(0);
                }
                try {
                    int temp = Integer.parseInt(command2);
                    size = MatrixSize.fromInt(temp);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print("Try again: ");
                    continue;
                }
                break;
            } while (true);
            
            System.out.println("Calculating...");
            try {
                System.out.println(matmul.runMatrixMultipliaction(processes, size));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println();
        }
        
    }
}
