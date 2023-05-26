use std::io::stdin;

const ZERO_DATE : usize = 1999*12;

fn main() {
    let n : i32 = input().parse().expect("not valid number Failed");

    //2000.01 ~ 9999.12
    let mut calendar = vec![0; 9999*12 - ZERO_DATE + 1];

    for _ in 0..n {
        //start-date end-date
        let input = input();
        let mut flag = false;

        input.split_whitespace().for_each(|x| {
            let mut tokens = x.split('-');

            let year = tokens.next().unwrap().parse::<i32>().expect("Fail get start");
            let month = tokens.next().unwrap().parse::<i32>().expect("Fail get end");

            calendar[calc_index(&year, &month, &mut flag)] += is_end(&flag);
        });
    }

    let mut ans = -1;

    let mut cnt = 0;
    let mut max = 0;
    let mut sum = 0;

    for i in calendar {

        sum += i;

        if sum > max {
            max = sum;
            ans = cnt;
        }

        cnt += 1;
    }

    if ans%12 < 9 {
        println!("{}-0{}", ans/12 + 2000, ans%12 + 1);
    } else {
        println!("{}-{}", ans/12 + 2000, ans%12 + 1);
    }
}

fn calc_index(year : &i32, month : &i32, flag : &mut bool) -> usize {

    let idx = ((year - 1) * 12 + (month - 1)) as usize - ZERO_DATE;

    if *flag {
        idx + 1
    } else {
        *flag = true;
        idx
    }
}

fn is_end(flag : &bool) -> i32 {
    if *flag {
        -1
    } else {
        1
    }
}

fn input() -> String {
    let mut input = String::new();
    stdin().read_line(&mut input).expect("Failed Input");

    input.trim().to_string()
}