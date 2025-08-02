package behavorial;

import java.util.*;

class Video {
    private String title;
    
    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}

interface  Playlist {
    PlaylistIterator createIterator();
}

class YoutubePlaylist implements Playlist{
    private List<Video> videos;

    public YoutubePlaylist() {
        this.videos = new ArrayList<>();
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public List<Video> getVideos() {
        return videos;
    }

    @Override
    public PlaylistIterator createIterator() {
       return new YoutubePlaylistIterator(videos);
    }

    
}

// iterator interface
interface PlaylistIterator {
    boolean hasNext();
    Video next();    
}

// concrete iterator class
class YoutubePlaylistIterator implements PlaylistIterator {

    private List<Video> videos;
    private int position;

    public YoutubePlaylistIterator(List<Video> videos) {
        this.videos = videos;
        this.position = 0;
    }

    public void reset() {
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < videos.size();
    }

    @Override
    public Video next() {
        return hasNext() ? videos.get(position++) : null;
    }
}


public class Iterator {
    public static void main(String[] args) {
        
        YoutubePlaylist playlist = new YoutubePlaylist();
        playlist.addVideo(new Video("System Design Basics"));
        playlist.addVideo(new Video("System Design Advanced"));

        PlaylistIterator iterator = playlist.createIterator() ;

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
    }
}
