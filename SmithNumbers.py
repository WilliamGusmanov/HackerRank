import math

def main():
  answers = []
  testCases = int(input())
  for i in range(testCases):
    number = int(input())
    found = False
    temp = number+1
    while not found:
      digitSum = sumDigits(temp)
      factors = primeFactors(temp)
      #print("factors of : ",temp, "is", factors)
      if len(factors)==1:
        temp = temp + 1
        continue
      else:
          primeSum = sumPrimes(factors)
          if (digitSum==primeSum):
          # print("DigitSUm " , digitSum)
          #  print("prime SUm " ,primeSum)
            found = True
            answers.append(temp)
      temp = temp + 1
  for x in answers:
    print(x)

 
 
def isPrime(num):
  ##print("Entered prime")
  sqrt = (int)(math.sqrt(num))
  for i in range(2,sqrt+1):
    if (num%i==0):
      return False
   

  return True

#returns list of prime factors
def primeFactors(num):
  factors = []
  while num%2==0:
    num = num/2
    factors.append(2)
  for i in range (3,int(math.sqrt(num))+1,2):
    while num%i==0:
      factors.append(i)
      num = num/i
  if num>2:
    factors.append(int(num))
  return factors

#sums digits of prime factors
def sumPrimes(factors):
  sum = 0
  #for every factor
  for number in factors:
    #print("number: " , number)
    #find digits in each factor
    if number > 10:
      temp = number
      #print("temp: ", temp)
      while(temp > 1):
        digit = temp % 10
        sum = sum + digit
        temp = int(temp / 10 )
    else:
      sum = sum + number
  return sum

def sumDigits(number):
  sum = 0
  temp = number
  while(temp > 0):
    digit = temp % 10
    sum = sum + digit
    temp = int(temp / 10 )

  return sum

main()
