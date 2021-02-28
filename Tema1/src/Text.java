

/**
 * Clasa text.utils.Text are trei atribute:  plainText, cryptoText și key.
 * Și metodele cript() și decript();
 */
public class Text {
    private String plainText;
    private String cryptoText;
    private String key;

    /**
     * Face conversia din ascii in integer modulo 26, pentru litere mici
     */
    public int aToI(char ch) {
        return (int) ch - 97;
    }



    /**
     * Face conversia din ascii in integer modulo 26, pentru litere mari
     */
    public int aToIBig(char ch) {
        return (int) ch - 65;
    }

    /**
     * Face conversia din integer in ascii, în litere mari
     */
    public char iToA(int nr) {
        return (char) (nr + 65);
    }

    /**
     * Parcurgem plainTextul, si eliminam orice nu este litera.
     */
    public void filtreaza() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                stringBuilder.append(c);
            }
        }
        plainText = stringBuilder.toString();
    }


    /**
     * Facem toate literele din plaintext si din cheie, litere mici
     */
    public void lower() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            stringBuilder.append(Character.toLowerCase(c));
        }
        plainText = stringBuilder.toString();
        stringBuilder =new StringBuilder();

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            stringBuilder.append(Character.toLowerCase(c));
        }
        key = stringBuilder.toString();

    }


    /**
     * Cripteaza un text cu ajutorul atributului 'key' și îl atribuie atributului 'cryptoText'
     */
    public void cript() {
        StringBuilder stringBuilder = new StringBuilder();
        int k = 0;
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (k == key.length()) {
                k = 0;
            }
            char keyCh = key.charAt(k);
            k++;

            stringBuilder.append(iToA((aToI(c) + aToI(keyCh)) % 26));

        }
        cryptoText = stringBuilder.toString();
    }

    /**
     * Punem in plainText textul decriptat
     */
    public void decript() {
        StringBuilder stringBuilder = new StringBuilder();
        int k = 0;
        for (int i = 0; i < cryptoText.length(); i++) {
            char c = cryptoText.charAt(i);
            if (k == key.length()) {
                k = 0;
            }
            char keyCh = key.charAt(k);
            k++;

            int nr = (aToIBig(c) - aToI(keyCh) + 26) % 26;
            stringBuilder.append(iToA(nr));

        }
        plainText = stringBuilder.toString();
    }



    public Text() {
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPlainText() {
        return plainText;
    }

    public String getCryptoText() {
        return cryptoText;
    }


}
