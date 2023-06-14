#Var 5
require 'test-unit'

class Test2 < Test::Unit::TestCase

    def test_1
        
        bookArray = [
            Book.new(0, "Tom Soer1", [Author.new("Mark", "Tven")], "big", 1857, 191, 2, "Soft"),
            Book.new(1, "Tom Soer2", [Author.new("Mark", "Tven"), Author.new("Joe", "Tven")], "AST", 1876, 191, 2, "Soft"),
            Book.new(2, "Tom Soer3", [Author.new("joe", "Tven")], "AST", 1903, 191, 2, "Soft"),
        ]
        authorGiven = [Author.new("Mark", "Tven")]
        result = task1(bookArray, authorGiven)
        expected = [Book.new(0, "Tom Soer1", [Author.new("Mark", "Tven")], "big", 1857, 191, 2, "Soft")]
        assert_equal(result, expected)
    end

    def test_2
        bookArray = [
            Book.new(0, "Tom Soer1", [Author.new("Mark", "Tven")], "big", 1857, 191, 2, "Soft"),
            Book.new(1, "Tom Soer2", [Author.new("Mark", "Tven"), Author.new("Joe", "Tven")], "AST", 1876, 191, 2, "Soft"),
            Book.new(2, "Tom Soer3", [Author.new("joe", "Tven")], "AST", 1903, 191, 2, "Soft"),
        ]
        publishingOfficeGiven = "AST"
        result = task2(bookArray, publishingOfficeGiven)
        expected = [
            Book.new(1, "Tom Soer2", [Author.new("Mark", "Tven"), Author.new("Joe", "Tven")], "AST", 1876, 191, 2, "Soft"),
            Book.new(2, "Tom Soer3", [Author.new("joe", "Tven")], "AST", 1903, 191, 2, "Soft"),
        ]
        assert_equal(result, expected)
    end

    def test_3
        bookArray = [
            Book.new(0, "Tom Soer1", [Author.new("Mark", "Tven")], "big", 1857, 191, 2, "Soft"),
            Book.new(1, "Tom Soer2", [Author.new("Mark", "Tven"), Author.new("Joe", "Tven")], "AST", 1876, 191, 2, "Soft"),
            Book.new(2, "Tom Soer3", [Author.new("joe", "Tven")], "AST", 1903, 191, 2, "Soft"),
        ]
        publishingOfficeGiven = "AST"
        yearGiven = 1868
        result = task3(bookArray, yearGiven)
        expected = [
            Book.new(1, "Tom Soer2", [Author.new("Mark", "Tven"), Author.new("Joe", "Tven")], "AST", 1876, 191, 2, "Soft"),
            Book.new(2, "Tom Soer3", [Author.new("joe", "Tven")], "AST", 1903, 191, 2, "Soft"),
        ]
        assert_equal(result, expected)
    end
end


class Author 
    attr_accessor :firstName
    attr_accessor :lastName

    def initialize(firstName,lastName)
        @firstName = firstName
        @lastName = lastName
    end

    def to_s
        "#{@firstName} #{@lastName}"
    end
end



class Book
    attr_reader :id
    attr_accessor :name
    attr_accessor :author
    attr_accessor :publishingOffice
    attr_accessor :year
    attr_accessor :pagesAmount
    attr_accessor :price
    attr_accessor :bindingType

    def initialize(id, name, author, publishingOffice, year, pagesAmount, price, bindingType)
        @id = id
        @name = name
        @author = author
        @publishingOffice = publishingOffice
        @year = year
        @pagesAmount = pagesAmount
        @price = price
        @bindingType = bindingType
    end

    def to_s
        "[#{@id}]\t#{@name}\t#{printAuthors(@author)}\t\t#{@publishingOffice}\t\t#{@year}\t#{@pagesAmount}\t\t#{@price}\t#{@bindingType}"
    end

end

#Task1: list of books with given author
def task1(bookArray, author)
    bookArray.select{ |book|
        printAuthors(author) == printAuthors(book.author)
    }
end

#Task2: list of books with publishing office
def task2(bookArray, publishingOffice)
    bookArray.select { |book|
        book.publishingOffice == publishingOffice
    }
end

#Task3: list of books that were published after #{yearGiven} year 
def task3(bookArray, year)
    bookArray.select { |book|
        book.year > year
    }
end


def printAuthors(author)
    a = ""
    author.each do |per|
        a = a + " " + per.to_s
    end
    a
end

bookArray = [
    Book.new(0, "Tom Soer1", [Author.new("Mark", "Tven")], "big", 1857, 191, 2, "Soft"),
    Book.new(1, "Tom Soer2", [Author.new("Mark", "Tven"), Author.new("Joe", "Tven")], "AST", 1876, 191, 2, "Soft"),
    Book.new(2, "Tom Soer3", [Author.new("joe", "Tven")], "AST", 1903, 191, 2, "Soft"),
]

puts "Book\tName\t\tAuthors\t\tPublishing Office\tYear\tPages Amount\tPrice\tBinding Type"
puts bookArray
# bookArray.each do |book|
#     puts book.to_s
# end

authorGiven = [Author.new("Mark", "Tven")]
puts "\n\n#Task1: list of books with given author #{printAuthors(authorGiven)} "
puts task1(bookArray, authorGiven)

publishingOfficeGiven = "AST"
puts "\n\n#Task2: list of books with given publishing Office #{publishingOfficeGiven} "
puts task2(bookArray, publishingOfficeGiven)


yearGiven = 1868
puts "\n\n#Task3: list of books that were published after #{yearGiven} year "
puts task3(bookArray, yearGiven)