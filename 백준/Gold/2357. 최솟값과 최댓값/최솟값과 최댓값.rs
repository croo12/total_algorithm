use std::io::{self, Read};
struct Tree {
    start: usize,
    end: usize,
    value: (i32, i32),
    left_tree: Option<Box<Tree>>,
    right_tree: Option<Box<Tree>>,
}

impl Tree {
    fn new(main: &[i32], start: usize, end: usize) -> Tree {
        if start == end {
            return Tree {
                start,
                end,
                value: (main[start], main[start]),
                left_tree: None,
                right_tree: None,
            };
        }

        let mid = (start + end) / 2;

        let left_tree = Box::new(Tree::new(main, start, mid));
        let right_tree = Box::new(Tree::new(main, mid + 1, end));

        let value: (i32, i32) = (
            left_tree.value.0.max(right_tree.value.0),
            left_tree.value.1.min(right_tree.value.1),
        );

        Tree {
            start,
            end,
            value,
            left_tree: Some(left_tree),
            right_tree: Some(right_tree),
        }
    }

    fn find(&self, start: usize, end: usize) -> (i32, i32) {
        if start == self.start && end == self.end {
            return self.value;
        }

        let mut t = (i32::MIN, i32::MAX);

        let left = self.left_tree.as_ref().unwrap();
        let right = self.right_tree.as_ref().unwrap();

        if start <= left.end {
            let tmp = left.find(start, end.min(left.end));

            t.0 = tmp.0.max(t.0);
            t.1 = tmp.1.min(t.1);
        }

        if right.start <= end {
            let tmp = right.find(start.max(right.start), end);

            t.0 = tmp.0.max(t.0);
            t.1 = tmp.1.min(t.1);
        }

        t
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut tokens = buf.split_ascii_whitespace();

    let n = tokens.next().unwrap().parse().unwrap();
    let m = tokens.next().unwrap().parse().unwrap();

    let numbers: Vec<i32> = tokens
        .by_ref()
        .take(n)
        .map(|s| s.parse().unwrap())
        .collect();

    let segment = Tree::new(&numbers, 0, n - 1);

    let mut answer = String::new();
    for _ in 0..m {
        let start: usize = tokens.next().unwrap().parse().unwrap();
        let end: usize = tokens.next().unwrap().parse().unwrap();

        let t = segment.find(start - 1, end - 1);

        answer.push_str(&format!("{} {}\n", t.1, t.0));
    }
    print!("{}", answer);
}