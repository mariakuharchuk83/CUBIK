
#p33 з бінарного у десяткове

def toDec(value)
    value.reverse.chars.map.with_index do |digit, index|
        digit.to_i * 2**index
    end.sum
end

value = "1111100011101"

puts "#{value} in decimal = #{toDec(value)}"