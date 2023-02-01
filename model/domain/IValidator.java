package model.domain;

import com.example.playground.Notes_decorated.model.domain.entity.Note;

public interface IValidator {

    boolean validateNote(Note note);
}