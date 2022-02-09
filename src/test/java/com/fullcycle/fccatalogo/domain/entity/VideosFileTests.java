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
public class VideosFileTests {
   
    @Test
    public void throwIllegalArgumentExceptionWhenTitleIsNull() {
        final RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new VideoFile(null, 100f, "www"));    
        assertTrue("title is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenDurationIsNull() {
        final RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new VideoFile("Video 1", null, "www"));    
        assertTrue("duration is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenIdIsNull() {
        RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new VideoFile(null, UUID.randomUUID(), "", 1f, ""));
        assertTrue("id is marked non-null but is null".contains(runtimeException.getMessage()));
        runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new VideoFile(1, null, "", 1f, ""));
        assertTrue("id UUID is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void createVideoWithIdAndTitleAndDurationAndUrlTest() {
        final UUID id = UUID.randomUUID();

        final VideoFile video = new VideoFile(1, id, "1", 12f, "www");

        assertNotNull(video);
        assertEquals("1", video.getTitle());
        assertEquals(12f, video.getDuration());
        assertEquals("www", video.getUrl());
        assertEquals(id, video.getIdUUID());
        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
    }

    @Test
    public void createVideoWithTitleAndDurationAndUrlTest() {
        final VideoFile video = new VideoFile("Video file teste 1", 1584f, "www");

        assertNotNull(video);
        assertEquals("Video file teste 1", video.getTitle());
        assertEquals(1584f, video.getDuration());
        assertEquals("www", video.getUrl());
        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
    }

    
    @Test
    public void createVideoWithTitleAndDurationTest() {
        final VideoFile video = new VideoFile("Video file 1", 15425f);

        assertNotNull(video);
        assertEquals("Video file 1", video.getTitle());
        assertEquals(15425f, video.getDuration());
        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
    }

}
