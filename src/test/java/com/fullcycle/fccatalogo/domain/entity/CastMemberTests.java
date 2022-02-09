package com.fullcycle.fccatalogo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CastMemberTests {

    @Test
    public void createCastMemberWithNameTest() {
        final CastMember cast = new CastMember("Cast Member 1");

        assertNotNull(cast);
        assertEquals("Cast Member 1", cast.getName());
        assertTrue(cast.isValidUUID(cast.getIdUUID().toString()));
    }

    @Test
    @EnumSource(value = CastMemberType.class)
    public void createCastMemberWithNameAndTypeTest() {
        final CastMemberType type1 = CastMemberType.TYPE1;
        final CastMemberType type2 = CastMemberType.TYPE2;

        final CastMember cast1 = new CastMember("Cast Member 1", type1);

        assertNotNull(cast1);
        assertEquals("Cast Member 1", cast1.getName());
        assertEquals(cast1.getType(), type1);
        assertTrue(cast1.isValidUUID(cast1.getIdUUID().toString()));
        assertTrue(CastMemberType.valueOf(cast1.getType()));

        final CastMember cast2 = new CastMember("Cast Member 2", type2);

        assertNotNull(cast2);
        assertEquals("Cast Member 2", cast2.getName());
        assertEquals(cast2.getType(), type2);
        assertTrue(cast2.isValidUUID(cast2.getIdUUID().toString()));
        assertTrue(CastMemberType.valueOf(cast2.getType()));
    }

    @Test
    @EnumSource(value = CastMemberType.class)
    public void createCastMemberWithNameAndTypeAndIdTest() {
        final UUID id = UUID.randomUUID();
        final CastMemberType type1 = CastMemberType.TYPE1;
        final CastMember cast1 = new CastMember(1, id, "Cast Member 1", type1);

        assertNotNull(cast1);
        assertEquals("Cast Member 1", cast1.getName());
        assertEquals(cast1.getType(), type1);
        assertTrue(cast1.isValidUUID(id.toString()));
        assertTrue(CastMemberType.valueOf(cast1.getType()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenIdIsNull() {
        RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new CastMember(null, UUID.randomUUID(), "", CastMemberType.TYPE1));
        assertTrue("id is marked non-null but is null".contains(runtimeException.getMessage()));
        runtimeException = assertThrows(IllegalArgumentException.class, () -> new CastMember(1, null, "", CastMemberType.TYPE1));
        assertTrue("id UUID is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsBlank() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new CastMember(null));
        assertTrue("name is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsNull() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new CastMember(""));
        assertTrue("name is marked non-blank but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsBlankAndTypeIsNull() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new CastMember(null, null));
        assertTrue("name is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsBlankAndType() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new CastMember(null, CastMemberType.TYPE1));
        assertTrue("name is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsNullAndType() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new CastMember("",  CastMemberType.TYPE1));
        assertTrue("name is marked non-blank but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenTypeIsNull() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new CastMember("teste", null));
        assertTrue("type is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenTypeIsNotValid() throws IllegalArgumentException {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            CastMember castMember = mock(CastMember.class);
            castMember.setName("teste");
            doThrow(IllegalArgumentException.class).when(castMember).setType(CastMemberType.TYPE1);
            castMember.setType(CastMemberType.TYPE1);
        });
    }
    
}
