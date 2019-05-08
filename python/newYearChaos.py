#!/bin/python3

import math
import os
import random
import re
import sys

def merge(q1, q2):
    i1, i2 = 0, 0
    num_inversions = 0
    q = list()

    while i1 < len(q1) and i2 < len(q2):
        if q1[i1] <= q2[i2]:
            q.append(q1[i1])
            i1 += 1
        else:
            num_inversions += (len(q1) - i1)
            q.append(q2[i2])
            i2 += 1

    while i1 < len(q1):
        q.append(q1[i1])
        i1 += 1

    while i2 < len(q2):
        q.append(q2[i2])
        i2 += 1

    return q, num_inversions


def mergeSort(q, lo, hi):
    if lo == hi:
        return [q[lo]], 0
    else:
        mid = (lo + hi) // 2
        q1, n1 = mergeSort(q, lo, mid)
        q2, n2 = mergeSort(q, mid + 1, hi)
        
        q, n = merge(q1, q2)
        return q, n1 + n2 + n

def minimumBribes(q):
    for i0 in range(len(q)):
        if q[i0] - i0 - 1 > 2:
            return 'Too chaotic'
            
    return mergeSort(q, 0, len(q) - 1)[1]

if __name__ == '__main__':
    t = int(input())
    for t_itr in range(t):
        num_inversions = 0
        n = int(input())
        q = list(map(int, input().rstrip().split()))
        
        print(minimumBribes(q))
