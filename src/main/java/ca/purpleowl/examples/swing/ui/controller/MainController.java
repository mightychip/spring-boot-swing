package ca.purpleowl.examples.swing.ui.controller;

import ca.purpleowl.examples.swing.jpa.model.JournalEntry;
import ca.purpleowl.examples.swing.jpa.repository.JournalEntryRepository;
import ca.purpleowl.examples.swing.ui.view.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.util.Optional;

@Controller
public class MainController extends JFrame {
    private final MainView view;
    private final JournalEntryRepository repo;
    private final JournalEntryController journalEntryController;


    @Autowired
    public MainController(JournalEntryRepository journalEntryRepository, JournalEntryController journalEntryController) {
        super("What a hideous application!");

        this.repo = journalEntryRepository;
        this.journalEntryController = journalEntryController.setCaller(this);

        this.view = new MainView() {
            @Override
            protected void newEntryButtonClicked() {
                doNewEntry();
            }

            @Override
            protected void viewEntryButtonClicked() {
                doViewEntry();
            }
        };

        this.setContentPane(view.$$$getRootComponent$$$());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        view.setJournalEntryListContent(repo.findAllByOrderByEntryTimeDesc().toArray());

        this.pack();
    }

    private void doNewEntry() {
        journalEntryController.setModel(new JournalEntry());
        journalEntryController.setVisible(true);
    }

    private void doViewEntry() {
        Long id = ((JournalEntry)view.getSelectedEntry()).getId();
        Optional<JournalEntry> maybeEntry = repo.findById(id);
        journalEntryController.setModel(maybeEntry.orElse(new JournalEntry()));
        journalEntryController.setVisible(true);
    }

    @Override
    public void validate() {
        super.validate();
        view.setJournalEntryListContent(repo.findAllByOrderByEntryTimeDesc().toArray());
    }
}
