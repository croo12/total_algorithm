use std::io;

fn main() {
    let input = input();

    let mut tokens = input.split_ascii_whitespace().flat_map(str::parse::<i32>);
    let n = tokens.next().unwrap() as usize;
    let k = tokens.next().unwrap();

    let mut memo = vec![i32::MAX; n + 1];
    memo[0] = 0;

    for i in 0..n {
        memo[i + 1] = memo[i + 1].min(memo[i] + 1);
        let value = i + (i >> 1);
        if value <= n {
            memo[value] = memo[value].min(memo[i] + 1);
        }
    }

    if memo[n] > k {
        println!("water");
    } else {
        println!("minigimbob")
    }
}

fn input() -> String {
    let mut input = String::new();

    io::stdin()
        .read_line(&mut input)
        .expect("IO Error activate");

    input
}