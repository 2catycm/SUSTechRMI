package demo.matmul;

import java.io.IOException;

public interface Matmul extends myrmi.Remote{

    default String runMatrixMultipliaction(int processes, MatrixSize size) throws IOException{
        return runMatrixMultipliaction(processes, size, ".");
    }
    String runMatrixMultipliaction(int processes, MatrixSize size, String baseLocation) throws IOException;
}
