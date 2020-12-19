run: compile
	java Interface

compile:
	javac GraphADT.java
	javac CS400Graph.java
	javac Location.java
	javac UWMap.java
	javac Interface.java
	javac -cp .:junit5.jar TestUWMap.java

test:
	java -jar junit5.jar --class-path . --scan-classpath --details tree

clean:
	$(RM) *.class
