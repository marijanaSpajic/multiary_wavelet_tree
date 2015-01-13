import sys
from mwt import *
from calculate import *

multiary = sys.argv[1]
input_file_name = sys.argv[2]

input_file = (input_file_name, "r")
input_string = input_file.readline().strip()

if __name__ == "__main__":
    num_of_layers = get_number_of_layers(multiary)
    layers = create_layers(num_of_layers)
