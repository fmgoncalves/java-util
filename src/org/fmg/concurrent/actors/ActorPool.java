package org.fmg.concurrent.actors;

public abstract class ActorPool<T> extends Actor<T> {
	protected abstract Actor<T> getActor();

	protected void act(T message) {
		this.getActor().send_one_way(message);
	};
}
