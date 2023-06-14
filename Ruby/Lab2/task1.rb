# Lab 2 Variant 5

$sum = 0

dots = [[42,51], [76,48], [117,48], [150,42], [204,41],
[228,85], [240,133], [243,193], [272,221], [265,275],
[207,274], [149,271], [103,261], [75,229], [93,172],
[84,149], [48,134], [26,107], [26,86], [27,71], [37,57]]
 
#p 32

for i in 0..dots.size-1 do
  if i == dots.size-1
    $sum += (dots[i][0] + dots[0][0]) * (dots[0][1] - dots[i][1])
  else
    $sum += (dots[i][0]+dots[i+1][0])*(dots[i+1][1]-dots[i][1])
  end
end

$result = $sum.abs/2
print "Result = " + $result.to_s, "\n"