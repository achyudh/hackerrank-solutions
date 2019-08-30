#!/bin/python3
import os
import sys


class TrieNode:
    def __init__(self):
        self.isLastChar = False
        self.childNodes = dict()


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        hasNewChar = False
        currentNode = self.root

        for char in word:
            if currentNode.isLastChar:
                return False

            elif char not in currentNode.childNodes:
                hasNewChar = True
                currentNode.childNodes[char] = TrieNode()

            currentNode = currentNode.childNodes[char]

        currentNode.isLastChar = True
        return hasNewChar or not currentNode.isLastChar


if __name__ == '__main__':
    trieSet = Trie()
    badString = None
    inputStrings = list()

    for _ in range(int(input())):
        inputStrings.append(input())

    for string in inputStrings:
        if not trieSet.insert(string):
            badString = string
            break
    
    if badString:
        print('BAD SET\n%s' % badString)
    else:
        print('GOOD SET')
