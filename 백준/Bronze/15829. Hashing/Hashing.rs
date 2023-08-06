use std::io::{self, Read};

const MOD:u64 = 1_234_567_891;
const FIRST:u64 = 31u64;
fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut tokens = buf.split_ascii_whitespace();

    let n = tokens.next().unwrap().parse::<usize>().unwrap();
    let s = tokens.next().unwrap().chars();
    
    let mut cache = vec![0; n+1];
    cache[0] = 1;
    cache[1] = FIRST;

    for idx in 2..=n {
        if idx % 2 == 0 {
            cache[idx] = cache[idx/2] * cache[idx/2] % MOD;
        } else {
            cache[idx] = cache[idx/2] * cache[idx/2] % MOD * FIRST % MOD;
        }
    }

    let mut hash = 0;
    for (idx, c) in s.enumerate() {
        let i = (c as u64) - 97 + 1;
        hash += cache[idx] * i % MOD;
    }

    println!("{}", hash);
}
