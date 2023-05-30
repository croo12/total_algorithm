use std::{io::stdin, cmp::Ordering};

fn tuple_factory() -> (u32, u32) {
    let input = read_line();
    let line : Vec<&str> = input.split_whitespace().collect();

    let a : u32 = line[0].parse().expect("a 파싱 실패");
    let b : u32 = line[1].parse().expect("b 파싱 실패");

    (a, b)
}

fn compare(a : &(u32, u32), b : &(u32, u32)) -> Ordering {
    let compare = a.1.cmp(&b.1);
    match compare {
        Ordering::Equal => a.0.cmp(&b.0),
        _ => compare
    }
}

fn main() {
    let n : usize = read_line().trim().parse().expect("N입니다");
    let mut arr : Vec<(u32, u32)> = vec![(0, 0); n];

    for idx in 0..n {
        arr[idx] = tuple_factory();
    }

    arr.sort_by(|a, b| compare(a, b) );

    let mut last : (u32, u32) = (0,0);
    let mut ans : u32 = 0;
    for tup in arr {
        if last.1 <= tup.0 {
            ans += 1;
            last = tup;
        }
    }

    println!("{}",ans);
}

fn read_line() -> String {
    let mut input = String::new();

    stdin().read_line(&mut input).expect("못 읽었엉");
    input
}