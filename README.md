# BullyAlgorithm
The Bully Algorithm

"As a first example, consider the bully algorithm devised by Garcia-Molina (1982). 
When any process notices that the coordinator is no longer responding to requests, it initiates an election.

A process, P, holds an election as follows:
1. P sends an ELECTION message to all processes with higher numbers.
2. If no one responds, P wins the election and becomes coordinator.
3. If one of the higher-ups answers, it takes over. P's job is done.

At any moment, a process can get an ELECTION message from one of its
lower-numbered colleagues. When such a message arrives, the receiver sends an
OK message back to the sender to indicate that he is alive and will take over. The
receiver then holds an election, unless it is already holding one. Eventually, all
processes give up but one, and that one is the new coordinator. It announces its
victory by sending all processes a message telling them that starting immediately
it is the new coordinator.
If a process that was previously down comes back up, it holds an election. If it
happens to be the highest-numbered process currently running, it will win the
election and take over the coordinator's job. Thus the biggest guy in town always
wins, hence the name "bully algorithm.""

Tanenbaum, A. S., Steen M. A.  (October 12, 2006) Distributed Systems: Principles and Paradigms. Pearson, 2 edition
