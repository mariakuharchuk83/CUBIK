def prm(a, b, n)
    dx = (b - a) / n
    x = a + dx / 2
    sum = 0
    (1..n).each {
      y = yield(x)
      sum += dx * y
      x += dx
    }
    return sum
  end
  
  def trp(a, b, n)
    dx = (b - a) / n
    x = a + dx
    sum = dx * (yield(a) / 2 - yield(b) / 2)
    loop {
      y = yield(x)
      sum += dx * y
      x += dx
      break if x > b
    }
    return sum
  end
  
  def f1(x)
    return ((x**3)*((1+x)**1.0/2.0))
  end
  
  def f2(x)
    return (1.0 / (x* (Math.log(x)**2)))
  end

  print "first func: \n"
  x = prm(1.0, 8.0, 100000.0) {|x| f1(x)}
  puts x, "\n"
  x = trp(1.0, 8.0, 100000.0) {|x| f1(x)}
  puts x, "\n"
  
  print "second func:\n"
  x = prm(2.0, 2.7, 100000.0) {|x| f2(x)}
  puts x, "\n"
  x = trp(2.0, 2.7, 100000.0) {|x| f2(x)}
  puts x, "\n"