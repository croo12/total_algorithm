use std::io::stdin;

fn vec_factory() -> Vec<u32> {
    let cn: Vec<u32> = read_line()
        .split_whitespace()
        .map(|x| x.trim().parse::<u32>().expect("안됨"))
        .collect();
    
    cn
}

fn main() {
    let _number = read_line();

    let trees = vec_factory();

    let mut two : u32 = 0;
    let mut one : u32 = 0;

    for number in trees {

        two += number / 2;
        one += number % 2;
    }

    while two > one {
        one += 2;
        two -= 1;
    }

    let ans : &str;
    if two == one {ans = "YES";}
    else {ans = "NO";}

    println!("{}", ans);
}

fn read_line() -> String {
    let mut input = String::new();

    stdin().read_line(&mut input).expect("못 읽었엉");
    input
}
