Code compiled and tested on biolinux (x64).

Instalation notes:

Download all files in a local directory. You will need the essential package which you can install by
typing   sudo apt-get install build-essential   in your terminal.

To compile, position yourself in the appropriate folder and type in terminal: g++ -Wno-cpp mwt.cpp -o MWT
To run type: ./MWT filename arity_number(between 2 and 10)

Example:

./MWT input.txt 10

> Input: 500040 Generated in: 2604 seconds.
> Enter command (rank/select/exit): rank
> Enter position: 250000
> Enter symbol: A
> Result: 60948

> Enter command(rank/select/exit): exit