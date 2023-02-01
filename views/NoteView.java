package com.example.playground.Notes_decorated.views;

import com.example.playground.Notes_decorated.model.domain.INoteController;
import com.example.playground.Notes_decorated.model.domain.INotesFactory;
import com.example.playground.Notes_decorated.model.domain.entity.Note;

import java.util.Arrays;
import java.util.List;

public class NoteView implements IView {

    private final INoteController noteController;

    private final Promptable promptable;

    private final INotesAdapter notesAdapter;

    private final INotesFactory notesFactory;

    public NoteView(
            INoteController noteController,
            Promptable promptable,
            INotesAdapter notesAdapter,
            INotesFactory notesFactory
    ) {
        this.noteController = noteController;
        this.promptable = promptable;
        this.notesAdapter = notesAdapter;
        this.notesFactory  =notesFactory;
    }

    public void run() {
        Intentions intention = Intentions.NONE;

        while (true) {
            String command = promptable.prompt("Введите команду: " +
                    Arrays.toString(Intentions.values())+ " ");
            intention = Intentions.valueOf(command.toUpperCase());
            if (intention == Intentions.EXIT) return;
            try {
                switch (intention) {
                    case CREATE:
                        String header = promptable.prompt("Введите заголовок: ");
                        String text = promptable.prompt("Введите текст: ");
                        noteController.saveNote(new Note(header, text));
                        break;
                    case READ:
                        String id = promptable.prompt("Идентификатор записки: ");
                        Note note = noteController.readNote(id);
                        promptable.print(note.toString());
                        break;
                    case LIST:
                        List<Note> lst = noteController.readList();
                        lst.forEach(i -> promptable.print(notesAdapter.getView(i) + "\n"));
                        break;
                    case UPDATE:
                        String numId = promptable.prompt("Какую записку редактировать? Введите номер ID: ");
                        noteController.idPresenceValidation(numId);
                        noteController.updateNote(numId, createNote());
                        break;
                    case DELETE:
                        String deleteId = promptable.prompt("Какой контакт удалить? Введите номер ID: ");
                        noteController.idPresenceValidation(deleteId);
                        noteController.deleteNote(deleteId);
                        noteController.readList()
                                .forEach(i -> promptable.print(notesAdapter.getView(i) + "\n"));
                        break;
                }
            } catch (Exception e) {
               promptable.print("Oopsie!\n" + e.getMessage());
            }
        }
    }

    private Note createNote() {
        String header = promptable.prompt("Заголовок: ");
        String text = promptable.prompt("Текст: ");
        return notesFactory.getNewNote(header, text);
    }
}
