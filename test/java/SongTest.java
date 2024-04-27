import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SongTest {

    @Test
    public void testSongConstructorAndGetters() {
        Song song = new Song("Bad Guy", "Billie Eilish", "When We All Fall Asleep, Where Do We Go?", 233);

        assertEquals("Bad Guy", song.getTitle(), "The title should be initialized correctly.");
        assertEquals("Billie Eilish", song.getArtist(), "The artist should be initialized correctly.");
        assertEquals("When We All Fall Asleep, Where Do We Go?", song.getAlbum(), "The album should be initialized correctly.");
        assertEquals(233, song.getDuration(), "The duration should be initialized correctly.");
    }
}
