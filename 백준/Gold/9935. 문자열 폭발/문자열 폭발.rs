use std::io::{self, Read};

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut tokens = buf.split_ascii_whitespace();

    let origin = tokens.next().unwrap().chars();
    let bomb: Vec<char> = tokens
        .next()
        .unwrap()
        .chars()
        .collect();

    let mut stk = vec![37; 1_000_000];
    let mut rear = 0;
    
    let mut answer = String::with_capacity(1_000_000);

    let mut bomb_key = 0;
    let bomb_len = bomb.len();
    for c in origin {

        if c == bomb[bomb_key] {
            stk[rear] = bomb_key;
            rear += 1;
            bomb_key += 1;

            if bomb_key == bomb_len {
                rear -= bomb_len;
                bomb_key = if rear == 0 {0} else { stk[rear - 1] + 1 };
            }
        } else if c == bomb[0] {
            stk[rear] = 0;
            rear += 1;
            bomb_key = 1;
        } else {
            for i in 0..rear {
                answer.push(bomb[stk[i]]);
            }

            rear = 0;
            bomb_key = 0;
            answer.push(c);
        }
    }

    for i in 0..rear {
        answer.push(bomb[stk[i]]);
    }

    println!("{}", if answer.len() != 0 { answer } else { String::from("FRULA") });
}