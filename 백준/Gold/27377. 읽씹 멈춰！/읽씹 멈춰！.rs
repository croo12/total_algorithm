use std::io::stdin;

fn solution() -> usize {

    let mut n : usize = read_line().trim().parse().expect("n 못받음");
    let tokens : Vec<usize> = read_line().split_whitespace().flat_map(|x| x.trim().parse::<usize>()).collect();

    let s = tokens[0];
    let t = tokens[1];

    //일단 무조건 반 쪼갤건데, 먼저 이게 1칸씩 가는 것보다 손해인지 체크하기
    let mut ans : usize = 0;

    while n > 0 {
        if n % 2 == 0 {
            let cost;
            
            if n / 2 * s < t {
                cost = n / 2 * s;
            } else {
                cost = t;
            }

            move_double(&mut n, &mut ans, cost);

        } else {
            move_one(&mut n, &mut ans, s);
        }

    }

    ans
}

fn move_one(n : &mut usize, ans : &mut usize, s : usize) {
    *n -= 1;
    *ans += s;
}

fn move_double(n : &mut usize, ans : &mut usize, t : usize) {
    *n /= 2;
    *ans += t;
}

fn main() {
    //input
    let mut t : usize = read_line().trim().parse().expect("실패했다");

    let mut answer = String::new();

    while t > 0 {
        t -= 1;

        let value = solution();

        answer.push_str(&value.to_string());
        answer.push('\n');
    }

    print!("{}", answer);
}

fn read_line() -> String {
    let mut input = String::new();

    stdin().read_line(&mut input).expect("못 읽었엉");
    input
}