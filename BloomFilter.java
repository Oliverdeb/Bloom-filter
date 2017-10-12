import java.lang.Math;

class BloomFilter {

  private byte[] bits;
  private int m;
  private int k;

  public BloomFilter(int n, double p){
      this.m = calculateM(n, p);
      bits = new byte[m];
      int kx = calculateK(m, n);

      // only have defined 4 hash functions, therefore only use 4 even if more required
      this.k = kx > 4 ? 4 : kx;
  }

  private int calculateM(int n, double p){
    // calculates M
    double m = (-n*Math.log(p)) / Math.pow(Math.log(2),2);

    return (int)Math.ceil(m);
  }

  private int calculateK(int m, int n){
    // calculates K

    double k = (m/n)*Math.log(2);
    return (int)Math.ceil(k);
  }



  public boolean contains(String x){

    for (int n = 0 ; n < k; n++){
      long h = HashFunctions.hN(n, x);
      int index = (int) (h % m);
      index = index < 0 ? -index : index;
      if ((bits[index] & 1 ) == 0)
        return false;
    }

    return true;
  }


  public void add(String x){
    for (int n = 0 ; n< k; n++){
      long h = HashFunctions.hN(n, x);
      int index = (int) (h % m);
      index = index < 0 ? -index : index;
      bits[index] = 1;
    }
  }


}
