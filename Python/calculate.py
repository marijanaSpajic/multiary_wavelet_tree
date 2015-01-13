import math

# returns number of layers/levels in a MWT
def get_number_of_layers(multiplicity):
    return math.ceil(7/math.log(multiplicity, 2))

# create empty lists that represents layers for MWT construction
def create_layers(num_of_layers):
    layers = []
    for i in range(num_of_layers):
        layers.append([]) #or layers.append(list())
    return layers

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

