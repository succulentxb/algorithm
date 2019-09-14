from py.tree.rb_node import RBNode


class RBTree(object):
    def __init__(self):
        self.nil = RBNode(color=RBNode.BLACK)
        self.root = self.nil

    def search(self, value) -> RBNode:
        return self.__search(self.root, value)

    def insert(self, value):
        y = self.nil
        x = self.root
        while x != self.nil:
            y = x
            if value < x.value:
                x = x.left
            elif value > x.value:
                x = x.right
            else:
                return x
        z = RBNode(value=value, p=y, left=self.nil, right=self.nil, color=RBNode.RED)
        if y == self.nil:
            self.root = z
        elif value < y.value:
            y.left = z
        else:
            y.right = z
        res = z
        self.__insert_fixup(z)
        return res

    def __search(self, node: RBNode, value) -> RBNode:
        if not node:
            return None

        if node.value > value:
            return self.__search(node.left, value)
        elif node.value < value:
            return self.__search(node.right, value)
        else:
            return node

    def __left_rotate(self, x: RBNode):
        y = x.right
        x.right = y.left
        if y.left != self.nil:
            y.left.p = x
        y.p = x.p
        if x.p == self.nil:
            self.root = y
        elif x == x.p.left:
            x.p.left = y
        else:
            x.p.right = y
        y.left = x
        x.p = y

    def __right_rotate(self, x: RBNode):
        y = x.left
        x.left = y.right
        if y.right != self.nil:
            y.right.p = x
        y.p = x.p
        if x.p == self.nil:
            self.root = y
        elif x == x.p.left:
            x.p.left = y
        else:
            x.p.right = y
        y.right = x
        x.p = y

    def __insert_fixup(self, z: RBNode):
        while z.p.color == RBNode.RED:
            if z.p == z.p.p.left:
                y = z.p.p.right
                if y.color == RBNode.RED:
                    z.p.color = RBNode.BLACK
                    y.color = RBNode.BLACK
                    z.p.p.color = RBNode.RED
                    z = z.p.p
                else:
                    if z == z.p.right:
                        z = z.p
                        self.__left_rotate(z)
                    z.p.color = RBNode.BLACK
                    z.p.p.color = RBNode.RED
                    self.__right_rotate(z.p.p)
            else:
                y = z.p.p.left
                if y.color == RBNode.RED:
                    z.p.color = RBNode.BLACK
                    y.color = RBNode.BLACK
                    z.p.p.color = RBNode.RED
                    z = z.p.p
                else:
                    if z == z.p.left:
                        z = z.p
                        self.__right_rotate(z)
                    z.p.color = RBNode.BLACK
                    z.p.p.color = RBNode.RED
                    self.__left_rotate(z.p.p)
        self.root.color = RBNode.BLACK

    def __transplant(self, u: RBNode, v: RBNode):
        if u.p == self.nil:
            self.root = v
        elif u == u.p.left:
            u.p.left = v
        else:
            u.p.right = v
        v.p = u.p

    def __min(self, x: RBNode):
        while x.left != self.nil:
            x = x.left
        return x

    def delete(self, value):
        z = self.search(value)
        if z == self.nil:
            return
        y = z
        y_origin_color = y.color
        if z.left == self.nil:
            x = z.right
            self.__transplant(z, z.right)
        elif z.right == self.nil:
            x = z.left
            self.__transplant(z, z.left)
        else:
            y = self.__min(z.right)
            y_origin_color = y.color
            x = y.right
            if y.p == z:
                x.p = y
            else:
                self.__transplant(y, y.right)
                y.right = z.right
                y.right.p = y
            self.__transplant(z, y)
            y.left = z.left
            y.left.p = y
            y.color = z.color
        if y_origin_color == RBNode.BLACK:
            self.__delete_fixup(x)

    def __delete_fixup(self, x: RBNode):
        while x != self.root and x.color == RBNode.BLACK:
            if x == x.p.left:
                w = x.p.right
                if w.color == RBNode.RED:
                    w.color = RBNode.BLACK
                    x.p.color = RBNode.RED
                    self.__left_rotate(x.p)
                    w = x.p.right
                if w.left.color == RBNode.BLACK and w.right.color == RBNode.BLACK:
                    w.color = RBNode.RED
                    x = x.p
                else:
                    if w.right.color == RBNode.BLACK:
                        w.left.color = RBNode.BLACK
                        w.color = RBNode.RED
                        self.__right_rotate(w)
                        w = x.p.right
                    w.color = x.p.color
                    x.p.color = RBNode.BLACK
                    w.right.color = RBNode.BLACK
                    self.__left_rotate(x.p)
                    x = self.root
            else:
                w = x.p.left
                if w.color == RBNode.RED:
                    w.color = RBNode.BLACK
                    x.p.color = RBNode.RED
                    self.__right_rotate(x.p)
                    w = x.p.left
                if w.right.color == RBNode.BLACK and w.left.color == RBNode.BLACK:
                    w.color = RBNode.RED
                    x = x.p
                else:
                    if w.left.color == RBNode.BLACK:
                        w.right.color = RBNode.BLACK
                        w.color = RBNode.RED
                        self.__left_rotate(w)
                        w = x.p.right
                    w.color = x.p.color
                    x.p.color = RBNode.BLACK
                    w.left.color = RBNode.BLACK
                    self.__right_rotate(x.p)
                    x = self.root
            x.color = RBNode.BLACK

    def print(self):
        print(self.__print(self.root))

    def __print(self, x: RBNode):
        if x == self.nil:
            return ""
        res = ""
        res += self.__print(x.left)
        res += str(x.value) + " "
        res += self.__print(x.right)
        return res


if __name__ == "__main__":
    tree = RBTree()
    inputs = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    for i in inputs:
        tree.insert(i)
        tree.print()
    tree.delete(5)
    tree.print()
    tree.delete(8)
    tree.print()
