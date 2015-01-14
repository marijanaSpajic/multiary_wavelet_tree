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
    base = get_number_of_layers(multiary)

    # MWT creation
    root = Root()
    dictionary = {}
    for sign in input_string:
        if sign not in dictionary:
            dictionary[sign] = change_base(ord(sign), multiary, base)
        root.put_signs_in_tree(dictionary[sign])
    import pdb; pdb.set_trace()

    






