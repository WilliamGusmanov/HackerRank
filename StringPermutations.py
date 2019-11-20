
import math 
import sys

def permuteString(n):
  if (n > 0):
    nf = math.factorial(n)
    if (n == 1):
      return 3
    else: return int(1 + 2*(nf/math.factorial(n-1)) + nf/(math.factorial(n-2)) + 0.5*(nf/math.factorial(n - 3) + nf / math.factorial(n - 2)))

size = int(sys.stdin.readline())
for i in range(size):
    n = int(sys.stdin.__next__())
    print(permuteString(n))
