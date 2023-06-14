
def var2
    sum = 0
    (0..8).each { |i|
      sum += 1.0 / (3 ** i)
    }
    sum
end

def var4(n)
    sum1 = 0
    sum2 = 0
    (1..n).each { |i|
        sum1 = 0
        (1..i).each{
            |var|
            sum1 += Math.sin(var)
        }
        sum2 += 1.0/sum1
    }
  
    sum2
end
  
  puts "Sum for var2 = #{var2()}\n"
  puts "Sum for var4 = #{var4(4)}"