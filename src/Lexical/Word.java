package Lexical;

enum Type{ID, COMPARE, ASSIGN, KeyWord, Int, Double, Punctuation, Char, Str, Bin, Hex, None}

class Word {
    private Type type;
    private String value;
    Word(Type type,String value){

        this.type = type;
        this.value = value;
    }
    void info(){
        System.out.println("type:"+this.type+" "+"value:"+this.value);
    }
    Type getType(){
        return this.type;
    }
}
