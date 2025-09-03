package com.notebooks.notesapp.service;

import com.notebooks.notesapp.entity.Notes;
import com.notebooks.notesapp.repository.NoteRepository;
import com.notebooks.notesapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@Slf4j
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public Notes addNote(Notes note) {
        log.info("Saving new note with title: {}", note.getTitle());

        // 1. Save the note to the database first
        Notes savedNote = noteRepository.save(note);

        // 2. After a successful save, append it to a file
        appendNoteToFile(savedNote);

        // 3. Return the saved note
        return savedNote;
    }

    public List<Notes> getNotesByUserId(Long userId) {
        // 1. Get the notes from the database as usual
        List<Notes> notesFromDb = noteRepository.findByUserId(userId);

        // 2. Try to read the contents of the log file
        try {
            List<String> fileLines = Files.readAllLines(Paths.get("notes.txt"));

            // 3. For each note, check if it's mentioned in the file
            for (Notes note : notesFromDb) {
                for (String line : fileLines) {
                    if (line.contains("Note ID: " + note.getId())) {
                        // 4. If it is, update its content before returning it
                        note.setContent(note.getContent() + " [Archived in file]");
                        break; // Move to the next note
                    }
                }
            }
        } catch (IOException e) {
            // If the file doesn't exist or can't be read, we'll just ignore it
            // and return the notes from the DB as is.
            System.err.println("Could not read notes.txt: " + e.getMessage());
        }

        // 5. Return the (potentially modified) list of notes
        return notesFromDb;
    }

    // Private helper method to handle file writing
    private void appendNoteToFile(Notes note) {
        String noteText = String.format("Note ID: %d | Title: %s | Content: %s%n",
                note.getId(), note.getTitle(), note.getContent());
        try {
            Files.writeString(
                    Paths.get("notes.txt"),
                    noteText,
                    StandardOpenOption.CREATE, // Create the file if it doesn't exist
                    StandardOpenOption.APPEND  // Append to the end of the file
            );
        } catch (IOException e) {
            // In a real app, you'd handle this error more gracefully (e.g., logging)
            log.error("Error writing to file for note ID: {}", note.getId(), e);
        }
    }
}