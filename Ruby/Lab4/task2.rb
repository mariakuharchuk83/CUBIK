
require 'matrix'

def generateMatrix(n, k)
  Matrix.build(n, n) do |row_index, column_index|
    if row_index == column_index
      2
    else
      k + 2
    end
  end
end

def getVector(n)
  Vector[Array.new(n) { |i| i + 1 }]
end

def print_matrix(matrix, n)
  (0...n+1).each { |i|
    (0...n+1).each { |j|
      print matrix[i, j], " "
    }
    puts
  }
end

def Gauss(matrix, b, n)
  print("Matrix A:\n")
  print_matrix(matrix, n)
  print("\nVector B:\n")
  print("#{b[0]}\n")
  print("\n")

  current_matrix = Matrix.hstack(matrix, Matrix.column_vector(b[0]))

  (0...n).each { |k|
    (k + 1..n).each { |j|
      current_matrix[k,j] = current_matrix[k,j].to_f / current_matrix[k,k]
    }
    current_matrix[k,k] = 1

    (k + 1...n).each { |i|
      (k + 1..n).each { |j|
        current_matrix[i,j] = current_matrix[i,j].to_f - (current_matrix[i,k] * current_matrix[k,j])
      }
      current_matrix[i,k] = 0
    }
  }

  print "Our matrix:\n"
  print_matrix(current_matrix, n)

  x = Matrix.zero(1,n)

  (0..(n-1)).reverse_each { |i|
    sum = 0
    (i + 1...n).each { |j|
      sum += current_matrix[i,j].to_f * x[0, j]
    }
    x[0, i] = current_matrix[i,n].to_f - sum
  }

  print "Solution:"
  (0...n).each{ |i|
    printf("\nx = #{x[0,i]}\n")
  }
end


n = 4
matrix = generateMatrix(n, 5)
vector = getVector(n)

Gauss(matrix, vector, n)
