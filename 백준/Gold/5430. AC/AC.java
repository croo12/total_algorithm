import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    int testCase;
    String[] orders;
    int[] numberArrayLengths;
    int[][] numberArrays;

    private void solution() {
        input();

        var sb = new StringBuilder();

        for (int i = 0; i < testCase; i++) {
            sb.append(doOrder(i)).append('\n');
        }

        System.out.print(sb);
    }

    private String doOrder (int idx) {
        char[] orderList = orders[idx].toCharArray();
        int startLength = 0;
        int endLength = numberArrayLengths[idx] - 1;

        boolean isReverse = false;

        for ( var order : orderList) {
            switch ( order ) {
                case 'R' :
                    isReverse = !isReverse;
                    break;
                case 'D' :
                    if ( startLength > endLength ) return "error";

                    if ( !isReverse ) {
                        startLength++;
                    } else {
                        endLength--;
                    }
            }
        }

        var sb = new StringBuilder();
        sb.append('[');

        if ( !isReverse ) {
            for ( int i = startLength; i <= endLength; i++) {
                sb.append(numberArrays[idx][i]).append(',');
            }
        } else {
            for ( int i = endLength; i >= startLength; i--) {
                sb.append(numberArrays[idx][i]).append(',');
            }
        }

        if ( startLength <= endLength ) {
            sb.setLength(sb.length() - 1);
        }

        sb.append(']');

        return sb.toString();
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            testCase = Integer.parseInt(br.readLine());

            orders = new String[testCase];
            numberArrayLengths = new int[testCase];
            numberArrays = new int[testCase][];

            for (int i = 0; i < testCase; i++ ) {
                orders[i] = br.readLine();

                int arrayLength = Integer.parseInt(br.readLine());
                numberArrayLengths[i] = arrayLength;
                numberArrays[i] = new int[arrayLength];

                for ( int j = 0; j < arrayLength; j++) {
                    int sum = 0;
                    int now;

                    while ( (now = br.read()) != ',' && now != ']' ) {
                        if ( now >= '0' && now <= '9') {
                            sum = sum*10 + now - '0';
                        }
                    }

                    numberArrays[i][j] = sum;
                }
                br.readLine();
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}
