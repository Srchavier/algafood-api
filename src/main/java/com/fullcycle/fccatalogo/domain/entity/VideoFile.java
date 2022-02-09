package com.fullcycle.fccatalogo.domain.entity;

import java.text.DecimalFormat;
import java.util.UUID;

public class VideoFile extends BaseEntity {
    private String title;
    private Float duration;
    private String url;
    
    public VideoFile(Integer id, UUID idUUID, String title, Float duration, String url) {
        super.setId(id);
        super.setIdUUID(idUUID);
        this.setTitle(title);
        this.setDuration(duration);
        this.setUrl(url);
    }

    public VideoFile(String title, Float duration, String url) {
        super.generateUUID();
        this.setTitle(title);
        this.setDuration(duration);
        this.setUrl(url);
    }

    public VideoFile(String title, Float duration) {
        super.generateUUID();
        this.setTitle(title);
        this.setDuration(duration);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title == null)
            throw new IllegalArgumentException("title is marked non-null but is null");
        this.title = title;
    }

    public Float getDuration() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Float.valueOf(decimalFormat.format(duration));
    }

    public void setDuration(Float duration) {
        if (duration == null)
            throw new IllegalArgumentException("duration is marked non-null but is null");

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        this.duration = Float.valueOf(decimalFormat.format(duration));
        this.duration = duration;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        // if (url == null)
        // throw new IllegalArgumentException("url is marked non-null but is null");
        this.url = url;
    }

}
