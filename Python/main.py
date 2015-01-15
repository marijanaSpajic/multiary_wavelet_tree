import sys
import time
from mwt import *
from calculate import *

# read arguments
multiary = int(sys.argv[1])
input_file_name = sys.argv[2]

# open input file and put it into a string
input_file = open(input_file_name, "r")
input_string = input_file.readline().strip()

if __name__ == "__main__":
    # calculating needed information
    base = get_number_of_layers(multiary)

    # MWT creation
    root = Root()
    dictionary = {}
    for sign in input_string:
        if sign not in dictionary:
            dictionary[sign] = change_base(ord(sign), multiary, base)
        root.put_signs_in_tree(dictionary[sign])

    # show signs and possibilities for user
    signs = list(dictionary.keys())
    signs.sort()
    print ("Type your command.\n")
    print ("Possible symbols:\n")
    print (" ".join(signs) + "\n\n")
    print ("Possible commands:\n")
    print ("1) rank   -> r(position,symbol)     max position = " + str(len(input_string)) + "\n")
    print ("2) select -> s(rank,symbol)\n")
    print ("3) exit   -> e\n")

    # get info from input
    while True:
        command = input("> ")
        while command[0] != "r" and command[0] != "s" and command[0] != "e":
            print("That command does not exist!\n")
            print ("Possible commands:\n")
            print ("1) rank   -> r(position,symbol)\n")
            print ("2) select -> s(rank,symbol)\n")
            print ("3) exit   -> e\n")
            command = input("> ")
        if command[0] == "e":
            sys.exit()
        index = command.index(",")
        index2 = command.index(")")
        position = int(command[2:index])
        symbol = command[index+1:index2]
        if command[0] == "r":
            symbol_list = dictionary[symbol]
            t = time.time()
            result = get_rank(root, position, symbol_list)
            print(time.time() - t)
            print ("rank(" + str(position) + "," + symbol + ") = " + str(result) + "\n")
        else:
            symbol_list = dictionary[symbol]
            t = time.time()
            result = get_select(root, position, symbol_list)
            if result == -1:
                print("select(" + str(position) + "," + symbol + ") cannot be computed " +
                      "because there aren't that many symbols " + symbol + " in input file!\n")
            else:
                print(time.time() - t)
                print ("select(" + str(position) + "," + symbol + ") = " + str(result) + "\n")
            
