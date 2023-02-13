package homework1;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private int count;
    private final WeightedQuickUnionUF union0;
    private final WeightedQuickUnionUF union1;
    private final int n;
    private final int top;
    private final int bottom;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be a positive number.");
        }
        grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        union0 = new WeightedQuickUnionUF(n * n + 2);
        union1 = new WeightedQuickUnionUF(n * n + 1);
        count = 0;
        this.n = n;
        top = 0;
        bottom = n * n + 1;
    }

    private int indexOf(int row, int col) {
        return (row - 1) * n + col;
    }

    private void isValid(int row, int col) {
        if (row > n || row < 1 || col > n || col < 1) {
            throw new IllegalArgumentException("The number is out of the border.");
        }
    }

    public void open(int row, int col) {
        isValid(row, col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            count++;
            if (row == 1) {
                union0.union(indexOf(row, col), top);
                union1.union(indexOf(row, col), top);
            }
            if (row == n) {
                union0.union(indexOf(row, col), bottom);
            }
            if (row > 1 && isOpen(row - 1, col)) {
                union0.union(indexOf(row, col), indexOf(row - 1, col));
                union1.union(indexOf(row, col), indexOf(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                union0.union(indexOf(row, col), indexOf(row + 1, col));
                union1.union(indexOf(row, col), indexOf(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                union0.union(indexOf(row, col), indexOf(row, col - 1));
                union1.union(indexOf(row, col), indexOf(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                union0.union(indexOf(row, col), indexOf(row, col + 1));
                union1.union(indexOf(row, col), indexOf(row, col + 1));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        isValid(row, col);
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        isValid(row, col);
        return union1.find(top) == union1.find(indexOf(row, col));
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return union0.find(top) == union0.find(bottom);
    }
}
