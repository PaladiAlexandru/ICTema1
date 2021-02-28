



/**
 * Poate calcula indexul de coincidenta
 * si frecventa literelor intr-un text
 */
public class StringsOperations {
    private String language;
    private String[] keyLengthStrings = new String[1000];

    /**
     * Calculeaza probabilitatea de aparitie a lui 'i' in textul dat ca parametru
     */
    public double p(char i, String text) {
        double aparitii = 0;
        for (int j = 0; j < text.length(); j++) {
            char c = text.charAt(j);
            if (c == i) {
                aparitii++;
            }
        }
        return aparitii / text.length();
    }

    /**
     * calculeaza indexul de coincidenta mutuala
     */
    public double MIC( String text) {
        double[] prob = {0.082, 0.015, 0.028, 0.043, 0.13, 0.022, 0.02, 0.061, 0.07, 0.0015, 0.0077, 0.04, 0.024, 0.067, 0.075, 0.019, 0.00095, 0.06, 0.063, 0.091, 0.028, 0.0098, 0.024, 0.0015, 0.02, 0.00074};
        double sum = 0;
        Text text1 = new Text();
        for (int j = 0; j < 26; j++) {
            sum += prob[j] * (f(j, text) / text.length());
        }
        return sum;
    }// probabilitatea ca extragand cate o litera din 2 texte diferite, acea litera sa coincida

    /**
     * Determina cheia
     */
    public String findKey( int keyLength) {
        Text text = new Text();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < keyLength; i++) {

            int s = -1;


            do {
                s = s + 1;
            }while (MIC(SHIFT(keyLengthStrings[i], s)) < 0.060 || MIC(SHIFT(keyLengthStrings[i], s)) > 0.070);

            key.append(text.iToA((26 - s) % 26));
        }
        return key.toString();
    }

    /**
     * Calculeaza frecventa literei primite ca parametru in textul primit ca parametru
     */
    public double f(int i, String text) {
        Text text1 = new Text();
        int nr = 0;
        for (int j = 0; j < text.length(); j++) {
            char c = text.charAt(j);
            if (c == text1.iToA(i)) {
                nr++;
            }
        }
        return nr;
    }

    public double findIC(String text) {
        double sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += (f(i, text) / text.length()) * ((f(i, text) - 1) / (text.length() - 1));
        }
        return sum;
    }


    public StringsOperations() {
    }

    public int getKeyLength(Text text1) {
        String[] strings;
        StringsMaker stringsMaker = new StringsMaker();
        int pas = 2;
        int ok = 0;
        start:
        while (true) {
            stringsMaker.setPas(pas);
            stringsMaker.makeStrings(text1.getCryptoText());
            strings = stringsMaker.getStrings();

            for (int i = 0; i < pas; i++) {
                if (findIC(strings[i]) >= 0.060 && findIC(strings[i]) <= 0.070) {
                    ok++;
                } else {
                    break;
                }
            }
            if (ok == pas) {
                keyLengthStrings = stringsMaker.getStrings();         //le salvam global
                break;

            }
            pas++;
        }

        return pas;
    }

    /**
     * Muta toate caracterele din text cu s pozitii mai la dreapta
     */
    public String SHIFT(String y, int s) {
        Text text = new Text();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < y.length(); i++) {
            char c = y.charAt(i);
            stringBuilder.append(text.iToA((text.aToIBig(c) + s) % 26));

        }
        return stringBuilder.toString();
    }

}
