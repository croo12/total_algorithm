use std::{io::stdin, collections::HashMap};

fn main() {
    let t: i32 = read_line().trim_end().parse().expect("parse err");
    
    let a_len: usize = read_line().trim_end().parse().expect("parse err");
    let a_tmp: Vec<i32> = parse_vec(read_line());
    let a = calc_cumulative_sum(&a_tmp, &a_len);
    
    let b_len: usize = read_line().trim_end().parse().expect("parse err");
    let b_tmp: Vec<i32> = parse_vec(read_line());
    let b = calc_cumulative_sum(&b_tmp, &b_len);

    let mut answer: u64 = 0;
    for key in a.keys() {
        let now = t - key;

        if let Some(tmp) = b.get(&now) {
            answer += a[&key] * tmp;
        }
    }

    println!("{}", answer);
}

fn calc_cumulative_sum(origin : &Vec<i32>, len: &usize) -> HashMap<i32, u64> 
{
    let mut sums:Vec<i32> = vec![0; *len + 1];
    let mut map:HashMap<i32, u64> = HashMap::new();

    sums[1] = origin[0];

    for idx in 1..=*len {
        sums[idx] = sums[idx-1] + origin[idx-1];
    }

    for idx in 0..=*len {
        for minus in 0..idx {
            *map.entry(sums[idx] - sums[minus]).or_insert(0) += 1;
        }
    }

    map
}

fn parse_vec<T: std::str::FromStr> (line : String ) -> Vec<T> 
    where <T as std::str::FromStr>::Err: std::fmt::Debug  
{ 
    
    let list: Vec<T> = line.split_whitespace()
        .map(|w| w.parse::<T>().unwrap())
        .collect(); 
    
    list
}

fn read_line() -> String {
    let mut input = String::new();

    stdin().read_line(&mut input).expect("can't read");
    input
}