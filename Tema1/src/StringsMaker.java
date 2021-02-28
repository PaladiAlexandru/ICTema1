

/**
 * Face stringuri din 'pas' în pas'
 */
public class StringsMaker {
    private int pas;
    private final String[] strings = new String[100_000_000];

    /**
     * Primește un text si face stringuri din 'pas' in 'pas'
     */
    public void makeStrings(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        int runda = 0;
        while (runda < pas) {
            for (int i = runda; i < text.length() - pas; i = i + pas) {
                char c = text.charAt(i);
                stringBuilder.append(c);
            }

            strings[runda] = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            runda++;
        }

    }

    public void setPas(int pas) {
        this.pas = pas;
    }

    public String[] getStrings() {
        return strings;
    }

    public StringsMaker() {
    }
}
