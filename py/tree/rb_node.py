class RBNode(object):
    BLACK = 0
    RED = 1

    def __init__(self, value=None, p=None, left=None, right=None, color=BLACK):
        self.p = p
        self.left = left
        self.right = right
        self.value = value
        self.color = color
