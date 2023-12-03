package com.rarchives.ripme.ui;

import com.rarchives.ripme.App;
import com.rarchives.ripme.uiUtils.ContextActionProtections;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UIContextMenuTests {
    @Test
    void testCtrlVPasteFromClipboard() throws IOException {
        // Create a JTextField for testing
        JTextField textField = new JTextField();

        // Create a test instance of JTextComponent
        JTextComponentTestImpl textComponent = new JTextComponentTestImpl(textField);
        // Set some content in the clipboard
        String clipboardContent = "Test clipboard content";
        textField.setText(clipboardContent);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(clipboardContent), null);

        // Simulate the paste event
        ContextActionProtections.pasteFromClipboard(textComponent);

        // Verify the text in the JTextField after the paste event
        assertEquals(clipboardContent, textField.getText());
    }
    // A test implementation of JTextComponent for testing purposes
    private static class JTextComponentTestImpl extends JTextPane {
        JTextComponentTestImpl(JTextComponent delegate) {
            setText(delegate.getText());
        }
    }
}
