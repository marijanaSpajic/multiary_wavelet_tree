import math
import mwt

# returns number of layers/levels in a MWT
def get_number_of_layers(multiplicity):
    return math.ceil(7/math.log(multiplicity, 2))

# create empty lists that represents layers for MWT construction
def create_layers(num_of_layers):
    layers = []
    for i in range(num_of_layers):
        layers.append([]) #or layers.append(list())
    return layers

# create empty lists that represents nodes where are values stored
def create_node_layers(num_of_layers):
    node_layers = []
    for i in range(num_of_layers - 1):
        node_layers.append([])
    
# function for changing base of a number
# function is also padding a list (fills list with 0s)
def change_base(number, base, num_of_levels):
    number_in_list = []
    (d, m) = divmod(number, base)
    number_in_list.append(m)
    while d > 0:
        (d, m) = divmod(d, base)
        number_in_list.append(m)
    number_in_list = number_in_list[::-1]
    while len(number_in_list) != num_of_levels:
        number_in_list.insert(0, 0)
    return number_in_list

# function that calculates how many times
# does the function rank() has to be called
# returns the answer
def get_rank(root, position, symbol_list):
    private_symbol_list = symbol_list[:]
    node = root
    while len(private_symbol_list) > 0:
        symbol = private_symbol_list.pop(0)
        result = rank(node, position, symbol)
        if len(private_symbol_list) == 0:
            break
        node = node.children[symbol]
        position = result
    return result

# function that iterates through data on a given
# node and returns number of symbols in data
def rank(node, position, symbol):
    counter = 0
    data = node.get_data()
    for i in range(position):
        if data[i] == symbol:
            counter += 1
    return counter
