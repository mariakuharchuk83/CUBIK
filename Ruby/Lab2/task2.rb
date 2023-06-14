$P = 2
$t = 32
$r = 0

#page 33 
$result = $P**$r*(1 - $P**(-1*$t))

print "result = " + $result.to_f.to_s, "\n"