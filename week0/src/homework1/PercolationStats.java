package homework1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] record;
    private final int n;
    private final int trials;
    public PercolationStats(int n,int trials){
        if(n<=0||trials<=0){
            throw new IllegalArgumentException("The number should be positive.");
        }
        this.n=n;
        this.trials=trials;
        record=new double[trials];
        for(int i=0;i<trials;i++){
            Percolation per=new Percolation(n);
            double count = 0;
            while(!per.percolates()){
                per.open(StdRandom.uniformInt(0,n),StdRandom.uniformInt(0,n));
                count = per.numberOfOpenSites();
            }
            record[i]=count/(n*n);
        }
    }
    public double mean(){
        return StdStats.mean(record);
    }
    public double stddev(){
        return StdStats.stddev(record);
    }
    public double confidenceLo(){
        return mean()-(1.96*stddev())/Math.sqrt(trials);
    }
    public double confidenceHi(){
        return mean()+(1.96*stddev())/Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats perStas =new PercolationStats(n,trials);
        System.out.println("mean="+perStas.mean());
        System.out.println("stddev="+perStas.stddev());
        System.out.println("95% confidence interval=["+perStas.confidenceLo()+","+perStas.confidenceHi()+"]");
    }
}
