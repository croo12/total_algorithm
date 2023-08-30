import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    char[] words;
    char[][] bridge;

    private void solution() {
        input();

        int width = bridge[0].length;
        int len = words.length;

        int[][][] cache = new int[2][width+1][len+1];

        cache[0][width][len] = 1;
        cache[1][width][len] = 1;

        for (int i = width - 1; i >= 0; i--) {
            //다리 탐색하기
            char now1 = bridge[0][i];
            char now2 = bridge[1][i];

            for (int j = 0; j < len; j++) {
                cache[0][i][j] = cache[0][i+1][j];
                cache[1][i][j] = cache[1][i+1][j];

                //이거 문자열의 어떤 부분임
                if (words[j] == now1) cache[0][i][j] += cache[1][i+1][j+1];
                if (words[j] == now2) cache[1][i][j] += cache[0][i+1][j+1];
            }

            cache[0][i][len] = 1;
            cache[1][i][len] = 1;
        }

        System.out.println(cache[0][0][0] + cache[1][0][0]);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            words = br.readLine().toCharArray();
            bridge = new char[2][];
            bridge[0] = br.readLine().toCharArray();
            bridge[1] = br.readLine().toCharArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}