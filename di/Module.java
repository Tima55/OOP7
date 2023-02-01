package com.example.playground.Notes_decorated.di;

import com.example.playground.Notes_decorated.controllers.NoteController;
import com.example.playground.Notes_decorated.model.data.FileStorage;
import com.example.playground.Notes_decorated.model.domain.ILogger;
import com.example.playground.Notes_decorated.model.domain.IMapper;
import com.example.playground.Notes_decorated.model.domain.INoteController;
import com.example.playground.Notes_decorated.model.domain.INotesFactory;
import com.example.playground.Notes_decorated.model.domain.IRepository;
import com.example.playground.Notes_decorated.model.domain.IStorage;
import com.example.playground.Notes_decorated.model.domain.IValidator;
import com.example.playground.Notes_decorated.model.domain.LoggerImpl;
import com.example.playground.Notes_decorated.model.domain.MapperIml;
import com.example.playground.Notes_decorated.model.data.RepositoryImpl;
import com.example.playground.Notes_decorated.model.domain.NotesFactory;
import com.example.playground.Notes_decorated.views.INotesAdapter;
import com.example.playground.Notes_decorated.views.IView;
import com.example.playground.Notes_decorated.views.NoteView;
import com.example.playground.Notes_decorated.views.NotesAdapterImpl;
import com.example.playground.Notes_decorated.views.Promptable;
import com.example.playground.Notes_decorated.views.PromptableImpl;
import com.example.playground.Notes_decorated.views.PromptableLoggingDecorator;

public class Module {

    public static IStorage provideStorage() {
        return new FileStorage("notes.txt");
    }

    public static IMapper provideMapper() {
        return new MapperIml();
    }

    public static IValidator provideValidator() {
        return new MapperIml();
    }

    public static IRepository provideRepository(
            IStorage storage,
            IMapper mapper
    ) {
        return new RepositoryImpl(mapper, storage);
    }

    public static INoteController provideNoteController(
            IRepository repository,
            IValidator validator
    ) {
        return new NoteController(repository, validator);
    }

    public static ILogger provideLogger() {
        return new LoggerImpl("log.txt");
    }

    public static Promptable providePromptable(
            ILogger logger
    ) {
        return new PromptableLoggingDecorator(
                new PromptableImpl(),
                logger
        );
    }

    public static INotesAdapter provideNotesAdapter() {
        return new NotesAdapterImpl();
    }

    public static INotesFactory provideNotesFactory() {
        return new NotesFactory();
    }

    public static IView provideView(
            INoteController controller,
            Promptable promptable,
            INotesAdapter notesAdapter,
            INotesFactory notesFactory
    ) {
        return new NoteView(controller, promptable, notesAdapter, notesFactory);
    }
}
