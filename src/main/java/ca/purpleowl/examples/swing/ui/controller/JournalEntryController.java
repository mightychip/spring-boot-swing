package ca.purpleowl.examples.swing.ui.controller;

import ca.purpleowl.examples.swing.jpa.model.JournalEntry;
import ca.purpleowl.examples.swing.jpa.repository.JournalEntryRepository;
import ca.purpleowl.examples.swing.ui.view.JournalEntryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.time.LocalDateTime;

/**
 * This this controller takes care of displaying and potentially saving a JournalEntry entity.  The preceding
 * Controller actually gets to set the model.
 */
@Controller
public class JournalEntryController extends JFrame {
    private final JournalEntryView view;
    private final JournalEntryRepository repo;
    private JournalEntry model;
    private JFrame caller;

    /**
     * This constructor is autowired and automagically gets an instance of JournalEntryRepository to allow it to save
     * records to the persistence mechanism when appropriate.
     *
     * @param journalEntryRepository - An instance of JournalEntryRepository supplied via Autowiring or Mocking.
     */
    @Autowired
    public JournalEntryController(JournalEntryRepository journalEntryRepository) {
        super("Another hideous screen?!");
        this.repo = journalEntryRepository;
        this.view = new JournalEntryView() {
            @Override
            protected void actionButtonClicked() {
                doAction();
            }
        };
        this.setContentPane(view.$$$getRootComponent$$$());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.pack();
    }

    /**
     * We allow for two potential actions.  If the JournalEntry is new, then the action is Save.  If the JournalEntry
     * already exists in the database, we just display it and close the window when we're done.
     */
    private void doAction() {
        if(model.isNew()) {
            model.setEntry(view.getText());
            model.setEntryTime(LocalDateTime.now());
            repo.save(model);
        }
        this.setVisible(false);
        model = null;
        view.setText("");
        caller.validate();
    }

    /**
     * Set the model and manipulate the view appropriately.  If the model is New, then set the View into Write Mode.  If
     * it isn't new, set it in Write mode.
     *
     * @param model - The JournalEntry object to be displayed and - maybe - edited.
     */
    public void setModel(JournalEntry model) {
        this.model = model;
        if(model.isNew()) {
            view.setText("");
            view.writeMode();
        } else {
            view.setText(model.getEntry());
            view.viewMode();
        }
    }

    /**
     * Set the View calling to this one, so that we can get it to refresh after we save an entry to the database.
     *
     * @param caller - A JFrame that instantiated this one.
     * @return A reference to this instance of JournalEntryController.  This is a builder method, because I like them.
     */
    public JournalEntryController setCaller(JFrame caller) {
        this.caller = caller;
        return this;
    }
}
