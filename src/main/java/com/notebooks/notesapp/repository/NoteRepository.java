package com.notebooks.notesapp.repository;

import com.notebooks.notesapp.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long>{
    List<Notes> findByUserId(Long userId);
}
