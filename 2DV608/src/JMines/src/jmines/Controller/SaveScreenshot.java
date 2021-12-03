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
package jmines.actions.other;

import jmines.actions.JMinesAction;
import jmines.View.MainFrame;
import jmines.persistence.Configuration;
import jmines.persistence.ImageAccess;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class representing the action used when the user click on the save
 * screenshot menu item.
 *
 * @author Zleurtor
 */
public class SaveScreenshot extends JMinesAction {

    //==========================================================================
    // Static attributes
    //==========================================================================
    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 5073524774158637185L;
    /**
     * The suffix of saved images.
     */
    private static final String SUFFIX = Configuration.getInstance().getString(Configuration.KEY_FILE_SCREENSHOT_SUFFIX);

    //==========================================================================
    // Attributes
    //==========================================================================

    //==========================================================================
    // Constructors
    //==========================================================================
    /**
     * Construct a new SaveScreenshot action.
     *
     * @param name The name of the menu item.
     * @param mainFrame The main frame of the application.
     */
    public SaveScreenshot(final String name, final MainFrame mainFrame) {
        super(name, mainFrame);

        setStatusText(Configuration.getInstance().getText(Configuration.KEY_STATUSTEXT_OTHER_SAVESCREENSHOT));
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

     * @param evt The event object relating the event that occurred.
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    public final void actionPerformed(final ActionEvent evt) {
        boolean running = getMainPanel().getTimer().isRunning();
        if (running) {
            getMainPanel().getTimer().pause();
        }

        // Create the dialog used to ask the user the name of the file to write
        // to.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setDialogTitle(Configuration.getInstance().getText(Configuration.KEY_TITLE_SAVESCREENSHOT));

        for (FileFilter filter : fileChooser.getChoosableFileFilters()) {
            fileChooser.removeChoosableFileFilter(filter);
        }

        fileChooser.setFileFilter(Configuration.getScreenshotFileFilter());

        // Ask the user the filename
        fileChooser.showSaveDialog(getMainPanel());
        File file = fileChooser.getSelectedFile();
        if (file == null) {
            if (running) {
                getMainPanel().getTimer().resume();
            }
            super.emptyStatusBar();
            return;
        } else if (!file.getName().endsWith(SUFFIX)) {
            file = new File(file.getAbsolutePath() + SUFFIX);
        }

        // Write
        BufferedImage image = new BufferedImage(getMainPanel().getWidth(), getMainPanel().getHeight(), BufferedImage.TYPE_INT_ARGB);
        getMainPanel().paint(image.getGraphics());
        try {
            ImageAccess.saveImage(image, SUFFIX.substring(1), file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getClass().getSimpleName() + " (" + e.getMessage() + ")", Configuration.getInstance().getText(Configuration.KEY_TITLE_ERROR), JOptionPane.ERROR_MESSAGE);
        }

        if (running) {
            getMainPanel().getTimer().resume();
        }
        super.emptyStatusBar();
    }
}
