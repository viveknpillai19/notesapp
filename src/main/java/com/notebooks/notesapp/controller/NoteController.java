package com.notebooks.notesapp.controller;

import com.notebooks.notesapp.entity.Notes;
import com.notebooks.notesapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService)
    {
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public Notes createNote(@RequestBody Notes notes){
        return noteService.addNote(notes);
    }
    @GetMapping("/{userId}")
    public List<Notes> getNotesForUser(@PathVariable Long userId) {
        return noteService.getNotesByUserId(userId);
    }
}
