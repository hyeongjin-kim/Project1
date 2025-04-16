package edu.postech.csed490h;

/**
 * A number translator converts a number to words in Korean and vice versa.
 */
public class KoreanNumberTranslator {
    //TODO: feel free to add any private methods (to avoid duplicate code)

    /**
     * Convert a number to words in Korean. The number should be in the range
     * [0, Long.MAX_VALUE], and otherwise an exception should be thrown. The
     * number should be converted to the shortest possible string of words.
     * For example, 1000 should be converted to 천, not 일천. Also, the number
     * 0 should be converted to 영, not 공.
     *
     * @param number a number
     * @return a string of words in Korean
     * @throws IllegalArgumentException if the number is not in the range
     **/

    String toWords(long number) {
        // TODO: implement this
        if(number == 0) {
            return "영";
        }
        else {
            String[] number_name = {"", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
            String[] unit_name = {"", "만", "억", "조", "경"};
            String[] digit_name = {"", "십", "백", "천"};
            int number_count = 0;

            StringBuffer result = new StringBuffer("");

            String string_number = Long.toString(number);
            int string_length = string_number.length();
            for (number_count = string_length - 1; number_count >= 0; number_count--) {
                int current_number = Integer.parseInt(String.valueOf(string_number.charAt(string_length - number_count - 1)));
                if (current_number > 0){
                    if(current_number == 1){
                        if(number_count /4==0 && number_count >5){
                            result.append(number_name[current_number]);
                        }
                    }
                    else{
                        result.append(number_name[current_number]);
                    }
                    result.append(digit_name[number_count%4]);

                }

                if(number_count%4 == 0){
                    result.append(unit_name[number_count/4]);
                }
            }
            return result.toString();
        }
    }

    /**
     * Convert words in Korean to a number. The words should represent a number
     * in the range [0, Long.MAX_VALUE]. Otherwise, an exception should be thrown.
     * This function is an inverse of the function toWords. For example, the
     * string 일억삼천만칠천팔백구십 should be converted to 130007890.
     *
     * Hint: the String class provides various methods for string processing,
     * including contains, indexOf, lastIndexOf, split, and so on.
     *
     * @param words a string of words
     * @return a number
     * @throws IllegalArgumentException if the input is not valid
     */
    long toNumber(String words) {
        // TODO: implement this
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
        var input1 = 1234567890;
        var input2 = "십이억삼천사백오십육만칠천팔백구십";

        System.out.println(input1 + " -> " + new KoreanNumberTranslator().toWords(input1));
        System.out.println(input2 + " -> " + new KoreanNumberTranslator().toNumber(input2));
    }

}
