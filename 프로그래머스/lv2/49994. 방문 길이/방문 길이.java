import java.util.*;
class Solution { 
    int[] y = {1,0,-1,0};
    int[] x = {0,1,0,-1};
    
    public int solution(String dirs) {
        int last = dirs.length();
        var set = new HashSet<Road>();
        int cnt = 0;
        
        Point start = new Point(0,0);
        
        while (cnt < last) {
            int order = convertOrderToIndex(dirs.charAt(cnt++));
            
            int nowX = start.x + x[order];
            int nowY = start.y + y[order];
            
            if ( nowX > 5)
                nowX = 5;
            else if ( nowX < -5)
                nowX = -5;
            else if ( nowY > 5)
                nowY = 5;
            else if ( nowY < -5)
                nowY = -5;
            else {
                Point end = new Point(nowX, nowY);
            
                set.add(new Road(start, end));
                start = end;
            }
        }
        
        return set.size();
    }
    
    public int convertOrderToIndex(char i) {
        switch (i) {
            case 'U' :
                return 0;
            case 'R' : 
                return 1;
            case 'D' : 
                return 2;
            default : 
                return 3;
        }
    }
    
    class Road {
        Point start, end;
        
        Road (Point start, Point end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public boolean equals(Object obj) {
            Road other = (Road)obj;
            return (this.start.x == other.start.x 
                && this.start.y == other.start.y 
                && this.end.x == other.end.x 
                && this.end.y == other.end.y)
                || (this.start.x == other.end.x
                && this.start.y == other.end.y
                && this.end.x == other.start.x
                && this.end.y == other.start.y);
        }
        
        @Override
        public int hashCode() {
            return (start.x + end.x) * 31 + (start.y + end.y) ;
        }
    }
    
    class Point {
        int x,y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}