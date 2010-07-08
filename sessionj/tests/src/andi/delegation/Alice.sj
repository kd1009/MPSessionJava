//$ bin/sessionjc -cp tests/classes/ tests/src/andi/delegation/Alice.sj -d tests/classes/
//$ bin/sessionj -cp tests/classes/ andi.delegation.Alice  
package andi.delegation;

import sessionj.runtime.*;
import sessionj.runtime.net.*;
//import benchmark1.BinaryTree;
public class Alice{
	
	private final noalias protocol p_carol 
	{
		cbegin.![!<BinaryTree>.?(BinaryTree)]*
	}	
	
	public void run(String carol_host, int carol_port, int depth, int singleSession) throws Exception {
		
		final noalias SJService c = SJService.create(p_carol, carol_host, carol_port);
		//create the balanced binary tree of depth @depth
		noalias BinaryTree theTree = BinaryTree.createBinaryTree(depth);
		final noalias SJSocket ds;		
		
		try (ds) 
		{
			ds = c.request();		
			
			int k = 1;
			ds.outwhile(k <= singleSession){
								
				theTree.print();
				
				System.out.println();
				
				ds.send(theTree);
				theTree = (BinaryTree)ds.receive();
				
				k++;
			}
		}finally{}
	}
	
	public static void main(String[] args) throws Exception{
		
		Alice a = new Alice();
		
		a.run(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
	}
}