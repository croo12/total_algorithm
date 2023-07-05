use std::{io::stdin, collections::HashMap};

fn main() {
    let t: i32 = read_line().trim_end().parse().expect("parse err");
    
    read_line();
    let a_tmp: Vec<i32> = parse_vec(read_line());
    let a = calc_cumulative_sum(&a_tmp);
    
    read_line();
    let b_tmp: Vec<i32> = parse_vec(read_line());
    let b = calc_cumulative_sum(&b_tmp);

    let mut answer: u64 = 0;
    for key in a.keys() {
        let now = t - key;

        if let Some(tmp) = b.get(&now) {
            answer += a[&key] * tmp;
        }
    }

    println!("{}", answer);
}

fn calc_cumulative_sum(origin : &Vec<i32>) -> HashMap<i32, u64> 
{
    let mut value:Vec<i32> = Vec::new();

    let mut value_map:HashMap<i32, u64> = HashMap::new();

    value.push(origin[0]);
    value_map.insert(origin[0], 1);

    for idx in 1..origin.len() {
        let calc = value[idx-1] + origin[idx];
        value.push(calc);

        if !value_map.contains_key(&calc) {
            value_map.insert(calc, 0);
        }
        value_map.insert(calc, value_map[&calc] + 1);
    }

    for idx in 0..value.len() {
        let sum = value[idx];
        
        for minus in 0..idx {
            let calc = sum - value[minus];

            if !value_map.contains_key(&calc) {
                value_map.insert(calc, 0);
            }
            value_map.insert(calc, value_map.get(&calc).unwrap() + 1);
        }
    }

    value_map
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
