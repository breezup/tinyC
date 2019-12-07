package Lexical;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Reader{
    private char[] buffer;
    private FileReader reader;
    private boolean  end;

    Reader(String filename) {
        File file = new File(filename);
        try {
            this.reader = new FileReader(file);
            this.buffer = new char[1];
            this.buffer[0] = ' ';
            this.end = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    boolean isEmpty(){
        return this.end;
    }
    char getChar(){
        char ret = ' ';
        if(this.buffer[0]!=' '){
            ret = this.buffer[0];
            this.buffer[0] = ' ';
        }
        else{
            try {
                if (reader.read(buffer) != -1) {
                    ret = this.buffer[0];
                    this.buffer[0] = ' ';
                }
                else{
                    this.end = true;
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return ret;
    }
    void putChar(char c){
        this.buffer[0] = c;
    }
}