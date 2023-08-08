use std::io::{self, Read};

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut tokens = buf
        .split_ascii_whitespace()
        .map(|s| s.parse::<i32>().unwrap());

    let mut answer = String::new();
    loop {
        let mut abc:Vec<i32> = tokens.by_ref().take(3).collect();
        
        if abc[0] == 0 {
            print!("{}", answer);
            return;
        }
        
        abc.sort();
        
        if abc[0] == abc[1] && abc[1] == abc[2] {
            answer.push_str("Equilateral\n");
        } else if abc[2] >= abc[1] + abc[0] {
            answer.push_str("Invalid\n");
        } else if abc[0] != abc[1] && abc[1] != abc[2] && abc[2] != abc[0] {
            answer.push_str("Scalene\n");
        } else {
            answer.push_str("Isosceles\n");
        }
    }
}