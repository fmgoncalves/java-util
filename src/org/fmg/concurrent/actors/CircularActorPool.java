package org.fmg.concurrent.actors;

public class CircularActorPool<T> extends ActorPool<T> {

	private Actor<T>[] actors;
	int curr = 0;

	public CircularActorPool(Actor<T>[] actors) {
		for(Actor<T> x: actors)
			x.start();
		this.actors = actors;
	}

	@Override
	public Actor<T> getActor() {
		return actors[curr++ % actors.length];
	}
}
