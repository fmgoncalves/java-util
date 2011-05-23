/**
 * 
 */
package org.fmg.util;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;


public class Profiler {
	private List<Pair<String, Long>> moments = new LinkedList<Pair<String, Long>>();

	public void register(String message) {
		long t = System.currentTimeMillis();
		moments.add(Pair.apply(message, t));
	}

	public void printLog(PrintStream out) {
		out.println(this);
		moments = new LinkedList<Pair<String, Long>>();
	}

	public void printLog() {
		this.printLog(System.out);
	}

	@Override
	public String toString() {
		String result = "";
		long t0 = -1;
		for (Pair<String, Long> moment : moments) {
			result += String.format("%s\t%d ms\n", moment.getFirst(),
					t0 == -1 ? 0 : moment.getSecond() - t0);
			t0 = moment.getSecond();
		}
		return result;
	}
}