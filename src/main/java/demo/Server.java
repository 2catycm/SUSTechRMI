package demo;

import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;
import myrmi.server.UnicastRemoteObject;

public class Server {

	public Server() {
	}

	public static void main(String args[]) {

		try {
			PaymentImpl robj = new PaymentImpl();
			// 在本地建立服务对象stub
			Payment stub = (Payment) UnicastRemoteObject.exportObject(robj, 0);

			// 服务对象移动到Registry上
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Mortgage", stub);
			System.out.println("Mortgage Server is ready to listen... ");

		} catch (Exception e) {
			System.err.println("Server exception thrown: " + e.toString());
			e.printStackTrace();
		}
	}
}
