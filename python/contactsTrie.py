#!/bin/python3
import os
import sys

class TrieNode:
    def __init__(self):
        self.count = 0
        self.childNodes = dict()

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        currentNode = self.root
        for char in word:
            currentNode.count += 1
            if char not in currentNode.childNodes:
                currentNode.childNodes[char] = TrieNode()
            currentNode = currentNode.childNodes[char]
        currentNode.count += 1

    def getCount(self, word):
        currentNode = self.root
        for char in word:
            if char not in currentNode.childNodes:
                return 0
            currentNode = currentNode.childNodes[char]
        return currentNode.count

def processContacts(queries):
    contacts = Trie()
    counts = list()

    for query in queries:
        if query[0] == 'add':
            contacts.insert(query[1])
        else:
            counts.append(contacts.getCount(query[1]))
    
    return counts
