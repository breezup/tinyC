package Lexical;

enum Type{ID, COMPARE, ASSIGN, KeyWord, Int, Double, Punctuation, Char, Str, Bin, Hex, None}

class Word {
    private Type type;
    private String value;
    Word(Type type,String value){

        this.type = type;
        this.value = value;
    }
    String info(){
        return this.type+" --> "+this.value;
    }
    Type getType(){
        return this.type;
    }
}
