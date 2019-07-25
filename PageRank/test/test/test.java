package test;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import graph.Edge;
import graph.Graph;
import graph.Node;
import pagerank.*;

public class test {
	
//	@Test
//	public void testNDiv(){
//		System.out.println(pr.nDIV(node1, node2));
//	}
	
	@Test
	public void testNDPR(){
		Node a = new Node("a,b");
		Node b = new Node("b,d,e");
		Node c = new Node("c,a");
		Node d = new Node("d");
		Node e = new Node("e,a,b");
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(a);
		nodes.add(b);
		nodes.add(c);
		nodes.add(d);
		nodes.add(e);
		Edge ab = new Edge(a,b,1);
		Edge be = new Edge(b,e,1);
		Edge ca = new Edge(c,a,1);
		Edge cb = new Edge(c,b,1);
		Edge cd = new Edge(c,d,1);
		Edge ce = new Edge(c,e,1);
		Edge dc = new Edge(d,c,1);
		Edge de = new Edge(d,e,1);
		Edge ed = new Edge(e,d,1);
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(ab);
		edges.add(be);
		edges.add(ca);
		edges.add(cb);
		edges.add(cd);
		edges.add(ce);
		edges.add(dc);
		edges.add(de);
		edges.add(ed);
		Graph graph = new Graph(nodes, edges);
		PageRank pr = new PageRank(graph);
		Map<Node, Double> pageRank = pr.nDPR();
		for (Node n: pageRank.keySet()){
			System.out.println(n.toString()+"\tnDPR: "+pageRank.get(n));
		}
		
	}

}
