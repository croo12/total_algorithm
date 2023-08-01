use std::io::{self, Read};
use std::collections;

struct Road {
    end_point : usize,
    weight : usize
}

impl PartialEq for Road {
    fn eq(&self, other: &Self) -> bool {
        self.weight == other.weight
    }
}

impl Eq for Road {}

impl PartialOrd for Road {
    fn partial_cmp(&self, other: &Self) -> Option<std::cmp::Ordering> {
        Some(other.cmp(self))
    }
}

impl Ord for Road {
    fn cmp(&self, other: &Self) -> std::cmp::Ordering {
        self.weight.cmp(&other.weight)
    }
}

const INF : usize = 10_987_654_321;

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut tokens = buf.split_ascii_whitespace().map(|s| s.parse::<usize>().unwrap());

    let n = tokens.next().unwrap();
    let m = tokens.next().unwrap();

    let vision: Vec<usize> = tokens.by_ref().take(n).collect();
    let mut adj_list:Vec<Vec<Road>> = Vec::with_capacity(n);

    for _ in 0..n {
        adj_list.push(Vec::new());
    }

    for _ in 0..m {
        let a = tokens.next().unwrap();
        let b = tokens.next().unwrap();
        let weight = tokens.next().unwrap();

        if (a == n - 1 || vision[a] == 0) && (b == n-1 || vision[b] == 0) {
            adj_list[a].push(Road {end_point : b, weight});
            adj_list[b].push(Road {end_point : a, weight});
        }
    }
    //adjList만듦

    let mut pq : collections::BinaryHeap<Road> = collections::BinaryHeap::with_capacity(n);
    pq.push(Road {end_point : 0, weight : 0});

    let mut visit = vec![false; n];
    let mut dist = vec![INF; n];
    dist[0] = 0;

    let mut result = INF;

    while let Some(now) = pq.pop() {
        // println!("now point : {}, now weight : {}", now.end_point, now.weight);

        if dist[now.end_point] < now.weight {
            continue;
        }

        visit[now.end_point] = true;
        if now.end_point == n - 1 {
            result = now.weight;
            break;
        }

        for road in &adj_list[now.end_point] {
            if !visit[road.end_point] && road.weight + dist[now.end_point] < dist[road.end_point] {

                pq.push(Road{end_point : road.end_point, weight : road.weight + dist[now.end_point]});
                dist[road.end_point] = road.weight + dist[now.end_point];
            }
        }
    }

    if result == INF {
        println!("{}", -1);
    } else {
        println!("{}", result);
    }
}

