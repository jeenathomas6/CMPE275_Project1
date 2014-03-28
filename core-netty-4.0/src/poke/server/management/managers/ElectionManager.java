/*
 * copyright 2014, gash
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

import java.util.concurrent.atomic.AtomicReference;

import com.google.protobuf.GeneratedMessage;
import eye.Comm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eye.Comm.LeaderElection;
import eye.Comm.LeaderElection.VoteAction;
import poke.resources.ForwardResource;
import poke.server.conf.ServerConf;

/**
 * The election manager is used to determine leadership within the network.
 * 
 * @author gash
 * 
 */
public class ElectionManager extends Thread{
	protected static Logger logger = LoggerFactory.getLogger("management");
	protected static AtomicReference<ElectionManager> instance = new AtomicReference<ElectionManager>();
    ServerConf conf;
	private String nodeId;

	/** @brief the number of votes this server can cast */
	private int votes = 1;

	public static ElectionManager getInstance(String id, int votes,ServerConf conf) {
		instance.compareAndSet(null, new ElectionManager(id, votes,conf));
		return instance.get();
	}

	public static ElectionManager getInstance() {
		return instance.get();
	}

	/**
	 * initialize the manager for this server
	 * 
	 * @param nodeId
	 *            The server's (this) ID
	 */
	public ElectionManager(String nodeId, int votes, ServerConf conf) {
		this.nodeId = nodeId;
        this.conf = conf;
		if (votes >= 0)
			this.votes = votes;
	}

	/*
	 * @param args
	 */
	public void processRequest(LeaderElection req) {
		if (req == null)
			return;

		if (req.hasExpires()) {
			long ct = System.currentTimeMillis();
			if (ct > req.getExpires()) {
				// election is over
				return;
			}
		}
		if (req.getVote().getNumber() == VoteAction.ELECTION_VALUE) {
			logger.info("I am hereeeeee");
			logger.info("Election declared by "+req.getNodeId());
			// an election is declared!
		} else if (req.getVote().getNumber() == VoteAction.DECLAREVOID_VALUE) {
			// no one was elected, I am dropping into standby mode`
		} else if (req.getVote().getNumber() == VoteAction.DECLAREWINNER_VALUE) {
            String val = req.getNodeId();
            logger.info("Inside Winner" + val);

		} else if (req.getVote().getNumber() == VoteAction.ABSTAIN_VALUE) {
			// for some reason, I decline to vote
		} else if (req.getVote().getNumber() == VoteAction.NOMINATE_VALUE) {
			logger.info("Inside Nominate");
			logger.info("My value: "+ nodeId);
			logger.info("Request node id : "+req.getNodeId());
			int comparedToMe = req.getNodeId().compareTo(nodeId);
			logger.info("value of compared "+comparedToMe);
			if (comparedToMe > 0) {
				logger.info("Request node Id is greater");
				// Someone else has a higher priority, forward nomination
				// TODO forward
			} else if (comparedToMe < 0 ) {
				// I have a higher priority, nominate myself
				// TODO nominate myself
				logger.info("I have higher priority");
                try{
                LeaderElection.Builder leaderBuilder = LeaderElection.newBuilder();
                leaderBuilder.setVote(VoteAction.DECLAREWINNER);
                leaderBuilder.setBallotId("five");
                leaderBuilder.setDesc("I am desc from nominate");
                leaderBuilder.setNodeId(nodeId);

                Comm.Management.Builder builder = Comm.Management.newBuilder();
                builder.setElection(leaderBuilder.build());

                for (HeartbeatData hd : HeartbeatManager.getInstance().outgoingHB.values()) {
                    if (hd.getFailuresOnSend() > HeartbeatData.sFailureToSendThresholdDefault)
                        continue;


                    GeneratedMessage msg = builder.build();

                    try {
                        logger.info("sending nominate value " + hd.getNodeId()+ " at" + hd.getPort()+ " and the node id is " + nodeId);
                        hd.channel.writeAndFlush(msg);

                    } catch (Exception e) {
                        hd.incrementFailuresOnSend();
                    }
                }
                }
                catch (Exception e)
                {
                    logger.info("exception" + e);
                }

            }
		}
	}
}
