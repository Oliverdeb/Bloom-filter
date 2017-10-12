import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

class main{
  public static void main(String[] args) throws Exception {
    new main();

  }

  public main() throws Exception{

    Scanner c = new Scanner(new FileReader(new File("google10k.txt")));

    // 10 000 words in file therefore N = 10k, prob of false pos = 1%
    BloomFilter bloom = new BloomFilter(10000, 0.01);

    while(c.hasNext()){
      bloom.add(c.next());
    }

    Scanner input  = new Scanner(System.in);
    System.out.print("Enter a word to check: ");
    String in = input.nextLine();
    while(!in.equalsIgnoreCase("q")){
      if (bloom.contains(in))
        System.out.println("Most probably contains " +in+ "!");
      else
        System.out.println("Does not contain");
      System.out.print("\nEnter a word to check: ");
      in = input.nextLine();
    }

  }

}
