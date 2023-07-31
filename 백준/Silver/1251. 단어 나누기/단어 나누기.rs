use std::io::{self, Read};

const MAX_CHAR : char = 'z';
const MAX_STR : &str = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();

    let chars: Vec<char> = buf.trim_end().chars().collect();
    let n = chars.len();

    println!("{}", search(0, 2, &n, &chars));
}

fn search(start : usize, select : usize, n : &usize, chars : &[char]) -> String {
    if select == 0 {
        return (&chars[start..]).iter().rev().collect();
    }

    let mut buf = String::from(MAX_STR);

    let mut rear = 0;
    let mut memo = [0; 48];

    let mut max = MAX_CHAR;
    for i in start..(n - select) {
        if chars[i] == max {
            memo[rear] = i;
            rear += 1;
        } else if chars[i] < max {
            memo[0] = i;
            rear = 1;

            max = chars[i];
        }
    }

    for &i in &memo[0..rear] {
        let str: String = (&chars[start..=i]).iter().rev().collect();
        let tmp = str + &search(i + 1, select - 1, n, chars);
        if std::cmp::Ordering::Greater == buf.cmp(&tmp) {
            buf = tmp;
        }
    }

    buf
}