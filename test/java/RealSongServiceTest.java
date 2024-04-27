import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class RealSongServiceTest {
    private RealSongService service;

    @Before
    public void setUp() {
        service = new RealSongService();
    }

    @Test
    public void testSearchById_validId() {
        Song song = service.searchById(1);
        assertNotNull("Song should not be null", song);
        assertEquals("Bad Guy", song.getTitle());
    }

    @Test
    public void testSearchById_invalidId() {
        Song song = service.searchById(10);
        assertNull("Song should be null", song);
    }

    @Test
    public void testSearchById_negativeId() {
        Song song = service.searchById(-1);
        assertNull("Song should be null", song);
    }

    @Test
    public void testSearchByTitle_exists() {
        List<Song> songs = service.searchByTitle("Levitating");
        assertFalse("Songs list should not be empty", songs.isEmpty());
        assertEquals("Levitating", songs.get(0).getTitle());
    }

    @Test
    public void testSearchByTitle_notExists() {
        List<Song> songs = service.searchByTitle("Nonexistent Song");
        assertTrue("Songs list should be empty", songs.isEmpty());
    }

    @Test
    public void testSearchByTitle_caseInsensitive() {
        List<Song> songs = service.searchByTitle("Levitating");
        assertFalse("Songs list should not be empty", songs.isEmpty());
        assertEquals("Levitating", songs.get(0).getTitle());
    }

    @Test
    public void testSearchByAlbum_exists() {
        List<Song> songs = service.searchByAlbum("Jordi");
        assertFalse("Songs list should not be empty", songs.isEmpty());
        assertTrue("List should contain Memories", songs.stream().anyMatch(song -> song.getTitle().equalsIgnoreCase("Memories")));
    }

    @Test
    public void testSearchByAlbum_notExists() {
        List<Song> songs = service.searchByAlbum("Nonexistent Album");
        assertTrue("Songs list should be empty", songs.isEmpty());
    }

    @Test
    public void testSearchByAlbum_caseInsensitive() {
        List<Song> songs = service.searchByAlbum("Jordi");
        assertFalse("Songs list should not be empty", songs.isEmpty());
        assertTrue("List should contain Memories", songs.stream().anyMatch(song -> song.getTitle().equalsIgnoreCase("Memories")));
    }
}
