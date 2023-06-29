use std::io::stdin;

fn main() {
    let _n = read_line().trim().parse::<u32>().expect("파싱 실패");
    let numbers: Vec<u32> = parse_vec(&read_line());
    let plus_multiple : Vec<u32> = parse_vec(&read_line());
    
    let mut answer : u32 = 0;
    let mut debut_group = vec![0; (plus_multiple[1] + 1) as usize];

    dfs(&numbers, &mut debut_group, 0, &mut answer);

    println!("{}", answer);
}

fn dfs(numbers : &Vec<u32>, debut_group : &mut Vec<u32>, index : usize, answer : &mut u32) {
    if index == numbers.len() {
        let mut result:u32 = 1;

        for i in debut_group {
            result *= *i;
        }

        *answer = *answer.max(&mut result);
        return;
    }

    for idx in 0..debut_group.len() {
        debut_group[idx] += numbers[index];
        dfs(numbers, debut_group, index + 1, answer);
        debut_group[idx] -= numbers[index];
    }
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
    // println!("input : {}", input);
    input
}
