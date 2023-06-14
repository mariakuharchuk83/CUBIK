#Lab 4 Variant 5
require 'matrix'

def sled(matrix, n)
    sum = 0
    (1...n).each { |i|
      sum += matrix[i, i]
    }
    sum
end

def print_matrix(matrix, n)
    (1...n).each { |i|
      (1...n).each { |j|
        print matrix[i, j], " "
      }
      puts
    }
end


n = 8
B = Matrix.build(n, n) do |row, col|
    row = rand(10)
    col = rand(10)
end

print_matrix(B, n)
puts "sled = #{sled(B, n)}"
