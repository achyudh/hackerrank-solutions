#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the minimumSwaps function below.
def minimumSwaps(arr):
    # Create node-swap graph
    edges = {x+1:y for x, y in enumerate(arr)}
    visited = set()
    num_swaps = 0
    for i0 in range(1, n+1):
        next_node = edges[i0]
        visited.add(i0)
        while next_node not in visited:
            visited.add(next_node)
            next_node = edges[next_node]
            num_swaps += 1
    return num_swaps


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')
    n = int(input())
    arr = list(map(int, input().rstrip().split()))
    res = minimumSwaps(arr)
    fptr.write(str(res) + '\n')
    fptr.close()
