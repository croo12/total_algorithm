use std::io::{self, Read};

const CACHE: [i32; 40] = [1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141];
fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut tokens = buf.split_ascii_whitespace().map(|s| s.parse::<usize>().unwrap());
    let n = tokens.next().unwrap();
    let m = tokens.next().unwrap();
    let ms:Vec<usize> = tokens.take(m).collect();

    let mut answer = 1;
    let mut before = 1;
    for i in &ms {
        let num = *i - before;
        if num > 0 {
            answer *= CACHE[num - 1];
        }
        before = i + 1;
    }
    if before < n {
        answer *= CACHE[n - before];
    }

    println!("{}", answer);
}