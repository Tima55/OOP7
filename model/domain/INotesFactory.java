package model.domain;

import com.example.playground.Notes_decorated.model.domain.entity.Note;

public interface INotesFactory {

    Note getNewNote(String header, String text);
}
