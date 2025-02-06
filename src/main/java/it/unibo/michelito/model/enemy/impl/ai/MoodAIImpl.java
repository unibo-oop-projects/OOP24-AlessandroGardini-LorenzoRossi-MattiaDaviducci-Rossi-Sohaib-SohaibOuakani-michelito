package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.MoodAI;
import it.unibo.michelito.model.enemy.api.ai.MoodAIFactory;
import it.unibo.michelito.model.enemy.api.ai.MoodType;
import it.unibo.michelito.model.enemy.api.ai.MovementAI;
import it.unibo.michelito.util.Direction;

public class MoodAIImpl implements MoodAI {
    private MoodType actualMood;
    private MovementAI actualMovement;
    private final MoodAIFactory moodAIFactory = new MoodAIFactoryImpl();
    private final long createdTime;

    MoodAIImpl(long currentTime) {
        this.createdTime = currentTime;
        setMood(MoodType.SLEEPING);
    }

    @Override
    public void setMood(MoodType mood) {
        switch (mood) {
            case SLEEPING:
                actualMovement = moodAIFactory.sleeping();
                actualMood = MoodType.SLEEPING;
                break;
            case CHILLING:
                actualMovement = moodAIFactory.chilling();
                actualMood = MoodType.CHILLING;
                break;
            case SEARCHING:
                actualMovement = moodAIFactory.searching();
                actualMood = MoodType.SEARCHING;
                break;
            default:
                    break;
        }
    }

    @Override
    public MoodType getMood() {
        return actualMood;
    }

    @Override
    public Direction getDirection() {
        return actualMovement.getDirection();
    }

    @Override
    public void update(long currentTime) {
        if( currentTime - createdTime >= 10000){
            setMood(MoodType.CHILLING);
        }


    }
}
