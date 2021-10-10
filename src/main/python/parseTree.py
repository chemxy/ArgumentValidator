# from pythonds.basic import Stack
from binaryTree import BinaryTree
from collections import deque

"""
reference: https://runestone.academy/runestone/books/published/pythonds/Trees/ParseTree.html
"""


def buildParseTree(fpexp):
    fplist = fpexp.split()
    pStack = deque()
    eTree = BinaryTree('')
    pStack.append(eTree)
    currentTree = eTree

    for i in fplist:
        if i == '(':
            currentTree.insertLeft('')
            pStack.append(currentTree)
            currentTree = currentTree.getLeftChild()

        elif i in ['+', '-', '*', '/']:
            currentTree.setRootVal(i)
            currentTree.insertRight('')
            pStack.append(currentTree)
            currentTree = currentTree.getRightChild()

        elif i == ')':
            currentTree = pStack.pop()

        elif i not in ['+', '-', '*', '/', ')']:
            try:
                currentTree.setRootVal(int(i))
                parent = pStack.pop()
                currentTree = parent

            except ValueError:
                raise ValueError("token '{}' is not a valid integer".format(i))

    return eTree

pt = buildParseTree("( ( 10 + 5 ) * 3 )")
pt.preorder()  #defined and explained in the next section
