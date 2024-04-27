import java.util.List;

public class Driver {
    public static void main(String[] args) {
        SongService realService = new RealSongService();
        SongServiceProxy proxyService = new SongServiceProxy(realService);
        System.out.println("Search by ID");
        System.out.println("Initial search by ID (0):");
        Song songById = proxyService.searchById(0);
        System.out.println("Song 1: " + songById.getTitle() + " by " + songById.getArtist());
        System.out.println("Repeating search by ID 0 (should use cache):");
        Song repeatedSongById = proxyService.searchById(0);
        System.out.println("Song 1: " + repeatedSongById.getTitle() + " by " + repeatedSongById.getArtist());
        System.out.println("Search by Title");
        System.out.println("Initial search by title 'Levitating':");
        List<Song> songsByTitle = proxyService.searchByTitle("Levitating");
        songsByTitle.forEach(song -> System.out.println("Found: " + song.getTitle() + " by " + song.getArtist()));
        System.out.println("Repeating search by title 'Levitating' (should use cache):");
        List<Song> repeatedSongsByTitle = proxyService.searchByTitle("Levitating");
        repeatedSongsByTitle.forEach(song -> System.out.println("Found: " + song.getTitle() + " by " + song.getArtist()));
        System.out.println("Search by Album");
        System.out.println("Initial search by album 'Illuminate':");
        List<Song> songsByAlbum = proxyService.searchByAlbum("Illuminate");
        songsByAlbum.forEach(song -> System.out.println("Found: " + song.getTitle() + " by " + song.getArtist()));
        System.out.println("Repeating search by album 'Illuminate' (should use cache):");
        List<Song> repeatedSongsByAlbum = proxyService.searchByAlbum("Illuminate");
        repeatedSongsByAlbum.forEach(song -> System.out.println("Found: " + song.getTitle() + " by " + song.getArtist()));
    }
}
