package 알고리즘;

import java.util.Arrays;

public class 유니온파인드 {
    static int[] parent;
    static int N=4;

    public static void main(String[] args) {
        // 부모 노드 배열
        parent = new int[N + 1];

        // 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        union(1,2);
        union(2,3);
        System.out.println(Arrays.toString(parent));
    }

    // find 함수: 루트 노드를 찾고, 경로 압축
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    // union 함수: 두 원소의 집합을 합침
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA; // 한 쪽을 다른 쪽 루트에 붙임
        }
    }
}
