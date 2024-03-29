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
package jmines.actions.displayandsounds;

import jmines.View.MainFrame;
import jmines.persistence.Configuration;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The super class of change buttons color action used in JMines.
 *
 * @author Zleurtor
 */
public class ChangeButtonsColor extends ChangeColor {

    //==========================================================================
    // Static attributes
    //==========================================================================
    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 2331116861580436027L;

    //==========================================================================
    // Attributes
    //==========================================================================

    //==========================================================================
    // Constructors
    //==========================================================================
    /**
     * Construct a new ChangeButtonsColor action.
     *
     * @param colorId The identifier for the color to set.
     * @param mainFrame The main frame of the application.
     */
    public ChangeButtonsColor(final byte colorId, final MainFrame mainFrame) {
        super(colorId, mainFrame);

        setStatusText(Configuration.getInstance().getConfigurableText(Configuration.KEY_STATUSTEXT_DISPAY_CHANGEBUTTONS, new String[] {getValue(Action.NAME).toString()}));
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
     * The Method used when the user click on the menu item.
     *
     * @param evt The event object relating the event that occurred.
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    public final void actionPerformed(final ActionEvent evt) {
        if (getColorId() != LOOK_AND_FEEL_DEPENDENT && getColorId() != CUSTOM) {
            getMainPanel().getGamePanel().setButtonsColor(getColor());

            getMainPanel().getGamePanel().repaint();
            Configuration.getInstance().putRealTimeconfiguration(Configuration.KEY_USER_BUTTONSCOLOR, getValue(ChangeColor.ID).toString());
        } else if (getColorId() == LOOK_AND_FEEL_DEPENDENT) {
            getMainPanel().getGamePanel().setButtonsColor(getMainPanel().getGamePanel().getLookAndFeelDependentButtonsColor());

            getMainPanel().getGamePanel().repaint();
            Configuration.getInstance().putRealTimeconfiguration(Configuration.KEY_USER_BUTTONSCOLOR, getValue(ChangeColor.ID).toString());
        } else if (getColorId() == CUSTOM) {
            java.awt.Color color = JColorChooser.showDialog(getMainPanel(), Configuration.getInstance().getText(Configuration.KEY_TITLE_CHOOSECOLOR), getColor());
            if (color != null) {
                setColor(color);
                getMainPanel().getGamePanel().setButtonsColor(color);

                getMainPanel().getGamePanel().repaint();
                Configuration.getInstance().putRealTimeconfiguration(Configuration.KEY_USER_BUTTONSCOLOR, toHexadecimal(color));
            }
        }

        super.emptyStatusBar();
    }

    //==========================================================================
    // Static methods
    //==========================================================================

    //==========================================================================
    // Methods
    //==========================================================================
}
