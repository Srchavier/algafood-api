package com.fullcycle.fccatalogo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GenreTests {

    @Test
    public void createGenreWithNameTest() {
        final Genre entity = new Genre("Genre 1");

        assertNotNull(entity);
        assertEquals("Genre 1", entity.getName());
        assertTrue(entity.isValidUUID(entity.getIdUUID().toString()));
    }

    @Test
    public void createGenreWithIdAndNameTest() {
        final UUID id = UUID.randomUUID();
        Genre genre = new Genre(1, id, "Genre 1");

        assertNotNull(genre);
        assertEquals("Genre 1", genre.getName());
        assertEquals(id, genre.getIdUUID());
        assertTrue(genre.isValidUUID(genre.getIdUUID().toString()));
    }

    @Test
    public void createGenreWithNameAndCategoriesTest() {
        final Category category1 = new Category("Category 2");
        List<Category> c = new ArrayList<>();
        c.add(category1);
        Genre genre = new Genre("Genre 1", c);

        assertNotNull(genre);
        assertNotNull(c);

        assertEquals("Genre 1", genre.getName());
        assertTrue(genre.isValidUUID(genre.getIdUUID().toString()));
        assertEquals(c.size(), genre.getCategories().size());
    }

    @Test
    public void createGenreWithIdAndNameAndCategoriesTest() {
        final UUID id = UUID.randomUUID();
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");
        final List<Category> c = new ArrayList<>();
        c.add(category1);
        c.add(category2);

        Genre genre = new Genre(1, id, "Genre 1", c);

        assertNotNull(genre);
        assertNotNull(c);
        assertEquals("Genre 1", genre.getName());
        assertEquals(id, genre.getIdUUID());
        assertTrue(genre.isValidUUID(genre.getIdUUID().toString()));
        assertEquals(c.size(), genre.getCategories().size());
    }

    @Test
    public void throwIllegalArgumentExceptionWhenIdIsNull() {
        RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Genre(null, UUID.randomUUID(), ""));
        assertTrue("id is marked non-null but is null".contains(runtimeException.getMessage()));
        assertThrows(IllegalArgumentException.class, () -> new Genre(1, null, ""));
        runtimeException = assertThrows(IllegalArgumentException.class, () -> new Category(1, null, ""));
        assertTrue("id UUID is marked non-null but is null".contains(runtimeException.getMessage()));

        assertThrowsExactly(IllegalArgumentException.class, () -> new Genre(1, UUID.randomUUID(), null));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsNullAndCategoriesIsNull() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new Genre((String)null, null));
        assertTrue("name is marked non-null but is null".contains(runtimeException.getMessage()));
        runtimeException = assertThrows(IllegalArgumentException.class, () -> new Genre((String)"Genre 1", null));
        assertTrue("categories is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsBlankAndCategories() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new Genre("", null));
        assertTrue("name is marked non-blank but is blank".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsNull() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new Genre((String) null));
        assertTrue("name is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsBlank() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new Genre(""));
        assertTrue("name is marked non-blank but is blank".contains(runtimeException.getMessage()));
    }

    @Test
    public void addCategoryToGenreTest() {
        final Genre entity = new Genre("Genre 1");
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");

        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> entity.addCategory(null));
        assertTrue("category is marked non-null but is null".contains(runtimeException.getMessage()));
 
        assertNotNull(entity);
        assertNotNull(entity.getCategories());
        
        entity.addCategory(category1);
        entity.addCategory(category2);

        assertEquals(2, entity.getCategories().size());
    }

    @Test
    public void removeCategoryToGenreTest() {
        final Genre entity = new Genre("Genre 1");
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");

        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> entity.addCategory(null));
        assertTrue("category is marked non-null but is null".contains(runtimeException.getMessage()));
 
        assertNotNull(entity);
        assertNotNull(entity.getCategories());
        
        entity.addCategory(category1);
        entity.addCategory(category2);

        assertEquals(2, entity.getCategories().size());
        entity.removeCategory(category2);
        assertEquals(1, entity.getCategories().size());
        entity.removeCategory(category1);
        assertEquals(0, entity.getCategories().size());

        runtimeException = assertThrows(IllegalArgumentException.class, () -> entity.removeCategory(category1));
        assertTrue("categories is marked Empty".contains(runtimeException.getMessage()));
    }
    
}
