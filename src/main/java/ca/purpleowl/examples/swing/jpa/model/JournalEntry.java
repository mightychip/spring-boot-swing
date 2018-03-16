package ca.purpleowl.examples.swing.jpa.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table
public class JournalEntry {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column
    private String entry;

    @Column
    private LocalDateTime entryTime;

    public JournalEntry() {}

    public Long getId() {
        return id;
    }

    public JournalEntry setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEntry() {
        return entry;
    }

    public JournalEntry setEntry(String entry) {
        this.entry = entry;
        return this;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public JournalEntry setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
        return this;
    }

    /**
     * We override this so that the JList displays the date.  There are other ways to do this, but this is one option.
     *
     * @return The String value of the Journal Entry timestamp.
     */
    @Override
    public String toString() {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(entryTime);
    }

    public boolean isNew() {
        return id == null;
    }
}
