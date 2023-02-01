package com.example.playground.Notes_decorated.di;

import com.example.playground.Notes_decorated.model.domain.INoteController;
import com.example.playground.Notes_decorated.model.domain.IRepository;
import com.example.playground.Notes_decorated.model.domain.IStorage;
import com.example.playground.Notes_decorated.views.IView;

public class Component {

    public IStorage storage;
    public IRepository repository;
    public INoteController noteController;
    public IView noteView;

    public Component() {

        storage = Module.provideStorage();
        repository = Module.provideRepository(storage, Module.provideMapper());
        noteController = Module.provideNoteController(repository, Module.provideValidator());
        noteView = Module.provideView(
                noteController,
                Module.providePromptable(
                        Module.provideLogger()
                ),
                Module.provideNotesAdapter(),
                Module.provideNotesFactory()
        );
    }
}
