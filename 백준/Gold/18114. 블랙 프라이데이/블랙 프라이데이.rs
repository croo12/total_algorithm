use std::io;

fn main() {
    let n_c : Vec<usize> = parse_vec(read_line());
    let n = n_c[0];
    let c = n_c[1] as i32;
    let mut numbers: Vec<i32> = parse_vec(read_line());
    numbers.sort();

    if solution(&numbers, &n, &c) {
        println!("{}", 1);
    } else {
        println!("{}", 0);
    }
}

fn solution(numbers: &[i32], n: &usize, c: &i32) -> bool {

    for i in 0..*n {
        if numbers[i] == *c {
            return true;
        }

        for j in (i+1)..*n {
            let sum = numbers[i] + numbers[j];

            if sum == *c {
                return true;
            }

            if let Ok(idx) = numbers.binary_search(&(c-sum)) {
                if idx > j {
                    return true;
                }
            };
        }
    }

    false
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