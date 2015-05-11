/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.util.algo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jujuedv
 */
public class EdmondsKarp {

    public int[][] C, F, E;
    public int s, t, f;

    private int[] P, M;

    private static int INF = 1 << 29;

    private int bfs() {
        for (int i = 0; i < E.length; ++i) {
            P[i] = -1;
        }
        P[s] = -2;

        M[s] = INF;

        List<Integer> Q = new LinkedList<Integer>();
        Q.add(s);
        while (!Q.isEmpty()) {
            int u = Q.get(0);
            Q.remove(0);
            for (int v : E[u]) {
                if (C[u][v] - F[u][v] > 0 && P[v] == -1) {
                    P[v] = u;
                    M[v] = Math.min(M[u], C[u][v] - F[u][v]);
                    if (v != t) {
                        Q.add(v);
                    } else {
                        return M[t];
                    }
                }
            }
        }

        return 0;
    }

    public void execute() {
        f = 0;
        F = new int[E.length][E.length];
        P = new int[E.length];
        M = new int[E.length];

        while (true) {
            int m = bfs();
            if (m == 0) {
                break;
            }

            f += m;

            int v = t;
            while (v != s) {
                int u = P[v];
                F[u][v] += m;
                F[v][u] -= m;
                v = u;
            }
        }
    }

    public void calculateAdj() {
        E = new int[C.length][];
        for (int u = 0; u < C.length; ++u) {
            int count = 0;
            for (int v = 0; v < C.length; ++v) {
                if (C[u][v] != 0) {
                    count++;
                }
            }
            
            E[u] = new int[count];
            count = 0;
            
            for (int v = 0; v < C.length; ++v) {
                if (C[u][v] != 0) {
                    E[u][count++] = v;
                }
            }
        }
    }

    public static void main(String args[]) {
        EdmondsKarp ek = new EdmondsKarp();
        ek.E = new int[][]{
            new int[]{1, 2},
            new int[]{2, 3},
            new int[]{1, 3},
            new int[]{}
        };
        ek.C = new int[][]{
            new int[]{0, 100, 100, 0},
            new int[]{0, 0, 1, 100},
            new int[]{0, 1, 0, 100},
            new int[]{0, 0, 0, 0}
        };
        ek.s = 0;
        ek.t = 3;
        ek.execute();

        System.out.println("done");
    }
}
