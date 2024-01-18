use std::io::{self, Read};

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let tokens = buf.split_ascii_whitespace();

    let mut students = [false; 31];
    students[0] = true;

    let good = tokens.map(|x| x.parse::<usize>().unwrap());
    good.into_iter().for_each(|x| students[x] = true);
    (1..=30).for_each(|i| if !students[i] { println!("{}", i)});
}
