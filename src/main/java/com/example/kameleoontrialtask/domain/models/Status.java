package com.example.kameleoontrialtask.domain.models;

public enum Status {
    LIKE,
    DISLIKE;

    public Status inverse() {
        return this.equals(Status.LIKE) ? Status.DISLIKE : Status.LIKE;
    }
}
