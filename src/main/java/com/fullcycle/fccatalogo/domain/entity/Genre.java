package com.fullcycle.fccatalogo.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Genre extends BaseEntity {
    private String name;
    private List<Category> categories = new ArrayList<>();

    // public Genre() {}

    public Genre(String name) {
        super.generateUUID();
        this.setName(name);
    }

    public Genre(Integer id, UUID idUUID, String name) {
        super.setId(id);
        super.setIdUUID(idUUID);
        this.setName(name);
    }

    public Genre(String name, List<Category> categories) {
        super.generateUUID();
        this.setName(name);
        this.setCategories(categories);
    }

    public Genre(Integer id, UUID idUUID, String name, List<Category> categories) {
        super.setId(id);
        super.setIdUUID(idUUID);
        this.setName(name);
        this.setCategories(categories);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException("name is marked non-null but is null");
        if (name.length() == 0)
            throw new IllegalArgumentException("name is marked non-blank but is blank");
        this.name = name;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        if (categories == null)
            throw new IllegalArgumentException("categories is marked non-null but is null");
        this.categories = categories;
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

}
