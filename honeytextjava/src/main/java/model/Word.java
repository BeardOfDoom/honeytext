package model;

public class Word {

    private String text;
    private WordClass wordClass;
    private String seedFromBinary;

    public String getText() {
        return text;
    }

    public WordClass getWordClass() {
        return wordClass;
    }

    public String getSeedFromBinary() {
        return seedFromBinary;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWordClass(WordClass wordClass) {
        this.wordClass = wordClass;
    }

    public void setSeedFromBinary(String seedFromBinary) {
        this.seedFromBinary = seedFromBinary;
    }
}
