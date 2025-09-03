package com.notebooks.notesapp.service;

import com.notebooks.notesapp.entity.Notes;
import com.notebooks.notesapp.entity.User;
import com.notebooks.notesapp.repository.NoteRepository;
import com.notebooks.notesapp.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    void testAddNote_Success() {
        // 1. Arrange: Set up the test conditions
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");

        Notes noteToSave = new Notes();
        noteToSave.setTitle("Test Title");
        noteToSave.setContent("Test Content");
        noteToSave.setUser(user);

        // This is the mock's behavior
        when(noteRepository.save(any(Notes.class))).thenReturn(noteToSave);

        // 2. Act: Call the method we want to test
        Notes savedNote = noteService.addNote(noteToSave);

        // 3. Assert: Check if the result is what we expect
        assertNotNull(savedNote);
        assertEquals("Test Title", savedNote.getTitle());
    }
}