package main.day07;

import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("rawtypes")
public class Day7Node implements Comparable {

    public Character node;
    public Set<Character> preConditions = new TreeSet<>();
    public int time;

    public boolean isRemoved = false;

    // Step R must be finished before step Y can begin.
    public Day7Node(String input) {
        String res = input.replaceFirst("Step ", "").replaceFirst(" must be finished before step ", "");
        this.node = res.charAt(1);
        this.preConditions.add(res.charAt(0));
        this.time = 60 + (this.node.charValue() - 64);
    }

    public Day7Node(Character c) {
        this.node = c;
        this.time = 60 + (this.node.charValue() - 64);
    }

    @Override
    public int compareTo(Object o) {
        return this.node.compareTo(((Day7Node) o).node);
    }

    @Override
    public String toString() {
        return this.node + ": " + this.preConditions.toString();
    }

    @Override
    public boolean equals(Object o) {
        return ((Day7Node) o).node == this.node;
    }
}
