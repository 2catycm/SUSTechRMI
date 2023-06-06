package demo.matmul;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import demo.terminal.Terminal;
import demo.terminal.TerminalImpl;
import myrmi.exception.RemoteException;

public class MatmulImpl implements Matmul {
    private Terminal terminal = new TerminalImpl();
    public MatmulImpl() throws RemoteException {
        super();
    }
    public static final String MATRIX_MULTIPLICATION_EXECUTABLE = "mpi_matmul_peer_pattern";
    @Override
    public String runMatrixMultipliaction(int processes, MatrixSize size, String baseLocation) throws IOException {
        String executable = Path.of(baseLocation, String.format("%s_%d.exe", MATRIX_MULTIPLICATION_EXECUTABLE, size.value)).toAbsolutePath().toString();
        return terminal.checkOutput(String.format("mpiexec -np %d %s", processes, executable));
    }
    
}
