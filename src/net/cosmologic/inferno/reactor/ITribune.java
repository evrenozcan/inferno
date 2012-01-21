/*
 * 
 */
package net.cosmologic.inferno.reactor;

import net.cosmologic.inferno.event.IGameEvent;
import net.cosmologic.inferno.sound.ISoundEffect;

/**
 * The Interface ITribune.
 * 
 * 
 * @author Evren Ozcan
 */
public interface ITribune extends Runnable
{
    
    /**
     * Start.
     */
    public void start();

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    boolean isActive();

    /**
     * Turn off.
     */
    void turnOff();

    /**
     * Chill out.
     */
    void chillOut();

    /**
     * Restart.
     */
    void restart();

    /**
     * Queue process.
     *
     * @param process the process
     */
    void queueProcess(IReactorProcess process);

    /**
     * Queue sound effect.
     *
     * @param effect the effect
     */
    void queueSoundEffect(ISoundEffect effect);

    /**
     * Queue input.
     *
     * @param wrapper the wrapper
     */
    void queueInput(IGameEvent wrapper);
}
