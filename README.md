# UMass Boston CS310 (Advanced Algorithms)
This is a remote repository for all my CS310 class projects and other associated files. This repository contains my solutions for each of the programming assignments (PAs) and homework assignments. View at your own discretion as answers may not be correct.

## Compiling the Code
In order to compile the code, `cd` to the src folder (or where the root of your source code is) and run the following command. This example compiles code from PA1.

```console
javac -d ../classes pa1/*.java
```

This will compile all Java files in project 1 and put the `.class` files in the `../classes/project` subdirectory.

## Running Programs
In order to run programs, `cd` to the classes subdirectory and run the following command. The Windows command is shown, other OS's will replace `;` to `:`. This also loads in the `algs4` library in order to utilize its objects. The example below runs the Markov Model program.

```console
java -cp .;../lib/algs4.jar pa1.MarkovModel 2 pa1/example.txt
```

Note that the input file being passed into `MarkovModel` is the file's relative path. It won't work if this isn't the case. Another helpful tip: for PA3, the programs utilize classes that allow you to work with binary data. You can test those programs while also printing out the binary output at the same time.

```console
java -cp .;../lib/algs4.jar pa3.MoveToFront - pa3/abra.txt | java -cp .;../lib/algs4.jar edu.princeton.cs.algs4.HexDump 16
```

The above command will print both the output for the program and the binary output of `HexDump`.
