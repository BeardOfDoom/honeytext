package model;

//http://erwinkomen.ruhosting.nl/eng/2014_Longdale-Labels.htm
public enum WordClass {
    FW, JJ, JJS, NN, NNS, NNP, NNPS, PRP, PRPS, VB, VBD, VBG, VBN, VBP, VBZ;

    public static boolean contains(String s)
    {
        for(WordClass wordClass : values())
            if (wordClass.name().equals(s))
                return true;
        return false;
    }
}
