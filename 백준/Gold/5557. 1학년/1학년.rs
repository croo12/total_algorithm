use std::io;

fn main() {
    let mut checker = [(0 as u128,0 as u128); 21];
    
    let n: u32 = read_line().trim_end().parse().unwrap();
    let mut numbers: Vec<u8> = parse_vec(read_line());

    let last = numbers.pop().unwrap();
    checker[numbers[0] as usize] = (1, 0);
    for i in 1..(n-1) as usize {
        let number = numbers[i];
        for idx in 0..21 {
            if checker[idx].0 > 0 {
                let value = checker[idx].0;

                if idx >= number as usize {
                    checker[idx - number as usize].1 += value;
                }
                if idx + number as usize <= 20 {
                    checker[idx + number as usize].1 += value;
                }
            }
        }

        for now in &mut checker {
            now.0 = now.1;
            now.1 = 0;
        }
    }

    println!("{}", checker[last as usize].0);
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