package myrmi.server;

import myrmi.Remote;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Util {
    // Server认为默认要创建的远程对象所在的位置
    // public static final String serverSupposeRemoteObjectAtHost = "0.0.0.0";
    public static final String serverSupposeRemoteObjectAtHost = "localhost";
    // public static final String clientSupposeRemoteObjectAtHost =
    // "host.docker.internal";

    // Client认为的 RemoteObject 的位置，如果没有指定的话。
    public static final String clientSupposeRemoteObjectAtHost = "localhost";

    public static Remote createStub(RemoteObjectRef ref) {
        try {
            Class<?> remoteInterface = Class.forName(ref.getInterfaceName());
            InvocationHandler handler = new StubInvocationHandler(ref);
            return (Remote) Proxy.newProxyInstance(remoteInterface.getClassLoader(), new Class<?>[] { remoteInterface },
                    handler);
        } catch (ClassNotFoundException e) {
            System.err.printf("Error creating stub for interface %s at %s:%d, class not found\n",
                    ref.getInterfaceName(), ref.getHost(), ref.getPort());
            System.exit(1);
        }
        // shouldn't get there
        return null;
    }

}
