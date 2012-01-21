/*
 * 
 */
package net.cosmologic.inferno.reactor;

/**
 * The Interface IReactorProcess.
 * 
 * @author Evren Ozcan
 */
public interface IReactorProcess
{
    
    /**
     * Update.
     *
     * @param delta the delta
     */
    void update(float delta);

    /**
     * Adds the process listener.
     *
     * @param callBack the call back
     */
    void addProcessListener(IReactorProcessListener callBack);

    /**
     * Sets the repeat.
     *
     * @param repeat the new repeat
     */
    void setRepeat(boolean repeat);

    /**
     * Sets the tribune.
     *
     * @param tribune the new tribune
     */
    void setTribune(ITribune tribune);

    /**
     * Checks if is asynch.
     *
     * @return true, if is asynch
     */
    boolean isAsynch();

    /**
     * Reset.
     */
    void reset();

    /**
     * Gets the result.
     *
     * @return the result
     */
    Object getResult();
}
