/*
 * copyright 2013, gash
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
package poke.server.management.managers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poke.monitor.MonitorListener;

public class HeartbeatListener implements MonitorListener {

    protected static Logger logger = LoggerFactory.getLogger("management-HeartbeatListener");

	private HeartbeatData data;

	public HeartbeatListener(HeartbeatData data) {
		this.data = data;
	}

	public HeartbeatData getData() {
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poke.monitor.MonitorListener#getListenerID()
	 */
	@Override
	public String getListenerID() {
		return data.getNodeId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poke.monitor.MonitorListener#onMessage(eye.Comm.Management)
	 */
	@Override
	public void onMessage(eye.Comm.Management msg) {
		if (logger.isDebugEnabled()){
			//logger.debug(msg.getBeat().getNodeId());
			logger.debug(msg.getElection().getNodeId());
			logger.info(msg.getElection().getNodeId());
			}

		if (msg.hasGraph()) {
			logger.info("Received graph responses");
		} else if (msg.hasBeat() && msg.getBeat().getNodeId().equals(data.getNodeId())) {
			logger.info("Inside onMessage in HeartbeatListener");
			logger.info("Received HB response from " + msg.getBeat().getNodeId());
			data.setLastBeat(System.currentTimeMillis());
		} else
			logger.error("Received heartbeatMgr from an wrong channel or unknown host: " + msg.getBeat().getNodeId());
		if(msg.hasElection())
		{
			logger.info("Recieved election response from "+msg.getElection().getNodeId());
			//call the process Request of Election Manager
			ElectionManager em=ElectionManager.getInstance();
			em.processRequest(msg, msg.getElection());
		}
<<<<<<< HEAD
		if(msg.hasJobPropose())
		{
			logger.info("In heart beat listener - got job proposal");
		}
=======
>>>>>>> 16cb6562cc02ef3108b1149670403bb03d2d94d9
		
	}
	


	@Override
	public void connectionClosed() {
		logger.info("Jeena - Inside connection closed");
		// note a closed management port is likely to indicate the primary port
		// has failed as well
	}

	@Override
	public void connectionReady() {
		// do nothing at the moment
	}
}
