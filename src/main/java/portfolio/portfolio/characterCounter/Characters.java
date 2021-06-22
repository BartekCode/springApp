package portfolio.portfolio.characterCounter;

import portfolio.portfolio.logic.UserService;
import portfolio.portfolio.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Characters {
 static String word = "bbaaaarrtttttteeeekkkkksssszkkkkkkuuddddllaaaaarrrrzzzzzzzzMMMAASSSAAPAPAPAFFFFFs";

   public static String reversWord(String word){
       int n = word.length();
       String finalWord = null;
       StringBuilder stringBuilder = new StringBuilder();
       for (int j = 0; j < word.length(); j++) {
           StringBuilder append = stringBuilder.append(word.charAt(n-1));
           finalWord =append.toString();
           n--;
       }

       return finalWord;
   }

   public int sumaRekurencja(int n){
       if (n>0){
           return n + sumaRekurencja(n-1);
       }else {
           return 0;
       }
   }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static void main(String[] args) throws ParseException {
        Characters characters = new Characters();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder countLettersWord = stringBuilder.append("");
        int i = 1;


        for (int j = 0; j < word.length()-1; j++) {
                if (word.charAt(j) == word.charAt(j + 1)) {
                    i++;
                } else {
                    countLettersWord = stringBuilder.append(word.charAt(j));
                    countLettersWord = stringBuilder.append(i);
                    i = 1;
                }
        }
        countLettersWord = stringBuilder.append(word.charAt(word.length()-1)+""+i);
        System.out.println(countLettersWord.toString());
        System.out.println(countLettersWord.reverse().toString());
        String s = reversWord(word);
        System.out.println(s);



        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("a", 1);
        mapa.put("b", 2);
        mapa.put("c", 3);
        mapa.put("d", 4);



        for (Map.Entry<String,Integer> entry: mapa.entrySet()){
            System.out.println(entry.getKey()+"/"+entry.getValue());
        }




        int rekurencja = characters.sumaRekurencja(3);
        System.out.println("Rekurencja "+ rekurencja);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.GERMAN);
        String dateString = "07-06-2013 10:11:59";
        Date date = formatter.parse(dateString);
        String format = formatter.format(date);

        Calendar now = Calendar.getInstance();

        System.out.println(date);

        String liczba = "12";
        palindrom(liczba);




    }

    private static void palindrom(String liczba) {
StringBuilder stringBuilder = null;
        char stChar = liczba.charAt(0);
        char lstChar = liczba.charAt(liczba.length()-1);
        if (stChar==lstChar){
                    System.out.println("liczba " +liczba +"jest palindromem");
        }else {
            int dodwaniaLiczb = Integer.parseInt(liczba);
            String reversWord = reversWord(liczba);
            int reversInt = Integer.parseInt(reversWord);
            System.out.println(reversInt);
            int suma = dodwaniaLiczb+reversInt;
            System.out.println(suma);
            String stringSum = Integer.toString(suma);
            palindrom(stringSum);
        } // musi wyjsc cos takiego 16 + 61  i jezeli wynik palindrom to koniec

        String danuta = reversWord("Danuta");
        System.out.println(danuta);
    }



}

