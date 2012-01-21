/*
 * 
 */
package net.cosmologic.inferno.android.handler;

import net.cosmologic.inferno.android.InfernoAndroidContext;
import net.cosmologic.inferno.event.GenericEvent;
import net.cosmologic.inferno.event.IGameEvent.EventType;
import net.cosmologic.inferno.io.KeyEventWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

/**
 * The Class OnKeyEventHandler.
 * 
 * 
 * @author Evren Ozcan
 */
public class OnKeyEventHandler implements OnKeyListener, IHandler
{

    /** The blocked. */
    private volatile boolean blocked;
    
    /** The keys pressed. */
    private final boolean[] keysPressed = new boolean[128];
    
    /** The context. */
    private final InfernoAndroidContext context;

    /**
     * Instantiates a new on key event handler.
     *
     * @param context the context
     */
    public OnKeyEventHandler(final InfernoAndroidContext context)
    {
        this.context = context;
        context.getActivity().getView().setOnKeyListener(this);
        context.getActivity().getView().setFocusableInTouchMode(true);
        context.getActivity().getView().requestFocus();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onKey(final View v, final int keyCode, final KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
        {
            return false;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (event.getAction() == KeyEvent.ACTION_DOWN)
            {
                return context.getGamePlatform().onInterrupt();
            }
        }
        if (blocked)
        {
            return false;
        }
        final KeyEventWrapper wrapper = new KeyEventWrapper();
        wrapper.keyCode = keyCode;
        wrapper.keyChar = (char) event.getUnicodeChar();
        final GenericEvent genericEvent = new GenericEvent(this, wrapper, EventType.KEYPRESS);
        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {
            genericEvent.action = EventType.Action.KEY_DOWN;
            if ((keyCode > 0) && (keyCode < 127))
            {
                keysPressed[keyCode] = true;
            }
        }
        if (event.getAction() == KeyEvent.ACTION_UP)
        {
            genericEvent.action = EventType.Action.KEY_UP;
            if ((keyCode > 0) && (keyCode < 127))
            {
                keysPressed[keyCode] = false;
            }
        }
        context.getTribune().queueInput(genericEvent);
        return false;
    }

    /**
     * Checks if is key pressed.
     *
     * @param keyCode the key code
     * @return true, if is key pressed
     */
    public boolean isKeyPressed(final int keyCode)
    {
        if ((keyCode < 0) || (keyCode > 127))
        {
            return false;
        }
        else
        {
            return keysPressed[keyCode];
        }
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isBlocked()
    {
        return blocked;
    }

    /**{@inheritDoc}*/
    @Override
    public void setBlocked(final boolean blocked)
    {
        this.blocked = blocked;
    }
}
