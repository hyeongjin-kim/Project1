package edu.postech.csed490h.numbertranslator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A class that translates numbers to words in Korean and vice versa. The number
 * should be converted to the shortest possible word representation. For example,
 * 1000 should be translated to "천" rather than "일천".
 */
public class KoreanNumberTranslator extends AbstractNumberTranslator
        implements NumberTranslator {

    KoreanNumberTranslator() {
        super(Locale.KOREAN);
    }
    private String[] number_name = {"", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
    private String[] unit_name = {"", "만", "억", "조", "경"};
    private String[] digit_name = {"", "십", "백", "천"};
    @Override
    public String toWords(long number) {
        // TODO: implement this method
        if(number == 0) {
            return "영";
        }
        else {

            StringBuffer total_word = new StringBuffer();
            List<String> words_in_4_digit = split_word_in_4_digit(Long.toString(number));

            for(int index = words_in_4_digit.size() - 1; index >=0 ; index--) {
                String current_word = number_to_string(words_in_4_digit.get(index));

                if(!current_word.isEmpty()) {
                    total_word.append(current_word);
                    total_word.append(unit_name[index]);
                }
            }
            return total_word.toString();

        }

    }

    private List<String> split_word_in_4_digit(String word){
        List<String> result = new ArrayList<String>();
        for(int i = word.length(); i > 0; i-=4){
            result.add(word.substring(Math.max(0, i - 4), i));
        }
        return result;
    }

    private String number_to_string(String numbers){
        StringBuffer stringBuffer = new StringBuffer();
        for(int index = 0; index < numbers.length(); index++){
            int current_number = Integer.parseInt(String.valueOf(numbers.charAt(index)));
            if(current_number != 0) {
                if(current_number != 1 || index == numbers.length()-1) {
                    stringBuffer.append(number_name[current_number]);
                }
                stringBuffer.append(digit_name[numbers.length()- 1 - index]);
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public long toNumber(String words) {
        // TODO: implement this method
        long result = 0;
        long current_result = 0;
        long current_number = 0;
        String number_name = "영일이삼사오육칠팔구";
        String unit_name = "십백천만억조경";


        long[] unit_number = {10, 100, 1000, 10000, (long)Math.pow(10,8), (long)Math.pow(10,12), (long)Math.pow(10,16)};
        int word_length = words.length();
        for(int word_count = word_length - 1; word_count >=0; word_count--){
            String current_word = String.valueOf(words.charAt(word_length - word_count - 1));
            int unit_check = number_name.indexOf(current_word);
            if(unit_check ==-1){
                if(unit_name.indexOf(current_word) < 3){
                    current_result += (current_number==0?1:current_number)*unit_number[unit_name.indexOf(current_word)];
                }
                else{
                    current_result += current_number;
                    result += (current_result!=0?current_result:1)*unit_number[unit_name.indexOf(current_word)];
                    current_result = 0;
                }
                current_number = 0;
            }
            else{
                current_number = unit_check;
            }
        }

        return result + current_result + current_number;
    }

    public static void main(String[] args) {
        var input1 = 10000001201L;
        var input2 = "백억천이백일";
        var translator = new KoreanNumberTranslator();

        System.out.println(input1 + " -> " + translator.toWords(input1));
        System.out.println(input2 + " -> " + translator.toNumber(input2));
    }

}
