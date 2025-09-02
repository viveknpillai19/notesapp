package com.notebooks.notesapp.repository;

import com.notebooks.notesapp.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long>{
}
