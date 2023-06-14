# Lab 1 Variant 5

print "Enter x: \n"
$x = gets.chomp.to_f

print "Enter a:\n"
$a = gets.chomp.to_f

$gamma = 10**(-5)
$fi = 0.61803

$result = Math.exp((Math.sin(Math::PI**2)).abs) * ( Math.log(1+Math.cos($a)**2) + Math.tan(Math.sin($x)) + 4.8*(10**1.3))  /  (5 * Math.sqrt($fi + $gamma))

print "Result = " + $result.to_s, "\n"