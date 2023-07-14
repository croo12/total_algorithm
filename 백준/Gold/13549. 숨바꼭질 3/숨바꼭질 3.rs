use std::io;
use std::collections;

const MAX : usize = 150_000;
fn main() {
    let n_k :Vec<usize> = parse_vec(read_line());
    let mut vis = [MAX as u32; MAX];
    let n = n_k[0];
    let k = n_k[1];

    struct Node {
        now: usize,
        time: u32,
    }

    let mut answer = 100_001;

    let mut q: collections::LinkedList<Node> = collections::LinkedList::new();
    q.push_back(Node{now : n, time : 0});
    vis[n] = 0;

    while let Some(tmp) = q.pop_front() {
        if tmp.now == k {
            answer = answer.min(tmp.time as usize);
        } else if tmp.now > k {
            answer = answer.min(tmp.time as usize + tmp.now - k);
        } else {

            if tmp.now < 75_000 && vis[tmp.now * 2] > tmp.time {
                vis[tmp.now * 2] = tmp.time;
                q.push_back(Node {now : tmp.now*2, time: tmp.time});
            }

            if vis[tmp.now + 1] > tmp.time + 1 {
                vis[tmp.now + 1] = tmp.time + 1;
                q.push_back(Node {now: tmp.now + 1, time: tmp.time + 1});
            }

            if tmp.now != 0 && vis[tmp.now - 1] > tmp.time + 1 {
                vis[tmp.now - 1] = tmp.time + 1;
                q.push_back(Node{now: tmp.now - 1, time: tmp.time + 1});
            }

        }
    }

    println!("{}", answer);
}

fn parse_vec<T: std::str::FromStr>(line: String) -> Vec<T>
where
    <T as std::str::FromStr>::Err: std::fmt::Debug,
{
    let list: Vec<T> = line
        .split_whitespace()
        .map(|w| w.parse::<T>().unwrap())
        .collect();

    list
}

fn read_line() -> String {
    let mut input = String::new();

    io::stdin().read_line(&mut input).expect("can't read");
    input
}