/*
 * 
 */
package net.cosmologic.inferno.ui.screen.render;

import net.cosmologic.inferno.ui.screen.IComponent;

/**
 * The listener interface for receiving abstractDeltaProcess events.
 * The class that is interested in processing a abstractDeltaProcess
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addAbstractDeltaProcessListener<code> method. When
 * the abstractDeltaProcess event occurs, that object's appropriate
 * method is invoked.
 *
 * @see AbstractDeltaProcessEvent
 * 
 * @author Evren Ozcan
 */
public class AbstractDeltaProcessListener implements IDeltaProcessListener
{

    /**{@inheritDoc}*/
    @Override
    public void onFirstEnter(final IComponent component)
    {
    }

    /**{@inheritDoc}*/
    @Override
    public void onStart(final IComponent component)
    {

    }

    /**{@inheritDoc}*/
    @Override
    public void onEnd(final IComponent component)
    {

    }

    /**{@inheritDoc}*/
    @Override
    public void onRepeat(final IComponent component)
    {
    }

}
