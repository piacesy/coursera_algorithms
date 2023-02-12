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

    public Percolation(int n){
        if(n <= 0){
            throw new IllegalArgumentException("n should be a positive number.");
        }
        grid = new boolean[n][n];
        union0 = new WeightedQuickUnionUF(n*n+2);
        union1 = new WeightedQuickUnionUF(n*n+2);
        count = 0;
        this.n = n;
        top = 0;
        bottom = n*n+1;
    }
    private int indexOf(int row,int col){
        return row*n+col+1;
    }
    private void isValid(int row,int col){
        if(row>=n||row<0||col>=n||col<0){
            throw new IllegalArgumentException("The number is out of the border.");
        }
    }
    public void open(int row,int col){
        isValid(row,col);
        if(!isOpen(row,col)){
            grid[row][col]=true;
            count++;
        }
    }
    public boolean isOpen(int row,int col){
        isValid(row,col);
        return grid[row][col];
    }
    public boolean isFull(int row,int col){
        isValid(row,col);
        return union1.find(top)==union1.find(indexOf(row,col));
    }
    public int numberOfOpenSites(){
        return count;
    }
    public boolean percolates(){
        return union0.find(top)==union0.find(bottom);
    }
}
