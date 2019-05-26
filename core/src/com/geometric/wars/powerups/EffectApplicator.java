package com.geometric.wars.powerups;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.geometric.wars.player.ShootingPlayersCube;

import java.util.Iterator;
import java.util.PriorityQueue;


public class EffectApplicator {
    private EffectApplicator(){}
    static EffectApplicator instance;
    public static EffectApplicator getInstance() {
        if(instance == null)
            instance = new EffectApplicator();
        return instance;
    }

    public void clearEffectsOn(ShootingPlayersCube cube) {
        if(effects.containsKey(cube)) {
            for(TimeEffect e : effects.get(cube))
                e.wasDestroyed = true;
        }
    }


    private static class TimeEffect implements Comparable<TimeEffect>{
        Effect effect;
        ShootingPlayersCube cube;
        long destroyTimeInMills;
        boolean wasDestroyed = false;
        TimeEffect(Effect effect, ShootingPlayersCube cube, long destroyTime) {
            this.effect = effect;
            this.cube = cube;
            this.destroyTimeInMills = destroyTime;
        }

        @Override
        public int compareTo(TimeEffect timeEffect) {
            int r1 = Long.compare(this.destroyTimeInMills,timeEffect.destroyTimeInMills);
            if(r1 == 0)
                return Long.compare(this.effect.hashCode(),timeEffect.effect.hashCode());
            return r1;
        }
    }

    private ObjectMap<ShootingPlayersCube, PriorityQueue<TimeEffect> > effects = new ObjectMap<>();
    private PriorityQueue<TimeEffect> timesOfUpdate = new PriorityQueue<>();



    void addEffect(ShootingPlayersCube cube, long durationInMillis, Effect effect) {
        if(!effects.containsKey(cube))
            effects.put(cube,new PriorityQueue<TimeEffect>());
        for(Iterator<TimeEffect> it = effects.get(cube).iterator(); it.hasNext();) {
            TimeEffect e = it.next();
            if(e.effect.getClass().isInstance(effect) && ! e.wasDestroyed) {
                e.effect.revert(e.cube);
                e.wasDestroyed = true;
            }
        }
        TimeEffect timeEffect = new TimeEffect(effect,cube, TimeUtils.millis() + durationInMillis);
        effect.apply(cube);
        effects.get(cube).add(timeEffect);
        timesOfUpdate.add(timeEffect);
    }


    public void update() {
        while (timesOfUpdate.size() > 0 && timesOfUpdate.peek().destroyTimeInMills <= TimeUtils.millis()) {
            if (!(timesOfUpdate.peek().wasDestroyed)) {
                timesOfUpdate.peek().effect.revert(timesOfUpdate.peek().cube);
            }
            effects.get(timesOfUpdate.peek().cube).poll();
            timesOfUpdate.poll();
        }
    }


}
