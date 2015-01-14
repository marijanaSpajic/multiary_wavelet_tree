import sys
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
    num_of_layers = base = get_number_of_layers(multiary)
    layers = create_layers(num_of_layers)

    # filling the layers, preparing data for MWT creation
    dictionary = {}
    for sign in input_string:
        if sign in dictionary:
            counter = 0
            for s in dictionary[sign]:
                layers[counter].append(s)
                counter += 1
        else:
            dictionary[sign] = change_base(ord(sign), multiary, base)
            counter = 0
            for s in dictionary[sign]:
                layers[counter].append(s)
                counter += 1

    # MWT creation

