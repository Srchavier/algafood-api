package com.fullcycle.fccatalogo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryTests {

    @Test
    public void createCategoryTest() {
        final Category entity = new Category("Category 1");
        assertNotNull(entity);
        assertEquals("Category 1", entity.getName());
        assertTrue(entity.isValidUUID(entity.getIdUUID().toString()));
    }

    @Test
    public void createCategoryAndIdTest() {
        final UUID id = UUID.randomUUID();
        final Category entity = new Category(1, id, "Category 1");

        assertNotNull(entity);
        assertEquals("Category 1", entity.getName());
        assertTrue(entity.isValidUUID(id.toString()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenIdIsNull() {
        RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Category(null, UUID.randomUUID(), ""));
        assertTrue("id is marked non-null but is null".contains(runtimeException.getMessage()));
        runtimeException = assertThrows(IllegalArgumentException.class, () -> new Category(1, null, ""));
        assertTrue("id UUID is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsNull() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new Category((String) null));
        assertTrue("name is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsBlank() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new Category(""));
        assertTrue("name is marked non-blank but is blank".contains(runtimeException.getMessage()));
    }
    
}
