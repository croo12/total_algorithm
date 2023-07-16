use std::io;

fn main() {
    let number : u8 = read_line().trim_end().parse().unwrap();
    let mut mod_number = number;
    let mut cycle = 0;

    loop {
        mod_number = calc(mod_number);
        cycle += 1;

        if mod_number == number {
            break;
        }
    }
    
    println!("{}", cycle);
}

fn calc(number: u8) -> u8 {
    let number = number % 10 * 10 + if number < 10 {
        number
    } else {
        (number / 10 + number % 10) % 10
    };
    
    number
}

fn read_line() -> String {
    let mut input = String::new();

    io::stdin().read_line(&mut input).expect("can't read");
    input
}
