import java.util.*;
class Solution {
    static class Genre implements Comparable<Genre> {
        String name;
        PriorityQueue<Song> songList;
        int playCount;
        
        public Genre(String name) {
            songList = new PriorityQueue<>();
            this.name = name;
        }
        
        public int compareTo(Genre o) {
            return Integer.compare(o.playCount, playCount);
        }
    }
    
    static class Song implements Comparable<Song> {
        int idx;
        int play;
        public Song (int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
        
        public int compareTo(Song o ) {
            return Integer.compare(o.play, this.play);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int len = genres.length;
        
        var map = new HashMap<String, Genre>();
        
        String genre;
        int play;
        
        Genre value;
        for (int i = 0; i < len; i++) {
            genre = genres[i];
            play = plays[i];
            
            if ( !map.containsKey(genre)) {
                map.put(genre, new Genre(genre));
            }
            
            value = map.get(genre);
            value.songList.offer(new Song(i, play));
            value.playCount += play;
        }
        
        var pq = new PriorityQueue<Genre>();
        
        Iterator<String> iter = map.keySet().iterator();
        while ( iter.hasNext()) {
            pq.offer(map.get(iter.next()));
        }
        
        var answerList = new ArrayList<Integer>();
        
        Genre now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            answerList.add(now.songList.poll().idx);
            if ( !now.songList.isEmpty()) {
                answerList.add(now.songList.poll().idx);
            }
        }
        
        int[] answer = new int[answerList.size()];
        
        int cnt = 0;
        for (var i : answerList) {
            answer[cnt++] = i;
        }
        
        return answer;
    }
}