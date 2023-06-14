#Lab 6 Variant 5
class Address 
    attr_reader :city
    attr_reader :street
    attr_reader :house
    attr_reader :flat

    def initialize(city, street, house, flat)
        @city = city
        @street = street
        @house = house
        @flat =flat
    end

    def to_s 
        "#{@city} #{@street} #{@house} #{flat}"
    end
end

class Post 
    attr_reader :address
    attr_reader :receiver
    attr_reader :cost

    def initialize(address, receiver, cost)
        @address = address
        @receiver = receiver
        @cost = cost
    end

    def to_s
        "#{@address.to_s}\nReceiver: #{@receiver}, cost: #{@cost}"
    end
end

#task 1
def task1(array)
    count = 0
    array.each do |post|
        if post.address.city == "Kyiv"
          count += 1
        end
    end
    count
end

#task 2
def task2(array)
    cities = Array.new
    count = 0
    array.each do |post|
        if post.cost >= 10
            cities.push(post.address.city)
            count += 1
        end
    end
    [count, cities]
end

#task 3
def task3(array)
    map = array.group_by{ |post| 
        post.address.to_s 
    }

    map.each do |key, value|
        count = 0
        value.each do |post|
            count += 1
        end
        if count < 2
            map.delete(key)
        end
    end
    map
end

postArray = [
    Post.new(Address.new("Kyiv", "Glushko", 12, 134), "Olha Kimchuk", 50),
    Post.new(Address.new("Kyiv", "Glushko", 12, 134), "Kim Ketral", 50),
    Post.new(Address.new("Lviv", "Central", 3, 45), "Mykola Kats", 124),
    Post.new(Address.new("Kyiv", "Glushko", 12, 122), "Grisha Truink", 9),
    Post.new(Address.new("Odessa", "Derebasovska", 43, 12), "Pan Goida", 3),
    Post.new(Address.new("Odessa", "Derebasovska", 43, 12), "Ron Donald", 23),
    Post.new(Address.new("Odessa", "Derebasovska", 43, 12), "Jim Kerry", 1005),
    Post.new(Address.new("Kharkov", "Konstitution", 1, 1), "Liza Ezizi", 78),
    Post.new(Address.new("Donetsk", "Stadionna", 16, 233), "Olha Kimchuk", 8),
    Post.new(Address.new("Donetsk", "Stadionna", 16, 233), "Mikola Kim", 34),
    Post.new(Address.new("Kyiv", "Sadova", 150, 81), "Mary Jane", 6)
]


puts "1) Post to Kyiv count is #{task1(postArray)}"
costPost, citiestPost = task2(postArray)
print "2) Cost more than ten has #{costPost} boxes in cities: "
citiestPost = citiestPost.uniq
citiestPost.each do |post|
    print post + ", "
end
puts "\n3) Find duplicate addresses"
result = task3(postArray)
result.each do |key, value|
    count = 0
    puts "Address: #{key} \nReceivers: "
    value.each do |post|
        print "#{post.receiver}, "
        count += 1
    end
    puts "\nTotal post amount = #{count}"
end