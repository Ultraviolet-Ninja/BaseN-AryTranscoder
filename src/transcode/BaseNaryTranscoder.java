package transcode;

public class BaseNaryTranscoder {
    public static final String BASE_91_CODEX = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%&()*+,./;<=>?@[]^_`{|}~\":";
    
    private final char[] codexString;

    public BaseNaryTranscoder(String codexString) {
        this.codexString = codexString.toCharArray();
    }

    public String encrypt(int value) {
        if (value < codexString.length) { return String.valueOf(codexString[value]);}

        StringBuilder sb = new StringBuilder();
        int size = codexString.length;
        while (value >= size) {
            int modIndex = value % size;
            sb.append(codexString[modIndex]);
            value /= size;
        }

        return sb.append(codexString[value])
                .reverse()
                .toString();
    }

    public int decrypt(String str) {
        int degree = str.length() - 1;
        int output = 0;
        String searchString = String.valueOf(codexString);

        for (char letter : str.toCharArray()) {
            int value = searchString.indexOf(letter);
            if (value < 0) throw new IllegalArgumentException("Detected letter not in the codex");
            output += (int) Math.pow(codexString.length, degree--) * value;
        }
        return output;
    }

    public int getBase() {return codexString.length;}
}
