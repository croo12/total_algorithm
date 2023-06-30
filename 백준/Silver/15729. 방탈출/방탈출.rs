use std::io::stdin;

fn main() {
    let n = read_line().trim_end().parse::<usize>().expect("파싱 실패");
    let target: Vec<u8> = parse_vec(&read_line());
    let mut me = vec![0 as u8; n];

    let mut answer = 0;

    for idx in 0..n {
        if target[idx] != me[idx] {

            answer += 1;
            me[idx] = target[idx];
            
            if idx < n - 1 {
                me[idx+1] ^= 1;
            }
            if idx < n - 2 {
                me[idx+2] ^= 1;
            }
        }
    }

    println!("{}", answer);
}

fn parse_vec<T : std::str::FromStr> (line : &String ) -> Vec<T> 
    where <T as std::str::FromStr>::Err: std::fmt::Debug  
{ 
    
    let list: Vec<T> = line.split_whitespace()
        .map(|w| w.parse::<T>().unwrap())
        .collect(); 
    
    list
}

fn read_line() -> String {
    let mut input = String::new();

    stdin().read_line(&mut input).expect("못 읽었엉");
    input
}
