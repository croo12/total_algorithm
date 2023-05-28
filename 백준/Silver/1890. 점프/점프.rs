use std::io::stdin;

fn dfs(row : usize, col : usize, n : &usize,  map : &Vec<Vec<usize>>, memo : &mut Vec<Vec<i64>>) -> i64 {
    if row >= *n || col >= *n {
        return 0;
    } else if row == *n - 1 && col == *n - 1 {
        return 1;
    } else {
        if memo[row][col] >= 0 {
            return memo[row][col];
        }

        let now = &map[row][col];

        if *now == 0 {
            memo[row][col] = 0;
            return 0;
        }

        let mut value: i64 = 0;

        value += dfs(row + now, col, n, map, memo);
        value += dfs(row, col + now, n, map, memo);

        memo[row][col] = value;
        value
    }
}

fn main() {
    //input
    let n = read_line().trim().parse::<usize>().unwrap();
    let mut map = vec![vec![0; n]; n];
    let mut memo = vec![vec![-1; n]; n];

    for i in 0..n {
        let line: Vec<usize> = read_line().trim().split_whitespace()
            .map(|x| x.parse().unwrap())
            .collect();
        
        map[i] = line;
    }

    let ans = dfs(0, 0, &n, &map, &mut memo);

    println!("{ans}");
}

fn read_line() -> String {
    let mut input = String::new();

    stdin().read_line(&mut input).expect("못 읽었엉");
    input
}