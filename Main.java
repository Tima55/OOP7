package com.example.playground.Notes_decorated;

import com.example.playground.Notes_decorated.di.Component;

public class Main {

    private static final Component appComponent = new Component();

    public static void main(String[] args) {

        appComponent.noteView.run();
    }
}
