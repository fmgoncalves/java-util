package org.fmg.util;


public class Pair<F, S> {
	private F first;
	private S second;
	
	public static <K,V> Pair<K,V> apply(K first, V second){
		return new Pair<K,V>(first,second);
	}

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return String.format("(%s, %s)", this.first, this.second);
	}
}
