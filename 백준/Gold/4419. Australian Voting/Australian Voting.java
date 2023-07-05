import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }

    int T, voters;
    VoteGraph voteGraph;
    Candidate[] candidates;
    List<Vote> deadVotes = new LinkedList<>();
    private void solution() {
        input();

        var flag = true;
        while (flag) {
            flag = process();
        }
    }

    boolean process() {

        //투표가 종료되었는지 체크하고 출력하기
        if (voteGraph.isEnd()) {
            var sb = new StringBuilder();
            var max = voteGraph.maxIdx;
            for (var candidate : candidates) {

                if (candidate.countVotes() == max) {
                    sb.append(candidate.name).append('\n');
                }
            }

            System.out.print(sb);
            return false;
        }

        //제일 표가 적은 녀석들을 전부 방출하기
        var min = voteGraph.minIdx;
        var max = voteGraph.maxIdx;
        for (var candidate : candidates) {
            if (candidate.countVotes() == min) {
                deadVotes.addAll(candidate.isOut());
            }
        }

        //방출한 사람들에게 투표했던 사람들의 표를 재분배하기
        for (var vote : deadVotes) {
            vote = vote.next;
            while (vote != null) {
                vote = candidates[vote.idx].subscribe(vote);
            }
        }

        deadVotes.clear();

        return true;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());
            candidates = new Candidate[T];

            voteGraph = new VoteGraph(1000);

            for(int i = 0; i < T; i++) {
                candidates[i] = new Candidate(br.readLine());
            }

            String line = "";
            StringTokenizer st;
            Vote first;
            Vote tmp;

            while (((line = br.readLine()) != null) && (line.length() > 0)) {
                st = new StringTokenizer(line, " ");
                first = new Vote(Integer.parseInt(st.nextToken()), null);
                tmp = first;

                for(var i = 1; i < T; i++) {
                    tmp.next = new Vote(Integer.parseInt(st.nextToken()), null);
                    tmp = tmp.next;
                }

                candidates[first.idx].subscribe(first);

                voters++;
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    class Candidate {
        private boolean isOut;
        private final LinkedList<Vote> subscribe;
        String name;

        public Candidate(String name) {
            isOut = false;
            subscribe = new LinkedList<>();
            this.name = name;

            voteGraph.addVote(-1);
        }

        public Vote subscribe(Vote vote) {
            if (isOut) {
                return vote.next;
            }

            voteGraph.addVote(subscribe.size());
            subscribe.addLast(vote);

            return null;
        }

        public int countVotes() {
            if (isOut) return -1;

            return subscribe.size();
        }

        public LinkedList<Vote> isOut() {
            voteGraph.removeCandidate(countVotes());
            isOut = true;
            return subscribe;
        }
    }
    class VoteGraph {
        private final int[] votes;
        int minIdx;
        int maxIdx;

        public VoteGraph(int total) {
            votes = new int[total + 1];
            minIdx = 0;
            maxIdx = 0;
        }

        public void addVote(int idx) {
            if (idx >= 0 && votes[idx] == 0) {
                System.err.println("addVote occur ERROR");
                return;
            }

            if (idx >= 0 ) votes[idx]--;
            votes[idx + 1]++;

            if (idx == minIdx && votes[idx] == 0) {
                minIdx++;
            }
            if (idx == maxIdx) {
                maxIdx++;
            }
        }

        public boolean isEnd() {
            return minIdx == maxIdx || maxIdx >= (voters + 1)/2;
        }

        public void removeCandidate(int idx) {
            votes[idx]--;

            if (votes[minIdx] == 0) {
                while (minIdx <= 1000 && votes[minIdx] == 0) {
                    minIdx++;
                }
            }
        }
    }
    static class Vote {
        int idx;
        Vote next;

        public Vote(int idx, Vote next) {
            this.idx = idx - 1;
            this.next = next;
        }
    }
}