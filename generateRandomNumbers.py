def main():
  inp = int(input())
  answers = []
  while(inp!=0):
    answers.append((grn(inp)))
    inp = int(input())

  for i in answers:
    print(i)




def grn(n):
  m = 4
  numbers = {n}
  noRepeats = True;
  temp = n
  while(noRepeats):
    temp = temp*temp
    temp = int(temp / pow(10,m/2))
    temp = int(temp % pow(10,m))
    
    
    
    if temp in numbers:
      noRepeats = False
    else:
      numbers.add(temp)

  return(len(numbers))
main()
