package Lexical;

import java.util.HashMap;
import java.util.LinkedList;

public class LexicalParser {
    private static LinkedList<Word> words;

    private static Word getWord(Reader r){
        int state = 0;
        ReservedWords rw = new ReservedWords();
        char c;
        StringBuilder word;
        word = new StringBuilder();
        while(!r.isEmpty()){
            switch (state){
                case 0:
                    c = r.getChar();
                    if(c=='\"'){
                        state = 1;
                    }
                    else if(c=='\''){
                        state = 2;
                    }
                    else if(c=='<'){
                        state = 3;
                        word.append(c);
                    }
                    else if(c=='='){
                        state = 4;
                        word.append(c);
                    }
                    else if(c=='>'){
                        state = 5;
                        word.append(c);
                    }
                    else if(('A'<=c&&c<='Z')||('a'<=c&&c<='z')){
                        state = 6;
                        word.append(c);
                    }
                    else if(c=='0'){
                        state = 7;
                        word.append(c);
                    }
                    else if('1'<=c&&c<='9'){
                        state = 8;
                        word.append(c);
                    }
                    else if(c=='('||c==')'||c=='{'||c=='}'||c=='['||c==']'||c==';'||c=='+'||c=='-'||c=='*'||c=='/'||c=='%'||c=='&'||c=='|'||c=='!'){
                        word.append(c);
                        state = 9;
                    }
                    break;
                case 1:
                    c = r.getChar();
                    if(32<=c&&c<'\"'||'\"'<c&&c<=126) {
                        word.append(c);
                        state = 1;
                    }
                    //此处warning是前面的问题引起的
                    else if(c=='\"'){
                        state = 10;
                    }
                    break;
                case 2:
                    c = r.getChar();
                    if(32<=c&&c<=126){
                        word.append(c);
                        state = 11;
                    }
                    break;
                case 3:
                    c = r.getChar();
                    if(c=='='){
                        state = 12;
                    }
                    else if(c!=' '){
                        r.putChar(c);
                        return new Word(Type.COMPARE,"LT");
                    }
                    break;
                case 4:
                    c = r.getChar();
                    if(c=='='){
                        state = 13;
                    }
                    else if(c!=' '){
                        r.putChar(c);
                        return new Word(Type.ASSIGN,"=");
                    }
                    break;
                case 5:
                    c = r.getChar();
                    if(c=='='){
                        state = 14;
                    }
                    else if(c!=' '){
                        r.putChar(c);
                        return new Word(Type.COMPARE,"GT");
                    }
                    break;
                case 6:
                    c = r.getChar();
                    if('A'<=c&&c<='Z'||'a'<=c&&c<='z'||'0'<=c&&c<='1'){
                        word.append(c);
                        state = 6;
                    }
                    else{
                        r.putChar(c);
                        if(rw.isReserved(word.toString())){
                            return new Word(Type.KeyWord,word.toString());
                        }
                        else
                            return new Word(Type.ID,word.toString());
                    }
                    break;
                case 7:
                    c = r.getChar();
                    if(c=='b'){
                        word.append(c);
                        state = 15;
                    }
                    else if(c=='x'){
                        word.append(c);
                        state = 16;
                    }
                    else if(c=='.'){
                        word.append(c);
                        state = 17;
                    }
                    else{
                        r.putChar(c);
                        return new Word(Type.Int,word.toString());
                    }
                    break;
                case 8:
                    c = r.getChar();
                    if('0'<=c&&c<='9'){
                        word.append(c);
                    }
                    else if(c=='.'){
                        word.append(c);
                        state = 18;
                    }
                    else{
                        r.putChar(c);
                        return new Word(Type.Int,word.toString());
                    }
                    break;
                case 9:
                    return new Word(Type.Punctuation, word.toString());
                case 10:
                    return new Word(Type.Str, word.toString());
                case 11:
                    c = r.getChar();
                    if(c=='\''){
                        state = 19;
                    }
                    else{
                        System.out.println("语法错误，应该使用'符号。");
                        r.putChar(c);
                        return new Word(Type.None,"");
                    }
                    break;
                case 12:
                    return new Word(Type.COMPARE, "LE");
                case 13:
                    return new Word(Type.COMPARE, "EQ");
                case 14:
                    return new Word(Type.COMPARE, "GE");
                case 15:
                    c = r.getChar();
                    if(c=='0'||c=='1'){
                        word.append(c);
                        state = 20;
                    }
                    else{
                        System.out.println("语法错误，应该使用0或1。");
                        r.putChar(c);
                        return new Word(Type.None,"");
                    }
                    break;
                case 16:
                    c = r.getChar();
                    if('0'<=c&&c<='9'||'a'<=c&&c<='f'){
                        word.append(c);
                        state = 21;
                    }
                    else{
                        System.out.println("语法错误，应该使用0,1,2...,9,a,b...,f。");
                        r.putChar(c);
                        return new Word(Type.None,"");
                    }
                    break;
                case 17:
                    c = r.getChar();
                    if('0'<=c&&c<='9'){
                        word.append(c);
                        state = 22;
                    }
                    else{
                        System.out.println("语法错误，应该使用0,1,2...,9。");
                        r.putChar(c);
                        return new Word(Type.None,"");
                    }
                    break;
                case 18:
                    c = r.getChar();
                    if('0'<=c&&c<='9'){
                        word.append(c);
                        state = 23;
                    }
                    else{
                        System.out.println("语法错误，应该使用0,1,2...,9。");
                        r.putChar(c);
                        return new Word(Type.None,"");
                    }
                    break;
                case 19:
                    return new Word(Type.Char,word.toString());
                case 20:
                    c = r.getChar();
                    if(c=='0'||c=='1'){
                        word.append(c);
                    }
                    else{
                        r.putChar(c);
                        return new Word(Type.Bin,word.toString());
                    }
                    break;
                case 21:
                    c = r.getChar();
                    if('0'<=c&&c<='9'||'a'<=c&&c<='f'){
                        word.append(c);
                    }
                    else{
                        r.putChar(c);
                        return new Word(Type.Hex,word.toString());
                    }
                    break;
                case 22:
                case 23:
                    c = r.getChar();
                    if('0'<=c&&c<='9'){
                        word.append(c);
                    }
                    else{
                        r.putChar(c);
                        return new Word(Type.Double,word.toString());
                    }
                    break;
            }
        }
        return new Word(Type.None,"");
    }
    public static void main(String[] args){
        Reader r = new Reader("D:\\GitWorkSpace\\tinyC\\src\\Lexical\\main.t");
        words = new LinkedList<>();
        Word w;
        while(!r.isEmpty()){
            w = getWord(r);
            if(!(w.getType()==Type.None))
                words.add(w);
        }
        for(Word wd:words){
            wd.info();
        }
    }
}
