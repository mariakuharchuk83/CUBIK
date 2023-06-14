class Baggage
  attr_reader :count
  attr_reader :weight
  
  def initialize(count, weight)
    @count, @weight = count, weight
  end
  
  def average_weight
    weight.fdiv count
  end

  def to_s
    "#{@count} item(s), #{@weight} kg"
  end
end

array = [
  Baggage.new(5, 20),
  Baggage.new(8, 38),
  Baggage.new(1, 3),
  Baggage.new(15, 24),
  Baggage.new(9, 8),
  Baggage.new(7, 31)
]

# task a)
count = array.inject(0) { |sum, baggage| sum + baggage.count }
weight = array.inject(0) { |sum, baggage| sum + baggage.weight }
average = weight.fdiv count
b = "nothing"
array.each do |bag|
  if (bag.average_weight - average).abs <= 0.3
    b = bag
    break
  end
end

puts "task a)"
puts "Overall average = #{average}"
puts (b.is_a? Baggage) ? b.to_s : b
puts

# task b)

more_2 = (array.select { |b| b.count > 2 }).length
average = (array.inject(0) { |sum, b| sum + b.count }).fdiv array.length
more_average = (array.select { |b| b.count > average }).length

puts "task b)"
puts "Average count = #{average}"
puts "1) #{more_2}"
puts "2) #{more_average}"
puts

# task c)

b = false
array.length.times do |i|
  bag = array[i]
  is_exit = false
  (i + 1).upto(array.length - 1) do |j|
    if bag.count == array[j].count && (bag.weight - array[j].weight).abs <= 0.5
      is_exit = true
      b = true
      break
    end
  end
  if is_exit
    break
  end
end

puts "task c) #{b}"
puts

# task d)
max = array[0]
array.each do |bag|
  if max.count < bag.count
    max = bag
  end
end
b = true
array.each do |bag|
  if bag.weight > max.weight
    b = false
    break
  end
end

puts "task d) #{b}"
puts

# task e)

b = false
array.each do |bag|
  if bag.count == 1 && bag.weight < 30
    b = true
    break
  end
end

puts "task e) #{b}"