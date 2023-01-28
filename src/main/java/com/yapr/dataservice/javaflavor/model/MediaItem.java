package com.yapr.dataservice.javaflavor.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.List;

@Document(collection = "medias")
public class MediaItem extends AbstractDoc {
    enum Type { Live, Post, Story };

    private BigInteger ownerId;
    private String title;
    private String caption;
    private Type type;
    private Object musicInfo;
    private Object targets;
    private Object segments;
    private Object scores;
    private Object rankingMetrics;
    private ZonedDateTime uploadTime;
    private Object uploadLocation;
    private ZonedDateTime recordedTime;
    private Object recordedLocation;
    private List<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ZonedDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(ZonedDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public ZonedDateTime getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(ZonedDateTime recordedTime) {
        this.recordedTime = recordedTime;
    }
}
