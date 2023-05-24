use std::{io, collections::{HashSet, VecDeque}};

fn main() {
    let input = input();

    let n_k : Vec<i32> = input.split_whitespace().map(|x| x.parse().unwrap()).collect();
    let n = n_k.get(0).unwrap();
    let k = n_k.get(1).unwrap();

    // N <= K 무조건 가능
    if n <= k {
        println!("minigimbob");
    }
    // N > K
    else {
        if bfs(n, k) {
            println!("minigimbob");
        } else {
            println!("water");
        }
    }
}

fn bfs(n :&i32, k :&i32) -> bool {

    let mut q = std::collections::VecDeque::<i32>::new(); 
    let mut vis = HashSet::<i32>::new();

    q.push_back(0);
    vis.insert(0);

    let mut cnt = 0;

    while !q.is_empty() && cnt <= *k {
        let count = q.len();
        cnt += 1;

        for _ in 0..count {
            let now = q.pop_front().unwrap();

            if now == * n { return true; }

            check(now + 1, &mut vis, &mut q);
            check(now + (now >> 1), &mut vis, &mut q);
        }
    }

    false
}

fn check(next : i32, vis: &mut HashSet<i32>, q: &mut VecDeque<i32>) {
    if !vis.contains(&next) {
        vis.insert(next);
        q.push_back(next);
    }
}

fn input() -> String {
    let mut input = String::new();

    io::stdin()
        .read_line(&mut input)
        .expect("IO Error activate");

    input
}