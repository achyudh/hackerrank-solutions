#!/bin/python3

import math
import os
import random
import re
import sys

def dfs(graph, visited, source):
    stack = [source]
    num_nodes = 0

    while stack:
        curr_node = stack.pop()
        if not visited[curr_node]:
            visited[curr_node] = True
            for neighbour in graph[curr_node]:
                stack.append(neighbour)
            num_nodes += 1

    return num_nodes

def maxRegion(grid, m, n):
    graph = [list() for x in range(m * n)]
    visited = [False for x in range(m * n)]
    for i0 in range(m):
        for j0 in range(n):
            if grid[i0][j0]:
                curr_node = n * i0 + j0

                if i0 and grid[i0 - 1][j0]:
                    graph[curr_node].append(n * (i0 - 1) + j0)
                
                if i0 and j0 and grid[i0 - 1][j0 - 1]:
                    graph[curr_node].append(n * (i0 - 1) + j0 - 1)

                if j0 and grid[i0][j0 - 1]:
                    graph[curr_node].append(n * i0 + j0 - 1)
                
                if j0 and i0 < m - 1 and grid[i0 + 1][j0 - 1]:
                    graph[curr_node].append(n * (i0 + 1) + j0 - 1)

                if i0 < m - 1 and grid[i0 + 1][j0]:
                    graph[curr_node].append(n * (i0 + 1) + j0)
                
                if i0 < m - 1 and j0 < n - 1 and grid[i0 + 1][j0 + 1]:
                    graph[curr_node].append(n * (i0 + 1) + j0 + 1)

                if j0 < n - 1 and grid[i0][j0 + 1]:
                    graph[curr_node].append(n * i0 + j0 + 1)
                
                if i0 and j0 < n - 1 and grid[i0 - 1][j0 + 1]:
                    graph[curr_node].append(n * (i0 - 1) + j0 + 1)
    
    max_nodes = 0
    for i0 in range(m):
        for j0 in range(n):
            if grid[i0][j0] and not visited[n * i0 + j0]:
                max_nodes = max(max_nodes, dfs(graph, visited, n * i0 + j0))
    return max_nodes

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    m = int(input())
    n = int(input())
    grid = []

    for _ in range(m):
        grid.append(list(map(int, input().rstrip().split())))

    res = maxRegion(grid, m, n)

    fptr.write(str(res) + '\n')
    fptr.close()
