package model.domain;

import com.example.playground.Notes_decorated.model.domain.entity.Note;

public interface IMapper {

    String map(Note note);

    Note map(String line);
}
