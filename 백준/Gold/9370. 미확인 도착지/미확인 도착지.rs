use std::collections;
use std::io::{self, Read};

const INF: usize = 987_654_321;

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut tokens = buf.split_ascii_whitespace().map(|x| x.parse().unwrap());

    let mut test_case: usize = tokens.next().unwrap();
    let mut answer = String::new();

    while test_case > 0 {
        test_case -= 1;

        let n = tokens.next().unwrap();
        let m = tokens.next().unwrap();
        let t = tokens.next().unwrap();

        let s = tokens.next().unwrap();
        let g = tokens.next().unwrap();
        let h = tokens.next().unwrap();

        let mut adj_list = Vec::<Vec<(usize, usize)>>::with_capacity(n + 1);

        for _ in 0..(n + 1) {
            adj_list.push(vec![]);
        }

        let mut base: usize = INF;

        for _ in 0..m {
            let a = tokens.next().unwrap();
            let b = tokens.next().unwrap();
            let d = tokens.next().unwrap();

            adj_list[a].push((b, d));
            adj_list[b].push((a, d));

            if (a == g && b == h) || (a == h && b == g) {
                base = d;
            }
        }

        let mut target_candidate = vec![];

        for _ in 0..t {
            target_candidate.push(tokens.next().unwrap());
        }

        target_candidate.sort();

        let adj_list = adj_list;
        let target_candidate = target_candidate;

        //sgh에서 다익스트라 돌려서 결과 리턴받기
        let from_start = dijkstra(&s, &adj_list, &n);
        let from_point1 = dijkstra(&g, &adj_list, &n);
        let from_point2 = dijkstra(&h, &adj_list, &n);

        //타겟들 돌리면서 길을 이용하는게 더 짧은 경우만 답에 넣기
        for target in target_candidate {
            let len = (from_start[g] + base + from_point2[target])
                .min(from_start[h] + base + from_point1[target]);

            if from_start[target] == len {
                answer.push_str(&format!("{} ", target));
            }
        }

        answer.push('\n');
    }

    print!("{}", answer);
}

#[derive(Eq, PartialEq)]
struct Dist(usize, usize);

impl PartialOrd for Dist {
    fn partial_cmp(&self, other: &Self) -> Option<std::cmp::Ordering> {
        Some(self.cmp(other))
    }
}

impl Ord for Dist {
    fn cmp(&self, other: &Self) -> std::cmp::Ordering {
        other.1.cmp(&self.1)
    }
}

fn dijkstra(start_point: &usize, adj_list: &Vec<Vec<(usize, usize)>>, n: &usize) -> Vec<usize> {
    let mut dist = vec![INF; *n + 1];

    let mut pq = collections::BinaryHeap::<Dist>::new();
    let mut vis = vec![false; *n + 1];

    pq.push(Dist(*start_point, 0));
    dist[*start_point] = 0;

    while !pq.is_empty() {
        let now = pq.pop().unwrap();

        if vis[now.0] {
            continue;
        }

        vis[now.0] = true;

        for &(end_point, length) in &adj_list[now.0] {
            if !vis[end_point] && dist[end_point] > dist[now.0] + length {
                dist[end_point] = dist[now.0] + length;
                pq.push(Dist(end_point, dist[now.0] + length));
            }
        }
    }

    dist
}