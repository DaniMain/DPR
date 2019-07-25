package pagerank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.*;

public class PageRank {
	
	private double df = 0.85;
	
	List<Node> nodes;
	List<Edge> edges;
	
	public PageRank(Graph graph){		
		this.nodes = graph.getNodes();
		this.edges = graph.getEdges();
	}
	
	public Map<Node,Double> nDPR(){
		
		Map<Node,Double> PRs = pr();
		
		//cerco il massimo DPR
		double max = Double.MIN_VALUE;
		for (Node n: PRs.keySet()){
			double pr = PRs.get(n);
			if (pr>max)
				max = pr;
		}
		
		//divido tutti i page rank dei nodi per valore massimo
		for (Node n: PRs.keySet()){
			double newPR = PRs.get(n)/max;
			PRs.put(n, newPR);
		}
		
		return PRs;
	}
	
	private Map<Node,Double> pr(){
		
		//per semplicità il numero di iterazioni è fissato al numero di nodi nel grafo + 1
		int iterations = this.nodes.size()+1;
		
		//chiave=nodo, valore=page rank di quel nodo
		//ogni volta viene aggiornata con il nuovo page rank
		Map<Node, Double> PRs = new HashMap<Node, Double>();
		
		//inizializzamo i nodi con valori tutti uguali tra loro
		initialization(PRs,this.nodes.size());
		
		/*finchè non converge */
		while(iterations>0){
			
			//mappa con i nuovi valori di page rank che vengono calcolati
			Map<Node,Double> newPRs = new HashMap<Node,Double>();
			
			//viene calcolato il page rank di ciascun nodo nel grafo
			for(Node n: this.nodes){
				
				//lista dei nodi vicini
				List<Node> neighbors = getNeighbors(this.edges,n);
								
				//per ciascun nodo vicino viene calcolata la sommatoria
				double sum = 0.;
				for (Node neighbor: neighbors){
					sum += (PRs.get(neighbor) * nDIV(n,neighbor));
				}
				
				//calcolo del page rank del nodo e aggiornamento della mappa con i nuovi pr
				double pr = (1. - df) + df * sum;
				newPRs.put(n, pr);
			}
			
			//aggiornamento di PRs con la mappa calcolata precedentemente
			PRs=newPRs;
			iterations--;
		}
		
		return PRs;
		
	}
	
	private double nDIV(Node n, Node neighbor) {
		double sum=0;
		for (Node i: getNeighbors(this.edges, n)){
			sum += DIV(n,i);
		}
		return DIV(n,neighbor)/sum;
	}
	
	private double DIV(Node n, Node neighbor) {
		Set<String> sourcesN = n.getSources();
		Set<String> sourcesNeighbor = neighbor.getSources();
		Set<String> diff1 = new HashSet<String>();
		Set<String> diff2 = new HashSet<String>();
		diff1.addAll(sourcesN);
		diff2.addAll(sourcesNeighbor);
		diff1.removeAll(sourcesNeighbor);
		diff2.removeAll(sourcesN);
		diff1.addAll(diff2);
		return diff1.size();
	}

	//ritorna i vicini da un nodo
	private List<Node> getNeighbors(List<Edge> edges, Node n) {
		List<Node> neighbors = new ArrayList<Node>();
		for (Edge e: edges){
			if (n.equals(e.getNode1())){
				neighbors.add(e.getNode2());
			}
		}
		return neighbors;
	}

	private void initialization(Map<Node,Double> map, int size){
		double initial_pr = 1./size;
		for (Node n: this.nodes)
			map.put(n, initial_pr);
	}

}
