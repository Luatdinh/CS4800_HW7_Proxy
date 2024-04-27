import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RealSongService implements SongService {
    private List<Song> songMetadata;

    public RealSongService() {
        this.songMetadata = new ArrayList<>();
        songMetadata.add(new Song("Watermelon Sugar", "Harry Styles", "Fine Line", 240));
        songMetadata.add(new Song("Bad Guy", "Billie Eilish", "When We All Fall Asleep, Where Do We Go?", 233));
        songMetadata.add(new Song("Circles", "Post Malone", "Hollywood's Bleeding", 204));
        songMetadata.add(new Song("Memories", "Maroon 5", "Jordi", 200));
        songMetadata.add(new Song("Levitating", "Dua Lipa", "Future Nostalgia", 212));
        songMetadata.add(new Song("Dance Monkey", "Tones and I", "The Kids Are Coming", 210));
        songMetadata.add(new Song("Blinding Lights", "The Weeknd", "After Hours", 209));
        songMetadata.add(new Song("Adore You", "Harry Styles", "Fine Line", 190));
        songMetadata.add(new Song("Someone You Loved", "Lewis Capaldi", "Divinely Uninspired to a Hellish Extent", 202));
        songMetadata.add(new Song("Señorita", "Shawn Mendes and Camila Cabello", "Illuminate", 195));
    }

    @Override
    public Song searchById(Integer songID) {
        simulateNetworkLatency();
        if (songID >= 0 && songID < songMetadata.size()) {
            return songMetadata.get(songID);
        }
        return null;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        simulateNetworkLatency();
        return songMetadata.stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        simulateNetworkLatency();
        return songMetadata.stream()
                .filter(song -> song.getAlbum().equalsIgnoreCase(album))
                .collect(Collectors.toList());
    }

    private void simulateNetworkLatency() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
