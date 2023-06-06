package myrmi.registry;

import myrmi.Remote;
import myrmi.exception.RemoteException;
import myrmi.server.Util;

import java.lang.reflect.Proxy;

public class LocateRegistry {

    // 获得默认网络位置的Registry。
    // 本次Assignment中，
    public static Registry getRegistry() {
        return getRegistry(Util.clientSupposeRemoteObjectAtHost, Registry.REGISTRY_PORT);
    }

    /**
     * 一般是Client调用，获得 Registry 的stub到client。
     * returns a stub of remote registry
     */
    public static Registry getRegistry(String host, int port) {
        if (port <= 0) {
            port = Registry.REGISTRY_PORT;
        }
        if (host == null || host.length() == 0) {
            try {
                host = java.net.InetAddress.getLocalHost().getHostAddress();
            } catch (Exception e) {
                host = "";
            }
        }
        Remote stub = (Remote) Proxy.newProxyInstance(Registry.class.getClassLoader(),
                new Class<?>[] { Registry.class }, new RegistryStubInvocationHandler(host, port));

        return (Registry) stub;
    }

    // Registry 程序默认调用。会在本地（）创建一个Registry
    public static Registry createRegistry() throws RemoteException {
        return createRegistry(Registry.REGISTRY_PORT);
    }

    /**
     * create a registry locally
     * but we still need to wrap around the lookup() method
     */
    public static Registry createRegistry(int port) throws RemoteException {
        if (port == 0) {
            port = Registry.REGISTRY_PORT;
        }
        new RegistryImpl(port); //
        return (Registry) Proxy.newProxyInstance(Registry.class.getClassLoader(), new Class<?>[] { Registry.class },
                new RegistryStubInvocationHandler(Util.serverSupposeRemoteObjectAtHost, port));
    }

    public static Registry createRegistry(String host, int port) throws RemoteException {
        if (port == 0) {
            port = Registry.REGISTRY_PORT;
        }
        new RegistryImpl(host, port);
        return (Registry) Proxy.newProxyInstance(Registry.class.getClassLoader(), new Class<?>[] { Registry.class },
                new RegistryStubInvocationHandler(host, port));
    }
}
