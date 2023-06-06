package demo.matmul;


import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;
import myrmi.server.UnicastRemoteObject;

public class ServerApp {

	public ServerApp() {
	}

	public static void main(String args[]) {

		try {
			Matmul robj = new MatmulImpl();
			// 在本地建立服务对象stub
			Matmul stub = (Matmul) UnicastRemoteObject.exportObject(robj, 0);

			// 服务对象移动到Registry上
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("matmul", stub);
			System.out.println("matmul Server is ready to listen... ");

		} catch (Exception e) {
			System.err.println("Server exception thrown: " + e.toString());
			e.printStackTrace();
		}
	}
}
