import uuid

class Root(object):
    def __init__(self):
        self.data = []
        self.children = {}
        self.list_of_signs = []
        self.index = 0

    def put_signs_in_tree(self, list_of_signs):
        self.list_of_signs = list_of_signs[:]
        self.index = self.list_of_signs.pop(0)
        self.data.append(self.index)
        if self.index not in self.children:
            self.create_child(self.index)
        self.children[self.index].put_data(self.list_of_signs)

    def create_child(self, index):
        self.children[index] = Node(self)

    def get_data(self):
        return self.data

class Node(Root):
    def __init__(self, parent_obj):
        self.data = []
        self.children = {}
        self.list_of_signs = []
        self.parent_obj = parent_obj
    
    def put_data(self, list_of_remaining_signs):
        self.list_of_signs = list_of_remaining_signs[:]
        self.index = self.list_of_signs.pop(0)
        #import pdb; pdb.set_trace()
        self.data.append(self.index)
        if len(self.list_of_signs) > 0:
            if self.index not in self.children:
                self.create_child(self.index)
            self.children[self.index].put_data(self.list_of_signs)
