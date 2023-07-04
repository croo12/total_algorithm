use std::io::stdin;

fn main() {
    let n_m : Vec<usize> = parse_vec(read_line());

    let mut map_vector : Vec<Vec<char>> = vec![vec!['\0'; n_m[1]]; n_m[0]];

    for row in 0..n_m[0] {
        let input = read_line();
        let mut tmp = input.trim_end().chars();

        for col in 0..n_m[1] {
            map_vector[row][col] = tmp.next().unwrap();
        }
    }

    let max = n_m[0].min(n_m[1]);
    let mut answer = 0;
    for length in (1..max+1).rev() {
        if check_square(length, &mut map_vector, n_m[0], n_m[1]) {
            answer = length * length;
            break;
        }
    }

    println!("{}", answer);
}

fn check_square(length : usize, map_vector : &mut Vec<Vec<char>>, row : usize, col : usize ) -> bool {

    for r in 0..(row-length+1) {
        for c in 0..(col-length+1) {
            if  map_vector[r][c] == map_vector[r + length - 1][c] && 
                map_vector[r][c] == map_vector[r][c + length - 1] && 
                map_vector[r][c] == map_vector[r + length - 1][c + length - 1] {

                return true;
            }
        }
    }

    false
}

fn parse_vec<T : std::str::FromStr> (line : String ) -> Vec<T> 
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
