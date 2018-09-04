package model;

public class Word {

    public Word(String text, WordClass wordClass) {
        this.text = text;
        this.wordClass = wordClass;
    }

    private String text;
    private WordClass wordClass;

    public String getText() {
        return text;
    }

    public WordClass getWordClass() {
        return wordClass;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWordClass(WordClass wordClass) {
        this.wordClass = wordClass;
    }
}
