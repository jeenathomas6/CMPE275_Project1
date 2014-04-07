<<<<<<< HEAD
package poke.server;

import java.util.Hashtable;

public class ServerInfo {

	private static Hashtable<String, String> nodeInfoTable = new Hashtable<String, String>();
	

	public ServerInfo (){
		initializeNodeInfoTable();
	}
	
	public Hashtable<String, String> getNodeInfoTable(){
		return nodeInfoTable;
	}
	
	public void initializeNodeInfoTable (){
		nodeInfoTable.clear();
		nodeInfoTable.put("zero","available");
		nodeInfoTable.put("one","available");
		nodeInfoTable.put("two","available");
		nodeInfoTable.put("three","available");
		nodeInfoTable.put("four","available");		
	}
	
	public void setNodeBusy (String nodeId){
		nodeInfoTable.get(nodeId);
		nodeInfoTable.put(nodeId, "busy");
	}
	
	public void setNodeAvailable (String nodeId){
		nodeInfoTable.get(nodeId);
		nodeInfoTable.put(nodeId, "available");
	}
	
	public void setNodeConnectionLost (String nodeId){
		nodeInfoTable.get(nodeId);
		nodeInfoTable.put(nodeId, "lost");
	}
	
	public String getNodeStatus (String nodeId){
		return nodeInfoTable.get(nodeId);
	}
}
=======
package poke.server;

import java.util.Hashtable;

public class ServerInfo {

	private static Hashtable<String, String> nodeInfoTable = new Hashtable<String, String>();
	

	public ServerInfo (){
		initializeNodeInfoTable();
	}
	
	public Hashtable<String, String> getNodeInfoTable(){
		return nodeInfoTable;
	}
	
	public void initializeNodeInfoTable (){
		nodeInfoTable.clear();
		nodeInfoTable.put("zero","available");
		nodeInfoTable.put("one","available");
		nodeInfoTable.put("two","available");
		nodeInfoTable.put("three","available");
		nodeInfoTable.put("four","available");		
	}
	
	public void setNodeBusy (String nodeId){
		nodeInfoTable.get(nodeId);
		nodeInfoTable.put(nodeId, "busy");
	}
	
	public void setNodeAvailable (String nodeId){
		nodeInfoTable.get(nodeId);
		nodeInfoTable.put(nodeId, "available");
	}
	
	public void setNodeConnectionLost (String nodeId){
		nodeInfoTable.get(nodeId);
		nodeInfoTable.put(nodeId, "lost");
	}
	
	public String getNodeStatus (String nodeId){
		return nodeInfoTable.get(nodeId);
	}
}
>>>>>>> 16cb6562cc02ef3108b1149670403bb03d2d94d9
