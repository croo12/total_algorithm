use std::io::{self, Read};

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut tokens = buf.split_ascii_whitespace().map(|s| s.parse::<i32>().unwrap());

    let n = tokens.next().unwrap();
    let m = tokens.next().unwrap();
    let l = tokens.next().unwrap();

    let mut centers: Vec<i32> = tokens.take(n as usize).collect();
    centers.sort();

    let mut start = 1;
    let mut end = l;

    let check = |i| {
        let mut before = 0;
        let mut sum = 0;

        for now in &centers {
            sum += (now - before - 1)/i;
            before = *now;
        }
        sum += (l - before - 1)/i;

        sum <= m
    };

    while start <= end {
        let mid = (start + end)/2;

        if check(mid) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }

    println!("{}", start);
}