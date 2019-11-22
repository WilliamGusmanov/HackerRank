#all subsets of a problem
set = [1,3,5,7]

def printsubsets(array):
  for i in range(2**len(array)):
    bits = bin(i)
    bits = bits[2:]
    list = []
    k = 0
    for j in range(len(bits)-1,-1,-1):
        if (bits[j] == "1"):
          list.append(array[k])
        k = k + 1
    print(list)
printsubsets(set)
