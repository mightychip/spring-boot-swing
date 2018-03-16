package ca.purpleowl.examples.swing.ui.controller;

import ca.purpleowl.examples.swing.jpa.model.JournalEntry;
import ca.purpleowl.examples.swing.jpa.repository.JournalEntryRepository;
import ca.purpleowl.examples.swing.ui.view.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.util.Optional;

/**
 * This is the main view of the application.  If it gets closed, then the application stops.
 */
@Controller
public class MainController extends JFrame {
    private final MainView view;
    private final JournalEntryRepository repo;
    private final JournalEntryController journalEntryController;

    /**
     * This autowired constructor automagically receives reference to both a JournalEntryRepository (for access to the
     * persistence mechanism) and a JournalEntryController for writing or displaying JournalEntries.
     *
     * @param journalEntryRepository - A JournalEntryRepository instance provided via Autowiring.
     * @param journalEntryController - A JournalEntryController provided via Autowiring.
     */
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

    /**
     * I hook the refresh of the JList up to the validate method.  Then you can call it from another view without
     * having to actually know the implementation under the hood.  It's just a JFrame that you're validating.
     */
    @Override
    public void validate() {
        super.validate();
        view.setJournalEntryListContent(repo.findAllByOrderByEntryTimeDesc().toArray());
    }
}
