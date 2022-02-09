package com.fullcycle.fccatalogo.domain.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class Video extends BaseEntity {
    private String title;
    private String description;
    private Integer yearLaunched;
    private Boolean opened;
    private String rating;
    private Float duration;
    private List<Category> categories = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private List<CastMember> castMembers = new ArrayList<>();
    private List<VideoFile> videoFiles = new ArrayList<>();

    // public Video() {}

    public Video(Integer id, UUID idUUID, String title, String description, Integer yearLaunched, Boolean opened) {
        super.setId(id);
        super.setIdUUID(idUUID);
        this.setTitle(title);
        this.setDescription(description);
        this.setYearLaunched(yearLaunched);
        this.setOpened(opened);
    }

    public Video(String title, String description, Integer yearLaunched, Boolean opened) {
        super.generateUUID();
        this.setTitle(title);
        this.setDescription(description);
        this.setYearLaunched(yearLaunched);
        this.setOpened(opened);
    }

    public Video(String title, String description, Integer yearLaunched, Boolean opened, String rating,
            Float duration) {
        super.generateUUID();
        this.setTitle(title);
        this.setDescription(description);
        this.setYearLaunched(yearLaunched);
        this.setOpened(opened);
        this.setRating(rating);
        this.setDuration(duration);
    }

    public Video(String title, String description, Integer yearLaunched, Float duration) {
        super.generateUUID();
        this.setTitle(title);
        this.setDescription(description);
        this.setYearLaunched(yearLaunched);
        this.setDuration(duration);
    }

    public Video(String title, String description, Integer yearLaunched, Float duration, List<VideoFile> videoFiles) {
        super.generateUUID();
        this.setTitle(title);
        this.setDescription(description);
        this.setYearLaunched(yearLaunched);
        this.setDuration(duration);
        this.setVideoFiles(videoFiles);
    }

    public Video(String title, String description, Integer yearLaunched, Float duration,
            List<VideoFile> videoFiles, List<Category> categories, List<Genre> genres, List<CastMember> castMembers) {
        super.generateUUID();
        this.setTitle(title);
        this.setDescription(description);
        this.setYearLaunched(yearLaunched);
        this.setDuration(duration);
        this.setVideoFiles(videoFiles);
        this.setCategories(categories);
        this.setGenres(genres);
        this.setCastMembers(castMembers);
    }

    public Video(String title, String description, Integer yearLaunched, Float duration,
            List<Category> categories, List<Genre> genres, List<CastMember> castMembers) {
        super.generateUUID();
        this.setTitle(title);
        this.setDescription(description);
        this.setYearLaunched(yearLaunched);
        this.setDuration(duration);
        this.setCategories(categories);
        this.setGenres(genres);
        this.setCastMembers(castMembers);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title == null)
            throw new IllegalArgumentException("title is marked non-null but is null");
        if (title.length() == 0)
            throw new IllegalArgumentException("title is marked non-blank but is blank");
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearLaunched() {
        return this.yearLaunched;
    }

    public void setYearLaunched(Integer yearLaunched) {
        Integer current = Calendar.getInstance().get(Calendar.YEAR);
        if (yearLaunched == null)
            throw new IllegalArgumentException("yearLaunched is marked non-null but is null");
        if (yearLaunched <= 0)
            throw new IllegalArgumentException("yearLaunched does not accept zero values");
        if (yearLaunched > current)
            throw new IllegalArgumentException("yearLaunched is greater than current year");
        this.yearLaunched = yearLaunched;
    }

    public Boolean isOpened() {
        return this.opened;
    }

    public Boolean getOpened() {
        return this.opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Float getDuration() {
        return this.duration;
    }

    public void setDuration(Float duration) {
        if (duration == null)
            throw new IllegalArgumentException("duration is marked non-null but is null");

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        this.duration = Float.valueOf(decimalFormat.format(duration));
        this.duration = duration;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        if (categories == null)
            throw new IllegalArgumentException("categories is marked non-null but is null");
        this.categories = categories;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        if (genres == null)
            throw new IllegalArgumentException("genres is marked non-null but is null");
        this.genres = genres;
    }

    public List<CastMember> getCastMembers() {
        return this.castMembers;
    }

    public void setCastMembers(List<CastMember> castMembers) {
        if (castMembers == null)
            throw new IllegalArgumentException("castMembers is marked non-null but is null");
        this.castMembers = castMembers;
    }

    public List<VideoFile> getVideoFiles() {
        return this.videoFiles;
    }

    public void setVideoFiles(List<VideoFile> videoFiles) {
        this.videoFiles = videoFiles;
    }

    public void addCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException("category is marked non-null but is null");
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException("category is marked non-null but is null");
        if (this.categories.isEmpty())
            throw new IllegalArgumentException("categories is marked Empty");
        this.categories.removeIf(c -> c.equals(category));
    }

    public void addGenre(Genre genre) {
        if (genre == null)
            throw new IllegalArgumentException("genre is marked non-null but is null");
        this.genres.add(genre);
    }

    public void removeGenre(Genre genre) {
        if (genre == null)
            throw new IllegalArgumentException("genre is marked non-null but is null");
        if (this.genres.isEmpty())
            throw new IllegalArgumentException("genres is marked Empty");
        this.genres.removeIf(c -> c.equals(genre));
    }

    public void addCastMember(CastMember castMember) {
        if (castMember == null)
            throw new IllegalArgumentException("castMember is marked non-null but is null");
        this.castMembers.add(castMember);
    }

    public void removeCastMember(CastMember castMember) {
        if (castMember == null)
            throw new IllegalArgumentException("castMember is marked non-null but is null");
        if (this.castMembers.isEmpty())
            throw new IllegalArgumentException("castMembers is marked Empty");
        this.castMembers.removeIf(c -> c.equals(castMember));
    }

}
