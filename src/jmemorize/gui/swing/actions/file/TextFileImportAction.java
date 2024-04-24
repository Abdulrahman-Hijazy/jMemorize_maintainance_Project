package jmemorize.gui.swing.actions.file;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.filechooser.FileFilter;

import jmemorize.core.Card;
import jmemorize.core.Category;
import jmemorize.core.Lesson;
import jmemorize.gui.swing.actions.file.AbstractImportAction;


import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;

import jmemorize.core.Lesson;
import jmemorize.core.io.XmlBuilder;
import jmemorize.gui.LC;
import jmemorize.gui.Localization;
import jmemorize.gui.swing.frames.MainFrame;
import jmemorize.util.ExtensionFileFilter;

public class TextFileImportAction extends AbstractImportAction {

    public TextFileImportAction()
    {
        setValues();
    }
    Card card ;

    @Override
    protected void doImport(File file, Lesson lesson) throws IOException {
        // Open the text file for reading
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into question and answer (assuming they are separated by a delimiter)
                String[] parts = line.split(";"); // Update delimiter as per your text file format
                if (parts.length == 2) {
                    String question = parts[0].trim();
                    String answer = parts[1].trim();
                    // Add the card to the lesson
                    Card card = new Card(question, answer);
                    lesson.m_rootCategory.addCard(card);
                   // lesson.addCard(question, answer);

                }
            }
        }
    }

    @Override
    protected FileFilter getFileFilter()//filter files to view txt files only
    {

        return new ExtensionFileFilter("txt", Localization.get(LC.FILE_TXT));
    }

    private void setValues()
    {
        setName("Impot Cards from a .txt file");
        //setName(Localization.get(LC.FILE_TXT));
        setMnemonic(1);
        setIcon("/resource/icons/file_saveas.gif"); //$NON-NLS-1$
    }
}
