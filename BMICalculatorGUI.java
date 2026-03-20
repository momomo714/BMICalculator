import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;
public class BMICalculatorGUI extends Application{
    private TextField nameField;
    private TextField heightField;
    private TextField weightField;
    private DatePicker datePicker;
    private Label resultLabel;
    private TextArea historyArea;
    @Override
    public void start(Stage primaryStage){
       GridPane grid=new GridPane();
       grid.setPadding(new Insets(20));
       grid.setVgap(10);
       grid.setHgap(10);
        ColumnConstraints col1=new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2=new ColumnConstraints();
        col2.setPercentWidth(30);
        ColumnConstraints col3=new ColumnConstraints();
        col3.setPercentWidth(40);
        grid.getColumnConstraints().addAll(col1,col2,col3);

        grid.setStyle("-fx-background-color: #ffffff;");
        Scene scene=new Scene(grid,500,350);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

       Label nameLabel=new Label("姓名：");
       nameField=new TextField();
       nameField.setPromptText("请输入姓名：");
       grid.add(nameLabel,0,0);
       grid.add(nameField,1,0);

        Label heightLabel=new Label("身高(m)：");
        heightField=new TextField();
        heightField.setPromptText("例如1.75");
        grid.add(heightLabel,0,1);
        grid.add(heightField,1,1);

        Label weightLabel=new Label("体重(kg)：");
        weightField=new TextField();
        weightField.setPromptText("例如70");
        grid.add(weightLabel,0,2);
        grid.add(weightField,1,2);

        Label dateLabel=new Label("日期：");
        datePicker=new DatePicker();
        datePicker.setValue(LocalDate.now());
        grid.add(dateLabel,0,3);
        grid.add(datePicker,1,3);

        Button calcButton=new Button("计算BMI");
        Button clearButton=new Button("清空");
        Button historyButton=new Button("查看历史记录");
        grid.add(calcButton,0,4);
        grid.add(clearButton,1,4);
        grid.add(historyButton,2,4);

        resultLabel=new Label("在这里显示结果：");
        resultLabel.setStyle("-fx-font-size:14px;-fx-text-fill: pink;");
        grid.add(resultLabel,0,5,2,1);

        calcButton.setOnAction(e->calculateBMI());
        clearButton.setOnAction(e->clearFields());
        historyButton.setOnAction(e->showHistoryWindow());

        calcButton.setPrefHeight(30);
        clearButton.setPrefHeight(30);
        historyButton.setPrefHeight(30);

        calcButton.setId("calcButton");
        clearButton.setId("clearButton");
        historyButton.setId("historyButton");

        primaryStage.setTitle("BMI Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void calculateBMI(){
        String name=nameField.getText().trim();
        String heightText=heightField.getText().trim();
        String weightText=weightField.getText().trim();
        LocalDate date=datePicker.getValue();if(name.isEmpty()||heightText.isEmpty()||weightText.isEmpty()||date==null){
        resultLabel.setText("请填写所有字段~");
        return;
        }
double height,weight;
        try{
            height=Double.parseDouble(heightText);
            weight=Double.parseDouble(weightText);
        }
        catch(NumberFormatException e){
            resultLabel.setText("身高和体重必须为数字~");return;
        }
        double bmi=weight/(height*height);
        String advice=getAdvice(bmi);
        String result=String.format("%s,您的BMI为:%.2f\n%s",name,bmi,advice);
        resultLabel.setText(result);

        saveRecord(name,date.toString(),height,weight,bmi);
    }
    private String getAdvice(double bmi) {
        if (bmi < 18.5) {return "体型偏瘦，请补充营养~";
        } else if (bmi < 24) {return "体型正常，继续保持~";
        } else if (bmi < 28) {return "体型偏胖，请多运动~";
        } else {return "肥胖，请注意饮食和锻炼~";}
    }
    private void saveRecord(String name,String date,double height,double weight,double bmi){
        try(FileWriter fw=new FileWriter("records.txt",true);
        BufferedWriter bw=new BufferedWriter(fw)){
            bw.write(String.format("%s,%s,%.2f,%.2f,%.2f%n",name,date,height,weight,bmi));
        }catch(IOException e){resultLabel.setText(resultLabel.getText()+"\n保存记录失败~");}
    }
    private void clearFields(){
       nameField.clear();
        heightField.clear();
        weightField.clear();
        datePicker.setValue(LocalDate.now());
        resultLabel.setText("在这里显示结果~");
    }
    private void showHistoryWindow(){
        Stage historyStage=new Stage();
        historyStage.setTitle("历史记录");

        TextArea historyArea=new TextArea();
        historyArea.setEditable(false);
        historyArea.setWrapText(true);

        StringBuilder content=new StringBuilder();
        try(BufferedReader br=new BufferedReader(new FileReader("records.txt"))){
        String line;
        while((line=br.readLine())!=null){
        String[]parts=line.split(",");
        if(parts.length==5){
            content.append(String.format("姓名：%s  日期：%s  身高：%.2fm  体重：%.2fkg  BMI：%.2f\n",
                    parts[0],parts[1],
                    Double.parseDouble(parts[2]),
                    Double.parseDouble(parts[3]),
                    Double.parseDouble(parts[4])));
}else{content.append(line).append("\n");
    }}}catch(FileNotFoundException e){content.append("暂无历史记录");}
    catch(IOException e){content.append("读取文件失败");}
    historyArea.setText(content.toString());

    VBox vbox=new VBox(historyArea);
    vbox.setPadding(new Insets(10));
    Scene scene=new Scene(vbox,600,400);
    historyStage.setScene(scene);
    historyStage.show();
}
public static void main(String[] args){
        launch(args);
}
}