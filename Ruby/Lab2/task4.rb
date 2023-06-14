#p34 з десяткового у бінарне

def toBin(value)
    val = ''

    while value > 0
        val += (value % 2).to_s
        value /= 2
    end
    return val.reverse
end

value = 222
 
puts "#{value} in binary = #{toBin(value)}"


