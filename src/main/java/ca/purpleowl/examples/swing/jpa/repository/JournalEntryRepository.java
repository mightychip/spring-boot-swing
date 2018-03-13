package ca.purpleowl.examples.swing.jpa.repository;

import ca.purpleowl.examples.swing.jpa.model.JournalEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JournalEntryRepository extends CrudRepository<JournalEntry, Long> {
    List<JournalEntry> findAllByOrderByEntryTimeDesc();
}
