import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongServiceProxy implements SongService {
    private SongService realService;
    private Map<Integer, Song> idCache;
    private Map<String, List<Song>> titleCache;
    private Map<String, List<Song>> albumCache;

    public SongServiceProxy(SongService realService) {
        this.realService = realService;
        this.idCache = new HashMap<>();
        this.titleCache = new HashMap<>();
        this.albumCache = new HashMap<>();
    }

    @Override
    public Song searchById(Integer songID) {
        if (idCache.containsKey(songID)) {
            System.out.println("Fetching song ID from cache: ID " + songID);
            return idCache.get(songID);
        } else {
            Song song = realService.searchById(songID);
            if(song != null) {
                System.out.println("Fetching song ID from server: ID " + songID);
                idCache.put(songID, song);
            }
            return song;
        }
    }

    @Override
    public List<Song> searchByTitle(String title) {
        title = title.toLowerCase();
        if (titleCache.containsKey(title)) {
            System.out.println("Fetching song title from cache: Title " + title);
            return titleCache.get(title);
        } else {
            System.out.println("Fetching song title from server: Title " + title);
            List<Song> songs = realService.searchByTitle(title);
            titleCache.put(title, songs);
            return songs;
        }
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        album = album.toLowerCase();
        if (albumCache.containsKey(album)) {
            System.out.println("Fetching song album from cache: Album " + album);
            return albumCache.get(album);
        } else {
            System.out.println("Fetching song album from server: Album " + album);
            List<Song> songs = realService.searchByAlbum(album);
            albumCache.put(album, songs);
            return songs;
        }
    }
}
