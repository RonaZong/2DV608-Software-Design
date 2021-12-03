/*
 * This file is part of JMines.
 * Copyright (C) 2009 Zleurtor
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
 * along with JMines.  If not, see <http://www.gnu.org/licenses/>.
 */
package jmines.listeners;

import jmines.View.MainPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The listener to use to listen the mouse event and mouse moves in the game
 * panel.
 *
 * @author Zleurtor
 */
public class MouseListenerForSmileyButton implements MouseListener {

    //==========================================================================
    // Static attributes
    //==========================================================================

    //==========================================================================
    // Attributes
    //==========================================================================
    /**
     * The main panel for which the events occur.
     */
    private final MainPanel mainPanel;
    /**
     * Tell whether or not the mouse has been exited from the smiley button
     * during mouse button pressed.
     */
    private boolean exited = false;
    /**
     * Tell whether or not the timer was running when mouse button has been
     * pressed.
     */
    private boolean timerWasRunning = false;

    //==========================================================================
    // Constructors
    //==========================================================================
    /**
     * Construct a new mouse listener.
     *
     * @param newMainPanel The main panel for which the events occur.
     */
    public MouseListenerForSmileyButton(final MainPanel newMainPanel) {
        this.mainPanel = newMainPanel;
    }

    //==========================================================================
    // Getters
    //==========================================================================

    //==========================================================================
    // Setters
    //==========================================================================

    //==========================================================================
    // Inherited methods
    //==========================================================================
    /**
     * Called method when the mouse is clicked.
     *
     * @param evt The event object relating the event that occurred.
     * @see MouseListener#mouseClicked(MouseEvent)
     */
    public final void mouseClicked(final MouseEvent evt) {
        mainPanel.getTimer().cancel();
    }

    /**
     * Called method when the mouse is moved into the game panel.
     *
     * @param evt The event object relating the event that occurred.
     * @see MouseListener#mouseEntered(MouseEvent)
     */
    public final void mouseEntered(final MouseEvent evt) {
    }

    /**
     * Called method when the mouse is moved out of the game panel.
     *
     * @param evt The event object relating the event that occurred.
     * @see MouseListener#mouseExited(MouseEvent)
     */
    public final void mouseExited(final MouseEvent evt) {
        if (mainPanel.getTimer().isPaused()) {
            exited = true;
        }
    }

    /**
     * Called method when the mouse is pressed.
     *
     * @param evt The event object relating the event that occurred.
     * @see MouseListener#mousePressed(MouseEvent)
     */
    public final void mousePressed(final MouseEvent evt) {
        timerWasRunning = mainPanel.getTimer().isRunning();
        mainPanel.getTimer().pause();
    }

    /**
     * Called method when the mouse is released.
     *
     * @param evt The event object relating the event that occurred.
     * @see MouseListener#mouseReleased(MouseEvent)
     */
    public final void mouseReleased(final MouseEvent evt) {
        if (exited && timerWasRunning) {
            mainPanel.getTimer().resume();
            exited = false;
        } else {
            mainPanel.getTimer().cancel();
        }
    }

}
