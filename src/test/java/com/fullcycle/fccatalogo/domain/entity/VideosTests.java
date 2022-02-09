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
public class VideosTests {
    
    @Test
    public void throwIllegalArgumentExceptionWhenIdIsNull() {
        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> new Video(null, (UUID) null, null, null, null, null));
        assertTrue("id is marked non-null but is null".contains(runtimeException.getMessage()));
        runtimeException = assertThrows(IllegalArgumentException.class, () -> new Video(1, (UUID) null, null, null, null, null));
        assertTrue("id UUID is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenTTitleIsNull() {
        final RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Video(1, UUID.randomUUID(), null, null, null, null));
        assertTrue("title is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenTitleIsBlank() {
        final RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Video(1, UUID.randomUUID(), "", null, null, null));
        assertTrue("title is marked non-blank but is blank".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenYearLaunchedIsNull() {
        final RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Video(1, UUID.randomUUID(), "Filme 1", "teste", null, null));
        assertTrue("yearLaunched is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenYearLaunchedIsNotInRange() {
        RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Video(1, UUID.randomUUID(), "Filme 1", "teste", 0, null));
        assertTrue("yearLaunched does not accept zero values".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenYearLaunchedIsGreaterThantoday() {
        final RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Video(1, UUID.randomUUID(), "Filme 1", "teste", 2042, null));
        assertTrue("yearLaunched is greater than current year".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenDurationIsNotNull() {
        final RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Video("Filme 1", "teste", 2022, (Float) null));
        assertTrue("duration is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenCategoriesIsNotNull() {
        final RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Video("Video 1", "My Description", 2012, 100f, null, null, null));
        assertTrue("categories is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenGenresIsNotNull() {
        final List<Category> categories = new ArrayList<>();
        final RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Video("Video 1", "My Description", 2012, 100f, categories, null, null));
        assertTrue("genres is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenCastMembersIsNotNull() {
        final List<Category> categories = new ArrayList<>();
        final List<Genre> genres = new ArrayList<>();
        final RuntimeException runtimeException = assertThrowsExactly(IllegalArgumentException.class, () -> new Video("Video 1", "My Description", 2012, 100f, categories, genres, null));
        assertTrue("castMembers is marked non-null but is null".contains(runtimeException.getMessage()));
    }

    @Test
    public void createVideoWithTitleAndYearLauchedTest() {
        final Video video = new Video("Video 1", "My Description", 2007, true);

        assertNotNull(video);
        assertEquals("Video 1", video.getTitle());
        assertEquals("My Description", video.getDescription());
        assertEquals(2007, video.getYearLaunched());
        assertEquals(true, video.getOpened());
        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
    }

    @Test
    public void createVideoWithTitleAndYearLauchedAndRatingAndDurationTest() {
        final Video video = new Video("Video 1", "My Description", 1985, false, "Rating 1", 100f);

        assertNotNull(video);
        assertEquals("My Description", video.getDescription());
        assertEquals("Video 1", video.getTitle());
        assertEquals("Rating 1", video.getRating());
        assertEquals(1985, video.getYearLaunched());
        assertEquals(100f, video.getDuration());
        assertEquals(false, video.getOpened());
        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
    }

    @Test
    public void createVideoWithIdAndTitleAndDescriptionAndYearLauchedAndOpenedTest() {
        final UUID id = UUID.randomUUID();
        final Video video = new Video(1, id, "Video 1", "My Description", 2000, true);

        assertNotNull(video);
        //assertEquals(100f, video.getDuration());
        //assertEquals("Rating 1", video.getRating());
        assertEquals("Video 1", video.getTitle());
        assertEquals(id, video.getIdUUID());
        assertEquals("My Description", video.getDescription());
        assertEquals(2000, video.getYearLaunched());
        assertEquals(true, video.getOpened());
        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
    }

    @Test
    public void createVideoWithTitleAndDescriptionAndYearLauchedAndDurationTest() {
        final Video video = new Video("Video 1", "My Description", 2001, 100f);

        assertNotNull(video);
        assertEquals(100f, video.getDuration());
        //assertEquals("Rating 1", video.getRating());
        assertEquals("Video 1", video.getTitle());
        //assertEquals(id, video.getIdUUID());
        assertEquals("My Description", video.getDescription());
        assertEquals(2001, video.getYearLaunched());
        //assertEquals(true, video.getOpened());
        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
    }

    @Test
    public void createVideoWithTitleAndDescriptionAndYearLauchedAndDurationAndVideosTest() {
        final VideoFile video1 = new VideoFile("Videofile 1", 100f, "www.teste");
        final VideoFile video2 = new VideoFile("Videofile 2", 100f, "www.teste");

        final List<VideoFile> videoFiles = new ArrayList<>();
        videoFiles.add(video1);
        videoFiles.add(video2);

        final Video video = new Video("Video 1", "My Description", 2020, 100f, videoFiles);

        assertNotNull(video);
        assertEquals(100f, video.getDuration());
        assertEquals("Video 1", video.getTitle());
        assertEquals("My Description", video.getDescription());
        assertEquals(2020, video.getYearLaunched());
        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
        assertEquals(2, video.getVideoFiles().size());
    }

    @Test
    public void createVideoWithFilesTest() {
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");
        final List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        final CastMember cast1 = new CastMember("Cast Member 1", CastMemberType.TYPE2);
        final CastMember cast2 = new CastMember("Cast Member 2", CastMemberType.TYPE1);
        final List<CastMember> castMembers = new ArrayList<>();
        castMembers.add(cast1);
        castMembers.add(cast2);
        
        final Genre genre1 = new Genre("Genre 1");
        final Genre genre2 = new Genre("Genre 2");
        final List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        final VideoFile video1 = new VideoFile("Videofile 1", 100f, "www.teste");
        final VideoFile video2 = new VideoFile("Videofile 2", 100f, "www.teste");

        final List<VideoFile> videoFiles = new ArrayList<>();
        videoFiles.add(video1);
        videoFiles.add(video2);

        final Video video = new Video("Video 1", "My Description", 2012, 100f, videoFiles, categories, genres, castMembers);
        
        assertNotNull(video);
        assertNotNull(video.getCategories());
        assertNotNull(video.getCastMembers());
        assertNotNull(video.getGenres());
        assertNotNull(video.getVideoFiles());

        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
        assertEquals("Video 1", video.getTitle());
        assertEquals("My Description", video.getDescription());
        assertEquals(100f, video.getDuration());
        assertEquals(2012, video.getYearLaunched());

        assertEquals(2, video.getVideoFiles().size());
        assertEquals(2, video.getCategories().size());
        assertEquals(2, video.getCastMembers().size());
        assertEquals(2, video.getGenres().size());
    }

    @Test
    public void createVideoWithFileTest() {
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");
        final List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        final CastMember cast1 = new CastMember("Cast Member 1", CastMemberType.TYPE2);
        final CastMember cast2 = new CastMember("Cast Member 2", CastMemberType.TYPE1);
        final List<CastMember> castMembers = new ArrayList<>();
        castMembers.add(cast1);
        castMembers.add(cast2);
        
        final Genre genre1 = new Genre("Genre 1");
        final Genre genre2 = new Genre("Genre 2");
        final List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        Video video = new Video("Video 1", "video descrip", 2012, 100f, categories, genres, castMembers);

        assertNotNull(video);
        assertNotNull(video.getCategories());
        assertNotNull(video.getCastMembers());
        assertNotNull(video.getGenres());

        assertTrue(video.isValidUUID(video.getIdUUID().toString()));
        assertEquals("Video 1", video.getTitle());
        assertEquals("video descrip", video.getDescription());
        assertEquals(100f, video.getDuration());
        assertEquals(2012, video.getYearLaunched());

        assertEquals(2, video.getCategories().size());
        assertEquals(2, video.getCastMembers().size());
        assertEquals(2, video.getGenres().size());
    }

    
    @Test
    public void addCategoryToVideoTest() {
        final Video video = new Video("Video 1", "My Description", 2007, true);

        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");

        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> video.addCategory(null));
        assertTrue("category is marked non-null but is null".contains(runtimeException.getMessage()));
 
        assertNotNull(video);
        assertNotNull(video.getCategories());
        
        video.addCategory(category1);
        video.addCategory(category2);

        assertEquals(2, video.getCategories().size());
    }

    @Test
    public void removeCategoryToVideoTest() {
        final Video video = new Video("Video 1", "My Description", 2007, true);
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");

        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> video.addCategory(null));
        assertTrue("category is marked non-null but is null".contains(runtimeException.getMessage()));
 
        assertNotNull(video);
        assertNotNull(video.getCategories());
        
        video.addCategory(category1);
        video.addCategory(category2);

        assertEquals(2, video.getCategories().size());
        video.removeCategory(category2);
        assertEquals(1, video.getCategories().size());
        video.removeCategory(category1);
        assertEquals(0, video.getCategories().size());

        runtimeException = assertThrows(IllegalArgumentException.class, () -> video.removeCategory(null));
        assertTrue("category is marked non-null but is null".contains(runtimeException.getMessage()));

        runtimeException = assertThrows(IllegalArgumentException.class, () -> video.removeCategory(category1));
        assertTrue("categories is marked Empty".contains(runtimeException.getMessage()));
    }

    @Test
    public void addGenreToVideoTest() {
        final Video video = new Video("Video 1", "My Description", 2007, true);

        final Genre genre1 = new Genre("Genre 1");
        final Genre genre2 = new Genre("Genre 2");

        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> video.addGenre(null));
        assertTrue("genre is marked non-null but is null".contains(runtimeException.getMessage()));
 
        assertNotNull(video);
        assertNotNull(video.getGenres());
        
        video.addGenre(genre1);
        video.addGenre(genre2);

        assertEquals(2, video.getGenres().size());
    }

