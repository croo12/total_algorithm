import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static class Robot {
        static int rear = 0;
        int location;

        public Robot(int location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return Integer.toString(location);
        }
    }
    int N, K;
    int[] A;
    boolean[] checkLocation;
    private void solution() {
        input();

        var robots = new LinkedList<Robot>();
        checkLocation = new boolean[2*N];

        var delete = new LinkedList<Robot>();

        int time = 0;

        while (K > 0) {
            time++;

            //벨트가 회전한다.
            Robot.rear++;
            Robot.rear %= 2*N;

            //나가는 길
            int exit = (2*N - Robot.rear + N - 1) % (2*N);
            //놓는 길
            int enter = (2*N - Robot.rear) % (2*N);

            //로봇 한칸씩 회전시켜보기
            for (var r : robots) {
                if (r.location == exit) {
                    delete.addLast(r);
                    switchMovable(r.location);

                } else if (!checkLocation[(r.location + 1)%(2*N)]) {
                    switchMovable(r.location);

                    //내 위치는 이제 +1임
                    r.location = (r.location + 1) % (2*N);
                    //내가 밟은 칸 내구도 낮추기
                    decreaseA(r.location);

                    //만약 나가는 문이면 나가니까 길을 막지 않는다
                    if (r.location == exit) {
                        delete.addLast(r);
                    } else {
                        checkLocation[r.location] = true;
                    }
                }
            }
            
            //로봇 삭제하기
            for (var r : delete) {
                robots.remove(r);
            }

            delete.clear();

            //로봇 놓는 칸에 로봇 없으면 새 로봇 놓기
            if (!checkLocation[enter]) {
                checkLocation[enter] = true;
                decreaseA(enter);

                robots.addLast(new Robot(enter));
            }

//            System.out.printf("rear: %d, exit_point: %d, enter: %d\n", Robot.rear, exit, enter);
//            System.out.println(Arrays.toString(A));
//            System.out.println(robots);
//            System.out.println(Arrays.toString(checkLocation));
//            System.out.println();
        }

        System.out.println(time);
    }

    void decreaseA (int idx) {
        if (A[idx] != 0)
            A[idx]--;
        if (A[idx] == 0) {
            K--;
            checkLocation[idx] = true;
        }
    }

    void switchMovable (int idx) {
        if (A[idx] > 0) {
            checkLocation[idx] = false;
        }
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            A= new int[2*N];
            for (int i = 0; i < 2*N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}