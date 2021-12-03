/*
 * This file is part of JMines.
 *
 * JMines is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JMines is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JMines. If not, see <http://www.gnu.org/licenses/>.
 */
package jmines.listeners;

import jmines.View.MainFrame;
import jmines.persistence.Configuration;

import javax.swing.*;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The Windows listener used for the main frame.
 *
 * @author Zleurtor
 */
public class WindowListenerForMainFrame implements WindowListener {

    //=========================================================================
    // Static attributes
    //=========================================================================

    //=========================================================================
    // Attributes
    //=========================================================================
    /**
     * The main frame to listen.
     */
    private final MainFrame mainFrame;

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Contruct a new Window listener.
     *
     * @param newMainFrame The main frame to listen.
     */
    public WindowListenerForMainFrame(final MainFrame newMainFrame) {
        this.mainFrame = newMainFrame;
    }

    //=========================================================================
    // Getters
    //=========================================================================

    //=========================================================================
    // Setters
    //=========================================================================

    //=========================================================================
    // Inherited methods
    //=========================================================================
    /**
     * The called method when the window is activated.
     *
     * @param evt The event object relating the event that occurred.
     * @see WindowListener#windowActivated(WindowEvent).
     */
    @Override
    public final void windowActivated(final WindowEvent evt) {
    }

    /**
     * The called method when the window is closed.
     *
     * @param evt The event object relating the event that occurred.
     * @see WindowListener#windowClosed(WindowEvent).
     */
    @Override
    public final void windowClosed(final WindowEvent evt) {
    }

    /**
     * The called method when the window is closing.
     *
     * @param evt The event object relating the event that occurred.
     * @see java.awt.event.WindowAdapter#windowClosing(WindowEvent)
     */
    @Override
    public final void windowClosing(final WindowEvent evt) {
        Configuration configuration = Configuration.getInstance();

        String message = configuration.getText(Configuration.KEY_TEXT_REALLYQUIT);
        String title = configuration.getText(Configuration.KEY_TITLE_QUIT);
        if (mainFrame.getMainPanel().isShown()) {
            if (JOptionPane.showConfirmDialog(mainFrame, message, title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else {
            if (JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    /**
     * The called method when the window is deactivated.
     *
     * @param evt The event object relating the event that occurred.
     * @see WindowListener#windowDeactivated(WindowEvent).
     */
    @Override
    public final void windowDeactivated(final WindowEvent evt) {
    }

    /**
     * The called method when the window is restored.
     *
     * @param evt The event object relating the event that occurred.
     * @see java.awt.event.WindowAdapter#windowDeiconified(WindowEvent)
     */
    @Override
    public final void windowDeiconified(final WindowEvent evt) {
        for (ComponentListener tmp : mainFrame.getMainPanel().getComponentListeners()) {
            tmp.componentShown(null);
        }
    }

    /**
     * The called method when the window is iconified.
     *
     * @param evt The event object relating the event that occurred.
     * @see java.awt.event.WindowAdapter#windowIconified(WindowEvent)
     */
    @Override
    public final void windowIconified(final WindowEvent evt) {
        for (ComponentListener tmp : mainFrame.getMainPanel().getComponentListeners()) {
            tmp.componentHidden(null);
        }
    }

    /**
     * The called method when the window is opened.
     *
     * @param evt The event object relating the event that occurred.
     * @see WindowListener#windowOpened(WindowEvent).
     */
    @Override
    public final void windowOpened(final WindowEvent evt) {
    }

    //=========================================================================
    // Static methods
    //=========================================================================

    //=========================================================================
    // Methods
    //=========================================================================
}
