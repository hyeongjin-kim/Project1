package edu.postech.csed490h.numbertranslator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * A class that translates numbers to words in English and vice versa. Each word
 * is separated by a space. For example, 1234567890 is translated to "one billion
 * two hundred thirty-four million five hundred sixty-seven thousand eight hundred
 * ninety" and vice versa.
 */
public class EnglishNumberTranslator extends AbstractNumberTranslator
        implements NumberTranslator {

    EnglishNumberTranslator() {
        super(Locale.ENGLISH);
    }

    private String[] number1to19 = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve","thirteen","fourteen",
            "fifteen","sixteen","seventeen","eighteen","nineteen"};
    private String[] tens = {"", "twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety", "hundred"};
    private String[] unit_name = {"","thousand","million","billion","trillion"};


    @Override
    public String toWords(long number) {
        // TODO: implement this method
        if(number == 0) {
            return "zero";
        }
        else if(number < 20L){
            return number1to19[(int)number];
        }
        else {
            StringBuffer total_word = new StringBuffer();
            List<String> words_in_3_digit = split_word_in_3_digit(Long.toString(number));

            for(int index = words_in_3_digit.size() - 1; index >=0 ; index--) {
                String current_word = number_to_string(words_in_3_digit.get(index));
                if(!current_word.isEmpty()) {
                    total_word.append(current_word);
                    if(index != 0) {
                        total_word.append(" ");
                    }
                    total_word.append(unit_name[index]);
                    if(index != 0) {
                        total_word.append(" ");
                    }
                }
            }
            return total_word.toString();

        }
    }
    private List<String> split_word_in_3_digit(String word){
        List<String> result = new ArrayList<String>();
        for(int i = word.length(); i > 0; i-=3){
            result.add(word.substring(Math.max(0, i - 3), i));
        }
        return result;
    }

    private String number_to_string(String numbers){
        StringBuffer result = new StringBuffer();
        int current_number = Integer.parseInt(numbers);
        if(current_number > 99){
            result.append(number1to19[(int)current_number/100]);
            result.append(" hundred");
        }
        current_number%=100;
        if(current_number > 0 && current_number <20){
            if(!result.isEmpty()){
                result.append(" ");
            }
            result.append(number1to19[(int)current_number]);
        }
        else if (current_number % 10 == 0 && current_number !=0) {
            if(!result.isEmpty()){
                result.append(" ");
            }
            result.append(tens[(int)current_number/10 - 1]);
        }
        else if (current_number > 20 && current_number < 100) {
            if(!result.isEmpty()){
                result.append(" ");
            }
            result.append(tens[(int)current_number/10 - 1]);
            result.append("-");
            result.append(number1to19[current_number%10]);
        }

        return result.toString();
    }
    @Override
    public long toNumber(String words) {
        // TODO: implement this method
        long total_result = 0;
        long current_result = 0;
        words = words.replace("-", " ");
        String[] splited_words = words.split(" ");
        List<String> numberlist = Arrays.asList(number1to19);
        List<String> tenslist = Arrays.asList((tens));
        List<String> unitlist = Arrays.asList(unit_name);
        Long[] unitnumber = {0L, 1000L,1000000L,1000000000L,1000000000000L};

        for(int index = 0; index < splited_words.length; index++){
            if(numberlist.contains(splited_words[index])){
                current_result += numberlist.indexOf(splited_words[index]);
            }
            else if (tenslist.contains(splited_words[index])) {
                if(tenslist.indexOf(splited_words[index]) == 9){
                    current_result *=100;
                }
                else{
                    current_result += (tenslist.indexOf(splited_words[index])+1)*10;
                }
            }

            else if (unitlist.contains(splited_words[index])){
                current_result *= unitnumber[unitlist.indexOf(splited_words[index])];
                total_result += current_result;
                current_result = 0;
            }
        }
        return total_result + current_result;
    }

    public static void main(String[] args) {
        var input1 = 10000001201L;
        var input2 = "ten billion one thousand two hundred one";
        var translator = new EnglishNumberTranslator();

        System.out.println(input1 + " -> " + translator.toWords(input1));
        System.out.println(input2 + " -> " + translator.toNumber(input2));
    }
}