    @Test
    public void removGenreToVideoTest() {
        final Video video = new Video("Video 1", "My Description", 2007, true);
        final Genre genre1 = new Genre("Genre 1");
        final Genre genre2 = new Genre("Genre 2");

        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> video.addGenre(null));
        assertTrue("genre is marked non-null but is null".contains(runtimeException.getMessage()));
 
        assertNotNull(video);
        assertNotNull(video.getGenres());
        
        video.addGenre(genre1);
        video.addGenre(genre2);

        assertEquals(2, video.getGenres().size());
        video.removeGenre(genre1);
        assertEquals(1, video.getGenres().size());
        video.removeGenre(genre2);
        assertEquals(0, video.getGenres().size());

        runtimeException = assertThrows(IllegalArgumentException.class, () -> video.removeGenre(null));
        assertTrue("genre is marked non-null but is null".contains(runtimeException.getMessage()));

        runtimeException = assertThrows(IllegalArgumentException.class, () -> video.removeGenre(genre1));
        assertTrue("genres is marked Empty".contains(runtimeException.getMessage()));
    }

    @Test
    public void addCastMemberToVideoTest() {
        final Video video = new Video("Video 1", "My Description", 2007, true);
        final CastMember cast1 = new CastMember("Cast Member 1", CastMemberType.TYPE2);
        final CastMember cast2 = new CastMember("Cast Member 2", CastMemberType.TYPE1);

        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> video.addCastMember(null));
        assertTrue("castMember is marked non-null but is null".contains(runtimeException.getMessage()));
 
        assertNotNull(video);
        assertNotNull(video.getCastMembers());
        
        video.addCastMember(cast1);
        video.addCastMember(cast2);

        assertEquals(2, video.getCastMembers().size());
    }

    @Test
    public void removCastMemberToVideoTest() {
        final Video video = new Video("Video 1", "My Description", 2007, true);
        final CastMember cast1 = new CastMember("Cast Member 1", CastMemberType.TYPE2);
        final CastMember cast2 = new CastMember("Cast Member 2", CastMemberType.TYPE1);

        RuntimeException runtimeException = assertThrows(IllegalArgumentException.class, () -> video.addCastMember(null));
        assertTrue("castMember is marked non-null but is null".contains(runtimeException.getMessage()));
 
        assertNotNull(video);
        assertNotNull(video.getCastMembers());
        
        video.addCastMember(cast1);
        video.addCastMember(cast2);

        assertEquals(2, video.getCastMembers().size());
        video.removeCastMember(cast1);
        assertEquals(1, video.getCastMembers().size());
        video.removeCastMember(cast2);
        assertEquals(0, video.getCastMembers().size());

        runtimeException = assertThrows(IllegalArgumentException.class, () -> video.removeCastMember(null));
        assertTrue("castMember is marked non-null but is null".contains(runtimeException.getMessage()));

        runtimeException = assertThrows(IllegalArgumentException.class, () -> video.removeCastMember(cast1));
        assertTrue("castMembers is marked Empty".contains(runtimeException.getMessage()));
    }

}
