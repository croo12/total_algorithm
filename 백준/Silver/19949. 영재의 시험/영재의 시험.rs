use std::io::{self, Read};

const PROBLEM_COUNT: usize = 10;

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let tokens: Vec<i32> = buf.split_ascii_whitespace().map(|s| s.parse().unwrap()).collect();

    let answer = dfs(&tokens, 0, 0, 0, 0);

    println!("{}", answer);
}

fn dfs(tokens: &Vec<i32>, idx: usize, correct: i32, before: i32, before_before: i32) -> i32 {
    if PROBLEM_COUNT - idx + (correct as usize) < 5 {
        return 0;
    } else if idx == PROBLEM_COUNT {
        return if correct >= 5 {
                1
            } else {
                0 
            };
    } else {

        let mut sum = 0;

        for i in 1..=5 {
            if before == before_before && i == before {
                continue;
            }

            sum += dfs(tokens, idx + 1, correct + 
                if tokens[idx] == i { 1 } else { 0 }, 
            i, before);
        }

        sum
    }
}