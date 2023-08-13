#include <iostream>
#include <vector>

using namespace std;

class Node {
public:
    int length;
    char c;
    Node* prev;

    Node(int length, char c, Node *prev) : length(length), c(c), prev(prev) {}
};

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    string n, m;
    cin >> n >> m;

    auto n_len = n.length();
    auto m_len = m.length();

    Node* first = new Node(0, '\0', nullptr);
    vector<vector<Node*>> memo = vector(n_len + 1, vector<Node*>(m_len + 1, first));

    Node* before;
    for (int i = 1; i <= n_len; ++i) {
        for (int j = 1; j <= m_len; ++j) {
            if ( n[i-1] == m[j-1] ) {
                before = memo[i - 1][j - 1];
                memo[i][j] = new Node(before->length + 1, n[i-1], before);
            } else {
                memo[i][j] = max(memo[i-1][j], memo[i][j-1], [](auto a, auto b) {
                    return a->length < b->length;
                });
            }
        }
    }

    Node* tmp = memo[n_len][m_len];
    int len = tmp->length;
    cout << len << '\n';

    if ( len > 0) {
        char* cs = new char[len+1] {0};
        while (tmp->c != '\0') {
            cs[--len] = tmp->c;
            tmp = tmp->prev;
        }
        string answer(cs);
        cout << answer << '\n';
    }
}