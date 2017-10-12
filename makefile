default: compile
	java -cp out main

compile:
	javac -d out main.java BloomFilter.java

clean:
	@rm out/*.class
