package model.domain;

import com.example.playground.Notes_decorated.model.domain.entity.Note;

public class NotesFactory implements INotesFactory {

    @Override
    public Note getNewNote(String header, String text) {
        return new Note(
                header,
                text
        );
    }
}
