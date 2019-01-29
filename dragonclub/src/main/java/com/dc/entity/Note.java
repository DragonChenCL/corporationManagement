package com.dc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Note {
    private int noteId;
    private Integer associationId;
    private Integer eventId;
    private String content;
    private Timestamp noteDate;
    private String name;
    private String replay;

    @Id
    @Column(name = "note_id")
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    @Basic
    @Column(name = "association_id")
    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    @Basic
    @Column(name = "event_id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "note_date")
    public Timestamp getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Timestamp noteDate) {
        this.noteDate = noteDate;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "replay")
    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return noteId == note.noteId &&
                Objects.equals(associationId, note.associationId) &&
                Objects.equals(eventId, note.eventId) &&
                Objects.equals(content, note.content) &&
                Objects.equals(noteDate, note.noteDate) &&
                Objects.equals(name, note.name) &&
                Objects.equals(replay, note.replay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, associationId, eventId, content, noteDate, name, replay);
    }
}
