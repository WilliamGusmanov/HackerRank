''' Given an unsorted array of nonnegative integers, find a continuous subarray which adds to a given number. '''
2
def findsubarray(arr, sum):
  tempsum = arr[0]
  l = 0
  r = 1
  while (l < len(arr) and r < len(arr)):
    if (tempsum == sum):
      print("sum found between ",l," and ", r)
      return
    if (tempsum > sum):
      tempsum = tempsum - arr[l]
      l = l + 1
    elif (tempsum < sum):
      tempsum = tempsum + arr[r] #0 + 4
      r = r + 1
    print(tempsum)
  print("no subarray")
