use std::io::stdin;

const MAX_SIZE : usize = 100_001;

fn tuple_factory() -> (u32, u32) {
    let cn: Vec<u32> = read_line()
        .split_whitespace()
        .map(|x| x.trim().parse::<u32>().expect("안됨"))
        .collect();
    let tuple = (cn[0], cn[1]);
    tuple
}

fn main() {
    //input
    let cn = tuple_factory();
    let mut memo: Vec<u32> = vec![0; MAX_SIZE];

    //solution
    for _ in 0..cn.1 {
        //0 : 비용, 1 : 고객의 수
        let city = tuple_factory();

        for idx in 0..MAX_SIZE {
            if ((idx + city.0 as usize) < MAX_SIZE) && memo[idx + city.0 as usize] < memo[idx as usize] + city.1 {
                memo[idx + city.0 as usize] = memo[idx] + city.1;
            }
        }
    }

    //print
    for idx in 0..MAX_SIZE {
        if memo[idx] >= cn.0 {
            println!("{}", idx);
            break;
        }
    }
}

fn read_line() -> String {
    let mut input = String::new();

    stdin().read_line(&mut input).expect("못 읽었엉");
    input
}
