package demo.terminal;

import java.io.IOException;

import myrmi.exception.NotBoundException;
import myrmi.exception.RemoteException;
import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;

public class ClientApp {
    public static void main(String[] args) throws NotBoundException, IOException {
        Registry registry = LocateRegistry.getRegistry();
        Terminal terminal = (Terminal) registry.lookup("terminal");
        while (true) {
            System.out.print(">> ");
            String command = System.console().readLine();
            if (command.equals("exit")) {
                break;
            }
            try {
                String result = terminal.checkOutput(command);
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
}
