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
# returns the result of a called
# rank(position, symbol)
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
# node and returns the number of symbol in data
def rank(node, position, symbol):
    counter = 0
    data = node.get_data()
    for i in range(position):
        if data[i] == symbol:
            counter += 1
    return counter

# function that calculates how many times
# does the function select() has to be called
# returns the result of a called
# select(position, symbol)
def get_select(root, rank, symbol_list):
    node = root
    for i in symbol_list[:-1]:
        node = node.children[i]
    reversed_symbol_list = symbol_list[::-1]
    # it could have been normal list with list.pop()
    # OVO TREBA MALO PROMIJENITI
    while len(reversed_symbol_list) > 0 :
        symbol = reversed_symbol_list.pop(0)
        result = select(node, rank, symbol)
        #if result == -1 !
        #if result == -2 !
        if len(reversed_symbol_list) == 0:
            break
        node = node.parent
        rank = result + 1
    #PRIPAZITI NA SLUCAJ KADA U NIZU NEMA DOVOLJNO
    return result
    
# function that iterates through data on a given node
# and returns the
def select(node, rank, symbol):
    counter = 0
    data = node.get_data()
    if rank > len(data):
        return -1
    for i in range(len(data)):
        if data[i] == symbol:
            counter += 1
        if counter == rank:
            return i
    return -2




