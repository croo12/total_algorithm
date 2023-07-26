use std::io::{self, Read};

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut tokens = buf.split_ascii_whitespace();

    let n = tokens.next().unwrap().parse().unwrap();
    let c = tokens.next().unwrap().parse().unwrap();

    let mut numbers: Vec<i32> = tokens.take(n).map(|s| s.parse().unwrap()).collect();
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