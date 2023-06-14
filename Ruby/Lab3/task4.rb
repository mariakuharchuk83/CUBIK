
epsilon = 0.00001

def factorial(n)
   x = n > 1 ? n * factorial(n - 1) : 1
   x.to_f
end

def task1()
  sum = 0.0
  n = 2.0
  loop do
    current = (1.0/(n * (n + 1.0))) ** (n * (n + 1))
    sum += current
    if (current.abs <= 0.00001)
      break
    end
    n += 1
  end

  sum
end
  
def task2()
  n = 1
  sum = 0
  loop do
    sum += 1.0/((2*n-1)*(2*n+1))
    if(sum - 0.5 <= 0.00001)
      break
    end
    n += 1 
  end
  sum
end
  

def task3
  n = 1
  sum = 0
  current = 0
  loop do
    current = (factorial(4*n-1) * factorial(n+1)) / (factorial(3*n)*(3**(2*n))*factorial(2*n))
    sum += current
    if(current.abs<= 0.00001)
      break
    end
    n +=1
  end
  sum
end
  



puts "task1 = #{task1().to_s}\n"
puts "task2 = #{task2().to_s}\n"
puts "task1 = #{task3().to_s}\n"