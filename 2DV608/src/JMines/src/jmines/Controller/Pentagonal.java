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
package jmines.actions.board;

import jmines.GameBoard;
import jmines.TilesShapeUnsupportedException;
import jmines.actions.JMinesAction;
import jmines.View.MainFrame;
import jmines.persistence.Configuration;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The class representing the action used when the user click the Pentagonal menu
 * item.
 *
 * @author Zleurtor
 */
public class Pentagonal extends JMinesAction {

    //==========================================================================
    // Static attributes
    //==========================================================================
    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 9076166126927136088L;

    //==========================================================================
    // Attributes
    //==========================================================================

    //==========================================================================
    // Constructors
    //==========================================================================
    /**
     * Construct a new Pentagonal action.
     *
     * @param name The name of the menu item.
     * @param mainFrame The main frame of the application.
     */
    public Pentagonal(final String name, final MainFrame mainFrame) {
        super(name, mainFrame);

        setStatusText(Configuration.getInstance().getText(Configuration.KEY_STATUSTEXT_BOARD_PENTAGONAL));
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
     * The Method used when the user click on the menu item. Restart a new game
     * using pentagonal buttons.
     *
     * @param evt The event object relating the event that occurred.
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    public final void actionPerformed(final ActionEvent evt) {
        try {
            getMainPanel().getGamePanel().getGameBoard().setTilesShape(GameBoard.SHAPE_PENTAGONAL);

            int width = getMainPanel().getGamePanel().getGameBoard().getWidth();
            int height = getMainPanel().getGamePanel().getGameBoard().getHeight();
            int maxMines = GameBoard.getMaxMines(GameBoard.SHAPE_PENTAGONAL, width, height);
            if (getMainPanel().getGamePanel().getGameBoard().getNumberOfMines() > maxMines) {
                getMainPanel().getGamePanel().getGameBoard().setNumberOfMines(maxMines, true);
                Configuration.getInstance().putRealTimeconfiguration(Configuration.KEY_USER_DIFFICULTY,
                        Configuration.DIFFICULTY_CUSTOM + Configuration.COMA + width + Configuration.COMA + height + Configuration.COMA + maxMines);
            }
        } catch (TilesShapeUnsupportedException e) {
            JOptionPane.showMessageDialog(null, e.getClass().getSimpleName() + " (" + e.getMessage() + ")", Configuration.getInstance().getText(Configuration.KEY_TITLE_ERROR), JOptionPane.ERROR_MESSAGE);
        }
        getMainPanel().getGamePanel().getGameBoard().initialize();

        Configuration.getInstance().putRealTimeconfiguration(Configuration.KEY_USER_SHAPE, Configuration.SHAPE_PENTAGONAL);

        getMainPanel().manageSmiley();
        super.emptyStatusBar();
    }

    //==========================================================================
    // Static methods
    //==========================================================================

    //==========================================================================
    // Methods
    //==========================================================================
}