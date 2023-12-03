package com.rarchives.ripme.ui;

import com.rarchives.ripme.App;
import com.rarchives.ripme.uiUtils.ContextActionProtections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UIContextMenuTests {

    private JFrame frame;
    private JTextField textField;
    private ContextMenuMouseListener contextMenuMouseListener;

    @BeforeEach
    void setUp() throws IOException, InterruptedException, InvocationTargetException {
        AtomicBoolean notDone = new AtomicBoolean(true);

        SwingUtilities.invokeAndWait(() -> {
            frame = new JFrame("ContextMenuMouseListener Example");
            textField = new JTextField("Hello, world!");

            // Create an instance of ContextMenuMouseListener
            contextMenuMouseListener = new ContextMenuMouseListener(textField);

            // Add ContextMenuMouseListener to JTextField
            textField.addMouseListener(contextMenuMouseListener);

            frame.getContentPane().add(textField, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setVisible(true);

            notDone.set(false);
        });

        // Wait for the GUI to be fully initialized
        while (notDone.get()) {
            Thread.yield();
        }
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
    }

    @Test
    void testCut() {
        // Simulate a cut event
        simulateCutEvent();
        // Add assertions if needed
    }

    @Test
    void testCopy() {
        // Simulate a copy event
        simulateCopyEvent();
        // Add assertions if needed
    }

    @Test
    void testPaste() {
        // Simulate a paste event
        simulatePasteEvent();
        // Add assertions if needed
    }

    @Test
    void testSelectAll() {
        // Simulate a select all event
        simulateSelectAllEvent();
        // Add assertions if needed
    }

    @Test
    void testUndo() {
        // Simulate an undo event
        simulateUndoEvent();
        // Add assertions if needed
    }

    private void simulatePasteEvent() {
        // Save the initial text content
        String initialText = contextMenuMouseListener.getTextComponent().getText();

        // Assume there is some text to paste
        String textToPaste = "Text to paste";

        // Set the text to the clipboard
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(textToPaste);
        clipboard.setContents(stringSelection, stringSelection);

        // Simulate a paste event
        contextMenuMouseListener.getTextComponent().paste();

        // Verify that the paste operation worked
        String actualText = contextMenuMouseListener.getTextComponent().getText();

        // Check if the text was appended after the initial text
        if (actualText.equals(initialText + textToPaste)) {
            System.out.println("Paste operation successful. Text content matches.");
        } else {
            fail("Paste operation failed. Text content does not match.");
        }
    }




    private void simulateSelectAllEvent() {
        // Simulate a select all event by invoking the selectAllAction
        contextMenuMouseListener.getSelectAllAction().actionPerformed(new ActionEvent(contextMenuMouseListener.getTextComponent(), ActionEvent.ACTION_PERFORMED, ""));

        // Verify that all text is selected
        int expectedSelectionStart = 0;
        int expectedSelectionEnd = contextMenuMouseListener.getTextComponent().getText().length();
        int actualSelectionStart = contextMenuMouseListener.getTextComponent().getSelectionStart();
        int actualSelectionEnd = contextMenuMouseListener.getTextComponent().getSelectionEnd();

        if (expectedSelectionStart == actualSelectionStart && expectedSelectionEnd == actualSelectionEnd) {
            System.out.println("Select All operation successful. Text is selected.");
        } else {
            fail("Select All operation failed. Text is not selected as expected.");
        }
    }

    private void simulateUndoEvent() {

        // Simulate an undo event by invoking the undoAction
        contextMenuMouseListener.getUndoAction().actionPerformed(new ActionEvent(contextMenuMouseListener.getTextComponent(), ActionEvent.ACTION_PERFORMED, ""));

        // Verify that the undo operation worked
        String expectedText = contextMenuMouseListener.getSavedString(); // Assuming the undo reverts to the saved state
        String actualText = contextMenuMouseListener.getTextComponent().getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Undo operation successful. Text content matches.");
        } else {
            fail("Undo operation failed. Text content does not match.");
        }
    }


    private void simulateCopyEvent() {
        // Save the initial text content
        String initialText = contextMenuMouseListener.getTextComponent().getText();

        // Simulate a copy event by invoking the copyAction
        contextMenuMouseListener.getCopyAction().actionPerformed(new ActionEvent(contextMenuMouseListener.getTextComponent(), ActionEvent.ACTION_PERFORMED, ""));

        // Verify that the copy operation worked
        String expectedText = initialText; // Assuming the entire text is copied
        String actualText = contextMenuMouseListener.getDebugSavedString();

        if (expectedText.equals(actualText)) {
            System.out.println("Copy operation successful. Text content matches.");
        } else {
            fail("Copy operation failed. Text content does not match.");
        }
    }

    private  void simulateCutEvent() {
        // Save the initial text content
        String initialText = contextMenuMouseListener.getTextComponent().getText();

        // Simulate a cut event by invoking the cutAction
        contextMenuMouseListener.getCutAction().actionPerformed(new ActionEvent(contextMenuMouseListener.getTextComponent(), ActionEvent.ACTION_PERFORMED, ""));

        // Verify that the cut operation worked
        String expectedText = initialText; // Assuming the entire text is cut
        String actualText = contextMenuMouseListener.getDebugSavedString();

        if (expectedText.equals(actualText)) {
            System.out.println("Cut operation successful. Text content matches.");
        } else {
            fail("Cut operation failed. Text content does not match.");
        }
    }
    // A test implementation of JTextComponent for testing purposes
    private static class JTextComponentTestImpl extends JTextPane {
        JTextComponentTestImpl(JTextComponent delegate) {
            setText(delegate.getText());
        }
    }
}
