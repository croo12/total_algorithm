class Solution {
    public String solution(int n, int t, int m, int p) {
        int cnt = 0;
        int num = 0;
        var sb = new StringBuilder();

        while (t > 0) {
            String line = Integer.toString (cnt, n);
            var startIdx = line.length();

            for (int i = 0; i < startIdx; i++) {
                num++;
                if ( num == p ) {
                    sb.append(line.charAt(i));
                    t--;

                    if (t <= 0) break;
                }

                if ( num == m) num = 0;
            }

            cnt++;
        }

        return sb.toString().toUpperCase();
    }
}