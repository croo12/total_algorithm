import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int N, M;
    int trueMan;
    long know;
    long[] parties;
    private void solution() {
        input();


        for (int i= 0; i < M; i++)
            for (var party : parties)
                if ((know & party) != 0)
                    know |= party;

        int answer = 0;
        for (var party : parties) {
            if ((party & know) == 0) answer++;
        }

        System.out.println(answer);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            //진실을 아는 사람 수
            st = new StringTokenizer(br.readLine());
            know = 0L;
            trueMan = Integer.parseInt(st.nextToken());
            for (int i= 0; i < trueMan; i++) {
                know += 1L << Integer.parseInt(st.nextToken());
            }


            parties = new long[M];
            long now;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int K = Integer.parseInt(st.nextToken());
                now = 0L;
                while (K-- > 0) {
                    now += 1L << Integer.parseInt(st.nextToken());
                }

                if ((know & now) != 0) {
                    know |= now;
                }

                parties[i] = now;
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}