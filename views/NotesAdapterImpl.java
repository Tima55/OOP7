package com.example.playground.Notes_decorated.views;

import com.example.playground.Notes_decorated.model.domain.entity.Note;

public class NotesAdapterImpl implements INotesAdapter {

    @Override
    public String getView(Note note) {
        return note.toString();
    }
}
