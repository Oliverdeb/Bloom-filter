class HashFunctions{

  public static long hN(int n, String x){

    switch (n){
      case 0:
      // Standard java hashcode function adapted to work with filter size
        return h1(x);

      case 1:
       // murmurhash java implentation from https://github.com/tnm/murmurhash-java/
        return murmur64(x);

      case 3:
      // FNV1 hash fucntion as described here
      // http://isthe.com/chongo/tech/comp/fnv/
        return fnv1(x);

      case 4:
      // FNV1a hash fucntion as described here
      // http://isthe.com/chongo/tech/comp/fnv/
        return fnv1a(x);
      default:
        break;
    }
    return 0;
  }



  private static long fnv1(String x){
    // FNV1 hash function implemented from pseudo code as described here
    // http://isthe.com/chongo/tech/comp/fnv/
    final byte[] bytes = x.getBytes();

    long hash = 0xcbf29ce484222325L;

    for(byte b : bytes){
      hash = hash * 0x100000001b3L;
      hash = hash ^ b;
    }
    return hash;
  }

  private static long fnv1a(String x){
    // FNV1a hash function implemented from pseudo code as described here
    // http://isthe.com/chongo/tech/comp/fnv/
    final byte[] bytes = x.getBytes();

    long hash = 0xcbf29ce484222325L;

    for(byte b : bytes){
      hash = hash ^ b;
      hash = hash * 0x100000001b3L;
    }
    return hash;
  }

  private static long h1(String x){
    // Standard java hashcode function adapted to work with filter size
    long hash = 0;
    for (int i = 0; i < x.length(); i++) {
      hash = 31 * hash + x.charAt(i);
    }
    return hash ;
  }


 public static long murmur64(final byte[] data, int length) {
   // murmurhash java implentation from https://github.com/tnm/murmurhash-java/
     return murmur64(data, length, 0xe17a1465);
 }

  public static long murmur64(final String text) {
   // murmurhash java implentation from https://github.com/tnm/murmurhash-java/
     final byte[] bytes = text.getBytes();
     return murmur64(bytes, bytes.length);
   }

  public static long murmur64(final byte[] data, int length, int seed) {
    // murmurhash java implentation from https://github.com/tnm/murmurhash-java/

    final long m = 0xc6a4a7935bd1e995L;
    final int r = 47;

    long h = (seed&0xffffffffl)^(length*m);

    int length8 = length/8;

    for (int i=0; i<length8; i++) {
        final int i8 = i*8;
        long k =  ((long)data[i8+0]&0xff)      +(((long)data[i8+1]&0xff)<<8)
                +(((long)data[i8+2]&0xff)<<16) +(((long)data[i8+3]&0xff)<<24)
                +(((long)data[i8+4]&0xff)<<32) +(((long)data[i8+5]&0xff)<<40)
                +(((long)data[i8+6]&0xff)<<48) +(((long)data[i8+7]&0xff)<<56);

        k *= m;
        k ^= k >>> r;
        k *= m;

        h ^= k;
        h *= m;
    }

    switch (length%8) {
    case 7: h ^= (long)(data[(length&~7)+6]&0xff) << 48;
    case 6: h ^= (long)(data[(length&~7)+5]&0xff) << 40;
    case 5: h ^= (long)(data[(length&~7)+4]&0xff) << 32;
    case 4: h ^= (long)(data[(length&~7)+3]&0xff) << 24;
    case 3: h ^= (long)(data[(length&~7)+2]&0xff) << 16;
    case 2: h ^= (long)(data[(length&~7)+1]&0xff) << 8;
    case 1: h ^= (long)(data[length&~7]&0xff);
            h *= m;
    };

    h ^= h >>> r;
    h *= m;
    h ^= h >>> r;

    return h;
  }
}
