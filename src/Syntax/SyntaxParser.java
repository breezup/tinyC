package Syntax;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class SyntaxParser {
    private static boolean isToken(String str){
        return str.equals("$") || str.equals("(") || str.equals(")") || str.equals("const") || str.equals("id") || str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("=") || str.equals(";");
    }

    public static void main(String[] args) throws FileNotFoundException {
        //将Token文件内容按token字符串读取到TokenStack中
        LinkedList<String> TokenArray = new LinkedList<>();
        File file = new File("D:\\GitWorkSpace\\tinyC\\src\\Syntax\\Token");
        if(!file.exists())
            System.out.println("文件未找到！");
        Scanner reader = new Scanner(file);
        String token;
        while(true){
            try {
                token = reader.next();
                if(isToken(token))
                    TokenArray.add(token);
                else
                    System.out.println(token+" is not a valid token!");
            }catch (Exception e){
                break;
            }
        }
        Table table = new Table();
        LinkedList<Integer> reductionSeq;
        File outfile = new File("D:\\GitWorkSpace\\tinyC\\src\\Syntax\\Seq");
        try {
            if (!outfile.exists())
                outfile.createNewFile();
            FileWriter fw = new FileWriter(outfile);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write("1. S->A;\r");
            fw.write("2. A->id=E\r");
            fw.write("3. E->E*E\r");
            fw.write("4. E->E/E\r");
            fw.write("5. E->E+E\r");
            fw.write("6. E->E-E\r");
            fw.write("7. E->id\r");
            fw.write("8. E->const\r");
            fw.write("9. E->(E)\r");
            fw.flush();
            fw.write("============reduction sequences============\r");
            while(TokenArray.size()>0){
                reductionSeq = table.getReductionSeq(TokenArray);
                for (Integer integer : reductionSeq) {
                    fw.write(integer+" ");
                }
                fw.write("\r");
                fw.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            System.out.println("All sequences were written.");
    }
}
