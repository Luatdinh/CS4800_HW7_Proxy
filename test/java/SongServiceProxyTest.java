import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SongServiceProxyTest {

    private SongServiceProxy proxyService;
    private List<Song> songMetadata;

    @BeforeEach
    public void setUp() {
        songMetadata = new ArrayList<>(Arrays.asList(
                new Song("Watermelon Sugar", "Harry Styles", "Fine Line", 240),
                new Song("Bad Guy", "Billie Eilish", "When We All Fall Asleep, Where Do We Go?", 233),
                new Song("Circles", "Post Malone", "Hollywood's Bleeding", 204),
                new Song("Memories", "Maroon 5", "Jordi", 200),
                new Song("Levitating", "Dua Lipa", "Future Nostalgia", 212),
                new Song("Dance Monkey", "Tones and I", "The Kids Are Coming", 210),
                new Song("Blinding Lights", "The Weeknd", "After Hours", 209),
                new Song("Adore You", "Harry Styles", "Fine Line", 190),
                new Song("Someone You Loved", "Lewis Capaldi", "Divinely Uninspired to a Hellish Extent", 202),
                new Song("Señorita", "Shawn Mendes and Camila Cabello", "Illuminate", 195)

        ));

        SongService realService = new SongService() {
            @Override
            public Song searchById(Integer songID) {
                return songMetadata.stream()
                        .filter(song -> songID.equals(songMetadata.indexOf(song)))
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public List<Song> searchByTitle(String title) {
                return songMetadata.stream()
                        .filter(song -> song.getTitle().equalsIgnoreCase(title))
                        .collect(Collectors.toList());
            }

            @Override
            public List<Song> searchByAlbum(String album) {
                return songMetadata.stream()
                        .filter(song -> song.getAlbum().equalsIgnoreCase(album))
                        .collect(Collectors.toList());
            }
        };

        proxyService = new SongServiceProxy(realService);
    }

    @Test
    public void testSearchById() {
        Song expectedSong = proxyService.searchById(1);
        Song resultFromProxyFirstCall = proxyService.searchById(1);
        Song resultFromProxySecondCall = proxyService.searchById(1);

        assertEquals(expectedSong, resultFromProxyFirstCall);
        assertSame(resultFromProxyFirstCall, resultFromProxySecondCall);
    }

    @Test
    public void testSearchByTitle() {
        List<Song> expectedSongs = proxyService.searchByTitle("Levitating");
        List<Song> resultFromProxyFirstCall = proxyService.searchByTitle("Levitating");
        List<Song> resultFromProxySecondCall = proxyService.searchByTitle("Levitating");

        assertEquals(expectedSongs, resultFromProxyFirstCall);
        assertSame(resultFromProxyFirstCall, resultFromProxySecondCall);
    }

    @Test
    public void testSearchByAlbum() {
        List<Song> expectedSongs = proxyService.searchByAlbum("Jordi");
        List<Song> resultFromProxyFirstCall = proxyService.searchByAlbum("Jordi");
        List<Song> resultFromProxySecondCall = proxyService.searchByAlbum("Jordi");

        assertEquals(expectedSongs, resultFromProxyFirstCall);
        assertSame(resultFromProxyFirstCall, resultFromProxySecondCall);
    }
}
