use std::io::stdin;

fn main() {
    let a = read_line().trim_end().to_string();
    let b = read_line().trim_end().to_string();

    let a_len = a.len();
    let b_len = b.len();

    let a: Vec<char> = a.chars().collect();
    let b: Vec<char> = b.chars().collect();

    let mut memo = vec![vec![0; a_len + 1]; b_len + 1];

    let mut max = 0;

    for i in 0..b_len {
        let c: char = b[i];
        for j in 0..a_len {
            if c == a[j] {
                memo[i+1][j+1] = memo[i][j] + 1;

                if memo[i+1][j+1] > max {
                    max = memo[i+1][j+1];
                }    
            }
        }
    }

    println!("{}", max);
}

fn read_line() -> String {
    let mut input = String::new();

    stdin().read_line(&mut input).expect("can't read");
    input
}
