#!/bin/python3

import math
import os
import random
import re
import sys
import heapq

# Complete the minimumMoves function below.
def minimumMoves(grid, startX, startY, goalX, goalY):
    maxX = len(grid)
    maxY = len(grid[0])
    heap = [(0, startX, startY)]
    visited = set()
    
    while heap:
        dist, curX, curY = heapq.heappop(heap)

        if curX == goalX and curY == goalY:
            return dist

        if (curX, curY) not in visited:
            visited.add((curX, curY))

            for nextX in range(curX+1, maxX):
                if grid[nextX][curY] == 'X':
                    break
                heapq.heappush(heap, (dist+1, nextX, curY))

            for nextY in range(curY+1, maxY):
                if grid[curX][nextY] == 'X':
                    break
                heapq.heappush(heap, (dist+1, curX, nextY))
            
            for nextX in range(curX-1, -1, -1):
                if grid[nextX][curY] == 'X':
                    break
                heapq.heappush(heap, (dist+1, nextX, curY))

            for nextY in range(curY-1, -1, -1):
                if grid[curX][nextY] == 'X':
                    break
                heapq.heappush(heap, (dist+1, curX, nextY))

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')
    n = int(input())

    grid = []
    for _ in range(n):
        grid_item = input()
        grid.append(grid_item)

    startXStartY = input().split()
    startX = int(startXStartY[0])
    startY = int(startXStartY[1])
    goalX = int(startXStartY[2])
    goalY = int(startXStartY[3])
    result = minimumMoves(grid, startX, startY, goalX, goalY)

    fptr.write(str(result) + '\n')
    fptr.close()
