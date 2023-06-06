package demo.terminal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import myrmi.exception.RemoteException;

public class TerminalImpl implements Terminal {
    protected TerminalImpl() throws RemoteException {
        super();
    }

    public int system(String command) throws InterruptedException, IOException {
        return Runtime.getRuntime().exec(command).waitFor();
    }

    public String checkOutput(String command) throws IOException {
        final Process process = Runtime.getRuntime().exec(command);
        try (Scanner scanner = new Scanner(new BufferedInputStream(process.getInputStream()))) {
            final ArrayList<String> result = new ArrayList<>();
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
            return String.join("\n", result);
        }
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
