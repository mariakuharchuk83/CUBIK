

def funcYif(x)
  if(x>-4 && x<=0)
    return ((x-2).abs / (x**2 * Math.cos(x)))**(1.0/7.0)
  elsif(x>0 && x<=12)
    return 1.0 / (Math.tan(x + Math.exp(-x)) / Math.sin(x)**2)**(1.0/4.5)
  else 
    return 1.0 / ( 1 + x / (1 + x / (1 + x)))
  end
end

def funcYcase(x)
    case x
    when -4...1
      return ((x-2).abs / (x**2 * Math.cos(x)))**(1.0/7.0)
    when 0...13
      return 1.0 / (Math.tan(x + Math.exp(-x)) / Math.sin(x)**2)**(1.0/4.5)
    else
      return 1.0 / ( 1 + x / (1 + x / (1 + x)))
    end
end

puts "if:\ny(-5) = #{funcYif(-5)}"
puts "y(0) = #{funcYif(0)}"
puts "y(12) = #{funcYif(12)}"
puts "y(13) = #{funcYif(13)}"
  
puts "case:\ny(-5) = #{funcYcase(-5)}"
puts "y(0) = #{funcYcase(0)}"
puts "y(12) = #{funcYcase(12)}"
puts "y(13) = #{funcYcase(13)}"
