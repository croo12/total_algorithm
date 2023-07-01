use std::io::stdin;

fn main() {
    let nm: Vec<i32> = parse_vec(&read_line());
    let mut p : Vec<i32> = (0..nm[0]+1).collect();

    let mut answer = String::new();
    for _ in 0..nm[1] {
        let order: Vec<i32> = parse_vec(&read_line());

        match order[0] {
            0 => {
                union(order[1], order[2], &mut p);
            },
            1 => {
                if find_parent(order[1], &mut p) == find_parent(order[2], &mut p) {
                    answer.push_str("yes\n");
                } else {
                    answer.push_str("no\n");
                }
            },
            _ => {
                panic!("what the f");
            }
        }
    }

    print!("{}", answer);
}

fn union(parent : i32, child : i32, p : &mut Vec<i32>) {
    let a = find_parent(child, p);
    let b = find_parent(parent, p);

    p[b as usize] = a;
}

fn find_parent(me : i32, p : &mut Vec<i32>) -> i32 {
    if p[me as usize] != me {
        p[me as usize] = find_parent(p[me as usize], p);
    }
    p[me as usize]
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

    stdin().read_line(&mut input).expect("can't read");
    input
}
