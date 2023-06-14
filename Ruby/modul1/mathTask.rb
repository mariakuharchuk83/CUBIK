#Var 5
require 'test-unit'

class Test1 < Test::Unit::TestCase
    def test_search
      result = f(-6, 2, 2, 1, 2, 3)

      expectedResult = {-6=>2, -4=>2, -2=>2, 0=>0, 2=>6}

      assert_equal( result, expectedResult)

    end
end

def f(start, enD, dx, a, b, c)
    result = {}
    (start..enD).step(dx).each do |x|
      if x < 0 && b != 0
        result[x] = a - x/(10+b)
      elsif x > 0 && b == 0
        result[x] = (x - a) / (x -c)
      else
        result[x] = 3*x + 2/c
      end
    end
    result
end


print f(-6, 2, 2, 1, 2, 3)