
# Пошта. Організувати сортування електронних листів за датою отримання або відправки, 
# зберігання листів по категоріям (вхідні, відправлені, спам, категорії користувача), 
# пошук листів по заголовку, тегами, діапазону дати і часу отримання, відправнику або одержувачу. 
# Передбачити механізм об'єднання листів від одного відправника в список і автоматичної відправки листів в спам.
require 'time'


class MyDate
    attr_reader :mon 
    attr_reader :day
    attr_reader :year
    attr_reader :hour
    attr_reader :minute
  
    def initialize(mon, day, year)
      @mon, @day, @year = mon, day, year
    end
  end

class Letter
    attr_reader :title
    attr_reader :tag
    attr_reader :text

    def initialize(title, tag, text)
        @title = title
        @tag = tag
        @text = text
    end

    def to_s
        "#{title}\n #{tag}\n #{text}\n"
    end
end


class Mail
    attr_reader :sender
    attr_reader :taker
    attr_reader :letter
    attr_reader :date

    def initialize(sender, taker, letter, dateSend)
        @sender = sender
        @taker = taker
        @letter = letter
        @date = date
    end

    def to_s 
        "Sender: #{@sender} \nTaker: #{@taker} \n #{@letter.to_s} date: #{@date} \n"
    end

end


class Taken < Mail
    def initialize(sender, taker, letter, dateSend)
      super(sender, taker, letter, dateSend)
      @type = "taken"
    end
  
    def to_s
      super + "type:#{@type} \n"
    end
end
  
class Sent < Mail
    def initialize(sender, taker, letter, dateSend)
        super(sender, taker, letter, dateSend)
      @type = "sent"
    end
  
    def to_s
      super + "type:#{@type} \n"
    end
end
  
class Spam < Mail
    def initialize(sender, taker, letter, dateSend)
        super(sender, taker, letter, dateSend)
      @type = "spam"
    end

    def to_s
      super + "type:#{@type} \n"
    end
end


def findByTitle(array, needTitle)
    result = []
    array.each do |mail|
        if mail.letter.title == needTitle
            result.push(mail)
        end
    end
    return result
end

def findByTag(array, needTag)
    result = []
    array.each do |mail|
        if mail.letter.tag == needTag
            result.push(mail)
        end
    end
    return result
end


def findByDateDiapos(array, timeStart, timeEnd)
    result = []
    array.each do |mail|
      if mail.date.between?(timeStart,timeEnd)
        result.push(mail)
      end
    end
   return result
end



def main 
    taken = Taken.new("bob@gmail.com", "rony@gmail.com", Letter.new("Job", "free", "We want to hire you!!!"), Time.new(2022, 12, 1, 10, 56))
    send = Sent.new("rony@gmail.com", "bob@gmail.com", Letter.new("Job", "fat", "You fat!!!"), Time.new(2022, 12, 2, 12, 30))
    spam = Spam.new("bob@gmail.com", "rony@gmail.com", Letter.new("Grooming", "free", "Protein!!!"), Time.new(2022, 9, 23, 7, 30))
    array = [taken,send,spam]

    array.each do |mail|
        puts "#{mail.to_s}\n\n\n"
    end

    print "Find by titile JOB:\n"
    findByTitle(array, "Job").each do |mail|
        puts "#{mail.to_s}\n\n"
    end

    print "\n\nFind by tag free:\n"
    findByTag(array, "free").each do |mail|
        puts "#{mail.to_s}\n\n"
    end


    start = Time.new(2022, 9, 23, 7, 30)
    finish = Time.new(2022, 12, 30, 12, 30)
    print "\n\nFind by tag time start: #{start} finish #{finish}\n"
    findByDateDiapos(array, start, finish).each do |mail|
        puts "#{mail.to_s}\n\n"
    end

end


main()