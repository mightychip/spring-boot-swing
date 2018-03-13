package ca.purpleowl.examples.swing.ui.controller;

import ca.purpleowl.examples.swing.jpa.model.JournalEntry;
import ca.purpleowl.examples.swing.jpa.repository.JournalEntryRepository;
import ca.purpleowl.examples.swing.ui.view.JournalEntryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.time.LocalDateTime;

@Controller
public class JournalEntryController extends JFrame {
    private final JournalEntryView view;
    private final JournalEntryRepository repo;
    private JournalEntry model;
    private JFrame caller;

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

    public JournalEntryController setCaller(JFrame caller) {
        this.caller = caller;
        return this;
    }
}
