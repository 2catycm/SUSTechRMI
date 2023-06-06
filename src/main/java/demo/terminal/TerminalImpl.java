package demo.terminal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import myrmi.exception.RemoteException;

public class TerminalImpl implements Terminal {
    public TerminalImpl() throws RemoteException {
        super();
    }

    public int system(String command) throws InterruptedException, IOException {
        return Runtime.getRuntime().exec(command).waitFor();
    }

    public String checkOutput(String command) throws IOException {
        final Process process = Runtime.getRuntime().exec(command);
        StringBuilder result = new StringBuilder();
        try (Scanner outputScanner = new Scanner(new BufferedInputStream(process.getInputStream()));
            Scanner errorScanner = new Scanner(new BufferedInputStream(process.getErrorStream()))
        ) {
            while (outputScanner.hasNextLine()) {
                result.append(outputScanner.nextLine()).append("\n");
            }
            while (errorScanner.hasNextLine()) {
                result.append(errorScanner.nextLine()).append("\n");
            }
        }
        return result.toString();
        // final ProcessBuilder builder = new ProcessBuilder(command.split("\\s+"));
        // builder.redirectErrorStream(true);
        // builder.inheritIO();
    }

    void remoteLogin(InputStream inputStream, OutputStream outputStream) {
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        final TerminalImpl terminal = new TerminalImpl();
        while (!Thread.interrupted()) {
            System.out.println(terminal.checkOutput("date"));
            System.out.println(terminal.checkOutput("echo Hello, &&echo \tworld! "));
            Thread.sleep(5000);
        }
    }
}
