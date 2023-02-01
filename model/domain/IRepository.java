package model.domain;

import com.example.playground.Notes_decorated.model.domain.entity.Note;

import java.util.List;

public interface IRepository {

    List<Note> getAllNotes();

    String createNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);
}
