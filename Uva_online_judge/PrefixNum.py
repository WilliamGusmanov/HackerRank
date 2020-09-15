#implement problem using a trie data structure

class TrieNode(object):
    
    def __init__(self, char: str):
        self.char = char # current value of the node
        self.children = []
        # Is it the last character of the word.`
        self.word_finished = False
  
def add(root, number: str):
    node = root
    for char in number:
        found_in_child = False
        # Search for the character in the children of the present `node`
        if (node.word_finished): #this means the number is a prefix
           return False

        for child in node.children:
            if child.char == char:
                # And point the node to the child that contains this char
                node = child
                found_in_child = True
                break
        # We did not find it so add a new chlid
        if not found_in_child:
            new_node = TrieNode(char)
            node.children.append(new_node)
            # And then point node to the new child
            node = new_node
    # Everything finished. Mark it as the end of a word.
    node.word_finished = True
    if len(node.children) > 0:
      return False
    else:
      return True


#global root that holds all prefix starting nodes

#takes in a list of phone numbers
#returns true if able to complete
#returns false if cannot create the trie

'''
first value from input: (how many times to run program)
  next value is the number of phone numbers

for: number of times to run program
  for: take in the phone numbers

  create the trie 
'''

val = input()
if (int(val) == 0):
  print("YES")
else:
  # iterate the number of times we run the trie
  answers = []
  for i in range(int(val)):
    root = TrieNode('')
    numOfPhoneNumbers = input()
    if (int(numOfPhoneNumbers) == 0):
      print("YES")
      break
    phone_list = []
    for j in range(int(numOfPhoneNumbers)):
      number = input()
      if (len(number) > 10):
        print("NO")
        break
      phone_list.append(number)
    
    count = 0
    if (len(phone_list) > 0):
      for n in phone_list:
        if not add(root, n):
          answers.append("NO")
          break
        count += 1
      if (count == len(phone_list)):
        answers.append("YES")
    else:
      print("YES")

  for a in answers:
    print(a)

