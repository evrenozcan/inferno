/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

/**
 * The Interface IDialogHolder.
 * 
 * @author Evren Ozcan
 */
public interface IDialogHolder
{
    
    /**
     * On dialog action.
     *
     * @param dialog the dialog
     * @param component the component
     */
    void onDialogAction(IDialog dialog, IComponent component);

    /**
     * On dialog completes.
     *
     * @param dialog the dialog
     */
    void onDialogCompletes(IDialog dialog);
}
