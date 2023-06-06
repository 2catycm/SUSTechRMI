package demo.terminal;

import java.io.IOException;

public interface Terminal extends myrmi.Remote{
    int system(String command) throws InterruptedException, IOException;
    String checkOutput(String command) throws IOException;
}
