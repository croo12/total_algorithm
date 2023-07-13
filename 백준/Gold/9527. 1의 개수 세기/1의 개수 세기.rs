use std::io::stdin;

fn main() {
    let a_b : Vec<u64> = parse_vec(read_line());
    let a = a_b[0];
    let b = a_b[1];

    println!("{}", calc_one_count(b) - calc_one_count(a-1));
}

fn calc_one_count(number : u64) -> u64 {
    if number == 0 {
        return 0;
    }

    let length  = (number as f64).log2() as u64 + 1;

    let mut checker: u64 = 1;

    let mut sum = 0;
    for _ in 0..length {
        checker <<= 1;

        let pattern = (number + 1) / checker;
        let namuji = (number + 1) % checker;

        sum += pattern * checker/2 + 
                if namuji <= checker/2 {
                    0
                } else {
                    namuji - checker/2
                }
    }

    sum
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

    stdin().read_line(&mut input).expect("can't read");
    input
}
