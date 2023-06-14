PI = 3.141

#c!=x
def y(x,n,c)
    if(c==x) 
        abort("func can't C=X!")
    end
    ((x**(1.0/n) + c**(1.0/n))**2)/((x-c)/(Math.sqrt(1.0/x)+n*Math.sqrt(1.0/c)))
end

#x!=0
def z(x,n,c)
    if(x==0)
        abort("func can't X=0!")
    end
    Math.sin(2*x)**2 - Math.cos(PI/3-2*x) * Math.sin(2*x-PI/6) - (1.0/Math.tan((PI+x)/(x+1)))**(2.0/x)
end

#step = (xLast - xFirst)/Amount 

def task1(n, c)
    step = (n - 1).to_f / (n + c)
    x = 1
    (n + c).times do
      puts "y(#{x}, #{n}, #{c}) = #{y(x, n, c)}"
      x += step
    end
end

def task2(n, c)
    step = (PI - (PI / n)).to_f / ((3 / 2) * n + c)
    x = PI / n
    ((3 / 2) * n + c).times do
      puts "z(#{x}, #{n}, #{c}) = #{z(x, n, c)}"
      x += step
    end
end

def task3(n, c)
    step = (c - 2).to_f / (2 * n)
    x = 2
    (2 * n).times do
      if 2 < x and x < n
        puts "f=y: y(#{x}, #{n}, #{c}) = #{y(x, n, c)}"
      elsif n < x and x < 2 * n
        puts "f=z: z(#{x}, #{n}, #{c}) = #{z(x, n, c)}"
      else
        puts "f=y+z : y(#{x}, #{n}) + z(#{x}, #{n}, #{c}) = #{y(x, n, c) + z(x, n, c)}"
      end
      x += step
    end
end


task1(2,5)
task2(2,5)
task3(2,5)
#task1(2,1)
#puts "z= #{z(0,2,5)}"