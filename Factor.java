import java.util.ArrayList;
public class Factor {
  public static boolean isPrime(int x){
    for(int i=2;i<x;i++){
      if(x%i==0)return false;
    }
    return true;
  }
  public static boolean factored(ArrayList<Integer> fac){
    for(int f : fac) if(!isPrime(f)) return false;
    return true;
  }
  public static int[] factors(int x){
    int[] out = new int[2];
    for(int i=2;i<x;i++){
      if(x % i == 0) {
        out[0] = i;
        out[1] = x/i;
      }
    }
    return out;
  }
  public static ArrayList<Integer> recursiveFactor(ArrayList<Integer> fac){
    if(factored(fac)) return fac;
    for(int i: fac){
      if(!isPrime(i)) {
        int[] f = factors(fac.get(i));
        fac.remove(i);
        fac.add(f[0]);
        fac.add(f[1]);
      }
    }
    return recursiveFactor(fac);
  }
  public static ArrayList<Integer> primeFactorization(int x){
    ArrayList<Integer> fac = new ArrayList<Integer>();
    fac.add(x);
    if(isPrime(x)) return fac; //already factored!
    while(!factored(fac)){
      for(int i=2;i<x;i++){
        if(fac.get(fac.size()-1) % i == 0) {
          int y = fac.remove(fac.size()-1);
          fac.add(i);
          fac.add(y/i);
        }
      }
    }
    for(int i=0;i<fac.size();i++){
      if(fac.get(i) == 1){
        fac.remove(i);
        --i;
      }
    }
    return fac;
  }
  public static void main(String[] args) {
    if(args.length == 0){
      System.out.println("What number should i factor, call me like \"java Factor NUMBER\"");
      System.exit(0);
    }
    System.out.println("factoring " + args[0]);
    ArrayList<Integer> fac = new ArrayList<Integer>();
    fac.add(Integer.parseInt(args[0]));
    System.out.println(recursiveFactor(fac));
  }
}
