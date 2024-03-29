package graph;

import java.util.HashSet;
import java.util.Set;

public class Node {
	
	private String name;
	
	public Node(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getSources() {
		String[] split = name.split(",");
		Set<String> sources = new HashSet<String>();
		for (int i=0;i<split.length;i++)
			sources.add(split[i]);
		return sources;
	}

	@Override
	public String toString() {
		return "Node [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Node other = (Node) obj;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
		Node otherNode = (Node) obj;
		return this.getName().equals(otherNode.getName());
	}

}
