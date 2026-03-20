import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
public class Hello{
    static double height;
    static double weight;
    static String name;
    static String time;
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args){while(true){
    System.out.print("请输入您的姓名：");
    name=scanner.nextLine();
    data();calculator();
    System.out.print("是否查看历史记录（y/n):");
    String choice1=scanner.nextLine();
    if(choice1.equalsIgnoreCase("y"))
    {viewHistory();}
    System.out.print("是否继续计算（y/n):");
    String choice2=scanner.nextLine();
    if(choice2.equalsIgnoreCase("n")){break;}}scanner.close();}
    public static void data(){
        System.out.println("你好，"+name);
        height=readPositiveDouble("请输入您的身高(m)：");
        weight=readPositiveDouble("请输入您的体重(kg)：");
        System.out.print("请输入年：");
        int year=(int)readPositiveDouble("");
        System.out.print("请输入月：");
        int month=(int)readPositiveDouble("");
        System.out.print("请输入日：");
        int day=(int)readPositiveDouble("");
        try{
            LocalDate date=LocalDate.of(year,month,day);
            time=date.toString();}
        catch(DateTimeException e){System.out.println("无效日期，已使用当前日期替代~");
        time=LocalDate.now().toString();}
        System.out.println("你好，"+name+"\n已为您记录您在"+time+"的身体数据");
    }
    public static void calculator(){
        double bmi=weight/(height*height);
        System.out.printf("您的BMI是%.2f\n",bmi);
        if(bmi<18.5){System.out.println("体型偏瘦，请补充营养~");}
        else if(bmi<24){System.out.println("体型正常，继续保持~");}
        else if(bmi<28){System.out.println("体型偏胖，请多运动~");}
        else{System.out.println("肥胖，请注意饮食和锻炼~");}
        try(FileWriter fw=new FileWriter("records.txt",true);
            BufferedWriter bw=new BufferedWriter(fw)){
        bw.write(String.format("%s,%s,%.2f,%.2f,%.2f%n",name,time,height,weight,bmi));}
    catch(IOException e){System.out.println("保存记录失败 "+e.getMessage());}}
    public static double readPositiveDouble(String prompt){
        double value;
        while(true){
            System.out.print(prompt);
            if(scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                scanner.nextLine();
                if (value > 0) {
                    break;
                } else {
                    System.out.println("请输入正数：");
                }
            }else{System.out.println("输入无效，请输入正确数字：");scanner.nextLine();}
            }return value;}
    public static void viewHistory(){
        System.out.println("\n---历史记录---");
        try(BufferedReader br=new BufferedReader(new FileReader("records.txt"))){
            String line;
            while((line=br.readLine())!=null){
                String[]parts=line.split(",");
                if(parts.length==5){
                    System.out.printf("姓名：%s,日期：%s,身高：%.2fm,体重：%.2fkg,BMI:%.2f\n",
                            parts[0],parts[1],Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]),Double.parseDouble(parts[4]));
                }else{System.out.println((line));}
        }
    }catch(FileNotFoundException e){System.out.println("暂无历史记录~");
    }catch(IOException e){System.out.println("读取历史记录失败~"+e.getMessage());}
    System.out.println("------------");
    }}