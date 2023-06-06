package demo.terminal;


import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;
import myrmi.server.UnicastRemoteObject;

public class ServerApp {

	public ServerApp() {
	}

	public static void main(String args[]) {

		try {
			Terminal robj = new TerminalImpl();
			// 在本地建立服务对象stub
			Terminal stub = (Terminal) UnicastRemoteObject.exportObject(robj, 0);

			// 服务对象移动到Registry上
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("terminal", stub);
			System.out.println("terminal Server is ready to listen... ");

		} catch (Exception e) {
			System.err.println("Server exception thrown: " + e.toString());
			e.printStackTrace();
		}
	}
}
