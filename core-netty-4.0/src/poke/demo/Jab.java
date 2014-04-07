/*
 * copyright 2012, gash
 * 
 * Gash licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package poke.demo;

import poke.client.ClientCommand;
import poke.client.ClientPrintListener;
import poke.client.comm.CommListener;
<<<<<<< HEAD
=======
import poke.server.PortListener;
>>>>>>> 16cb6562cc02ef3108b1149670403bb03d2d94d9
import poke.server.management.managers.ClientListener;
import poke.server.management.managers.HeartbeatListener;

/**
 * DEMO: how to use the command class
 * 
 * @author gash
 * 
 */
public class Jab {
	private String tag;
	private int count;

	public Jab(String tag) {
		this.tag = tag;
	}

	public void run() {
		ClientCommand cc = new ClientCommand("localhost", 5573);
		//CommListener listener = new ClientPrintListener("jab demo");
		//HeartbeatListener hblistener = new HeartbeatListener("jab_demo");
		//ClientListener clientListener=new ClientListener("jab_demo");
		//PortListener portlistener=new PortListener("jab_demo");
		//cc.addListener(listener);
		//cc.addListener(hblistener);
		//cc.addListener(clientListener);

<<<<<<< HEAD
		for (int i = 0; i < 1; i++) {
			count++;
			cc.poke(tag, 1);
=======
		for (int i = 0; i < 3; i++) {
			count++;
			cc.poke(tag, count);
>>>>>>> 16cb6562cc02ef3108b1149670403bb03d2d94d9
		}
	}

	public static void main(String[] args) {
		try {
			Jab jab = new Jab("jab");
			jab.run();

			// we are running asynchronously
			//System.out.println("\nExiting in 5 seconds");
			//Thread.sleep(5000);
			//System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
