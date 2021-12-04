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
package jmines.actions.info;

import java.awt.event.ActionEvent;

import jmines.actions.JMinesAction;
import jmines.dialogs.AboutDialog;
import jmines.panels.MainFrame;
import jmines.persistence.Configuration;

/**
 * The class representing the action used when the user click the About menu
 * item.
 *
 * @author Zleurtor
 */
public class About extends JMinesAction {

    //==========================================================================
    // Static attributes
    //==========================================================================
    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = -2725457376310981362L;

    //==========================================================================
    // Attributes
    //==========================================================================
    /**
     * The dialog displaying all the information.
     */
    private AboutDialog dialog;

    //==========================================================================
    // Constructors
    //==========================================================================
    /**
     * Construct a new About action.
     *
     * @param name The name of the menu item.
     * @param mainFrame The main frame of the application.
     */
    public About(final String name, final MainFrame mainFrame) {
        super(name, mainFrame);

        dialog = new AboutDialog(mainFrame.getMainPanel());

        setStatusText(Configuration.getInstance().getText(Configuration.KEY_STATUSTEXT_INFO_ABOUT));
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
     * The Method used when the user click on the menu item. This only shows a
     * dialog displaying information about JMines.
     *
     * @param evt The event object relating the event that occurred.
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public final void actionPerformed(final ActionEvent evt) {
        dialog.setVisible(true);

        super.emptyStatusBar();
    }

    //==========================================================================
    // Static methods
    //==========================================================================

    //==========================================================================
    // Methods
    //==========================================================================
}
