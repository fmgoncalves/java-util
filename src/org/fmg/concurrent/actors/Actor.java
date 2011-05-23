package org.fmg.concurrent.actors;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Actor<T> extends Thread {

	private volatile Queue<T> inbox = new LinkedList<T>();

	protected ReentrantLock r = new ReentrantLock();
	private Condition populated = r.newCondition();
	private Condition empty = r.newCondition();

	@Override
	public void run() {
		for (;;) {
			r.lock();
			if (!inbox.isEmpty())
				act(inbox.poll());
			else
				try {
					empty.signal();
					populated.await();
				} catch (InterruptedException e) {
				}
			r.unlock();
		}
	}

	public void send_one_way(T message) {
		r.lock();
		inbox.add(message);
		populated.signal();
		r.unlock();
	}

	protected abstract void act(T message);

	protected boolean toTheEnd() {
		r.lock();
		while (!inbox.isEmpty())
			try {
				empty.await();
			} catch (InterruptedException e) {
			}
		r.unlock();
		return true;
	}
}
