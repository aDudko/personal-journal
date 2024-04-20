package ru.dudko.project.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostStatus {
    CREATED("Created", "Добавлена"),
    DELETED("Deleted", "Удалена");

    private final String key;
    private final String label;


}
