use std::io::stdin;

const END_CONDITION: [[usize;3]; 8] = [[0,1,2], [3,4,5], [6,7,8], [0,3,6], [1,4,7], [2,5,8], [0,4,8], [2,4,6]];

fn main() {
    let mut boards: Vec<String> = Vec::new();

    loop {
        let board = read_line();

        if board.trim() == "end" {
            break;
        }

        boards.push(board);
    }

    let mut answer = String::new();

    for board in boards {
        if check(&mut board.chars().collect()) {
            answer.push_str("valid\n");
        } else {
            answer.push_str("invalid\n");
        }
    }

    print!("{}", answer);
}

fn check(board: &mut Vec<char>) -> bool {
    let mut x_cnt = 0;
    let mut o_cnt = 0;

    for c in board.into_iter() {
        if *c == 'X' {
            x_cnt += 1;
        } else if *c == 'O' {
            o_cnt += 1;
        }
    }

    if x_cnt == o_cnt {
        return is_end(board, 'O', false);
    } else if x_cnt == o_cnt + 1 {
        return is_end(board, 'X', x_cnt + o_cnt == 9);
    }

    return false;
}

fn is_end(board: &mut Vec<char>, answer : char, is_full:bool) -> bool {
    let answer_is_o = answer == 'O';

    let must_win = is_win(&board, answer_is_o);
    let never_win = is_win(&board, !answer_is_o);
    if never_win {
        return false;
    } else if must_win {
        for i in 0..9 {
            let mut flag = false;

            if board[i] == answer {
                board[i] = '.';

                if !is_win(&board, answer_is_o) {
                    flag = true;
                }

                board[i] = answer;
            }

            if flag {
                return true;
            }
        }
        return false;
    } else {
        return is_full;
    }
}

fn is_win(now : &Vec<char>, is_o : bool) -> bool {
    let c;
    if is_o {
        c = 'O';
    } else {
        c = 'X';
    }

    for is in END_CONDITION {
        let mut flag = true;
        for i in is {
            if now[i] != c {
                flag = false;
                break;
            }
        }

        if flag {
            return true;
        }
    }

    return false;
}

fn parse_vec<T: std::str::FromStr>(line: String) -> Vec<T>
where
    <T as std::str::FromStr>::Err: std::fmt::Debug,
{
    let list: Vec<T> = line
        .split_whitespace()
        .map(|w| w.parse::<T>().unwrap())
        .collect();

    list
}

fn read_line() -> String {
    let mut input = String::new();

    stdin().read_line(&mut input).expect("can't read");
    input
}