 public int CelebrityProblem(int [] people){
        //stack begins with people
        //pop off the stack if they know someone, the last member in the stack should be the celebrity, if empty, then noone is a celebrity
        Stack<Integer> stack = new Stack<Integer>();
        //push onto the stack
        for (int i = 0; i < people.length; i++){
            stack.push(people[i]);
        }
        //
        while (stack.size() > 1) {
            int person = stack.peek();
            boolean found = false;
            int i = 0;
            while (!found && i < people.length){
                if (stack.peek() != people[i] && HasAcquaintance(stack.peek(), people[i])){
                    stack.pop(); //pop because they know someone
                    found = true; //end loop
                }
                i++;
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
            }
        else {
            return 0;
        }
        }
    }
