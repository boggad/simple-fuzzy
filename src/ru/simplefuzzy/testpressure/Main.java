package ru.simplefuzzy.testpressure;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends Application {

    Group root;
    Polyline hand_water, hand_air, pres_arrow;
    Rotate paRotate, wRotate, aRotate;
    Fuzzy fuzzy;
    FuzzyPin pressure, delta, hand_angle;
    static Timer main;
    double baseDelta;
    Random rand;
    int i;
    double a;

    private void drawUi() {
        // задвижка воды
        Circle hand_base = new Circle(100, 100, 50);
        hand_base.setFill(Color.TRANSPARENT);
        hand_base.setStroke(Color.BLACK);
        Circle hand_center = new Circle(100, 100, 5);
        hand_center.setFill(Color.LIGHTBLUE);
        hand_center.setStroke(Color.BLACK);
        hand_water = new Polyline(80, 85, 130, 75, 230, 85, 230, 115, 130, 125, 80, 115, 80, 85);
        hand_water.setFill(Color.WHITE);
        wRotate = new Rotate(0, 100, 100);
        hand_water.getTransforms().add(wRotate);
        root.getChildren().add(hand_base);
        root.getChildren().add(hand_water);
        root.getChildren().add(hand_center);

        //задвижка воздуха
        Circle hand_base2 = new Circle(330, 100, 50);
        hand_base2.setFill(Color.TRANSPARENT);
        hand_base2.setStroke(Color.BLACK);
        Circle hand_center2 = new Circle(330, 100, 5);
        hand_center2.setFill(Color.SILVER);
        hand_center2.setStroke(Color.BLACK);
        hand_air = new Polyline(80, 85, 130, 75, 230, 85, 230, 115, 130, 125, 80, 115, 80, 85);
        hand_air.setTranslateX(230);
        hand_air.setFill(Color.WHITE);
        aRotate = new Rotate(330, 100, 100);
        hand_air.getTransforms().add(aRotate);
        root.getChildren().add(hand_base2);
        root.getChildren().add(hand_air);
        root.getChildren().add(hand_center2);

        //барометр

        Circle bar_base = new Circle(560, 100, 70);
        bar_base.setFill(Color.TRANSPARENT);
        bar_base.setStroke(Color.BLACK);
        root.getChildren().add(bar_base);
        //barometer sectors
        Arc bar_5 = new Arc(bar_base.getCenterX(), bar_base.getCenterY(), 63, 63, 320, 33.5);
        bar_5.setType(ArcType.OPEN);
        bar_5.setFill(Color.TRANSPARENT);
        bar_5.setStrokeWidth(10);
        bar_5.setStroke(Color.RED);
        root.getChildren().add(bar_5);

        Arc bar_4 = new Arc(bar_base.getCenterX(), bar_base.getCenterY(), 63, 63, 6.5, 33.5);
        bar_4.setType(ArcType.OPEN);
        bar_4.setFill(Color.TRANSPARENT);
        bar_4.setStrokeWidth(10);
        bar_4.setStroke(Color.RED);
        root.getChildren().add(bar_4);

        Arc bar_3 = new Arc(bar_base.getCenterX(), bar_base.getCenterY(), 68, 68, 48.5, 33.5);
        bar_3.setType(ArcType.ROUND);
        bar_3.setFill(Color.GREEN);
        root.getChildren().add(bar_3);

        Arc bar_2 = new Arc(bar_base.getCenterX(), bar_base.getCenterY(), 63, 63, 90.5, 33.5);
        bar_2.setType(ArcType.OPEN);
        bar_2.setFill(Color.TRANSPARENT);
        bar_2.setStrokeWidth(10);
        bar_2.setStroke(Color.GREEN);
        root.getChildren().add(bar_2);

        Arc bar_1 = new Arc(bar_base.getCenterX(), bar_base.getCenterY(), 63, 63, 136.5, 33.5);
        bar_1.setType(ArcType.OPEN);
        bar_1.setFill(Color.TRANSPARENT);
        bar_1.setStrokeWidth(10);
        bar_1.setStroke(Color.RED);
        root.getChildren().add(bar_1);

        Circle blank_c = new Circle(bar_base.getCenterX(), bar_base.getCenterY(), 20);
        blank_c.setFill(Color.WHITE);
        blank_c.setStroke(Color.WHITE);
        root.getChildren().add(blank_c);

        Text bar_t0 = new Text("0");
        Font bar_text = new Font("Tahoma", 16);
        bar_t0.setFont(bar_text);
        bar_t0.setLayoutX(bar_base.getCenterX() - 82);
        bar_t0.setLayoutY(bar_base.getCenterY() - 2);
        root.getChildren().add(bar_t0);

        Text bar_t1 = new Text("1");
        bar_t1.setFont(bar_text);
        bar_t1.setLayoutX(bar_base.getCenterX() - 57);
        bar_t1.setLayoutY(bar_base.getCenterY() - 54);
        root.getChildren().add(bar_t1);

        Text bar_t2 = new Text("2");
        bar_t2.setFont(bar_text);
        bar_t2.setLayoutX(bar_base.getCenterX() + 2);
        bar_t2.setLayoutY(bar_base.getCenterY() - 73);
        root.getChildren().add(bar_t2);

        Text bar_t3 = new Text("3");
        bar_t3.setFont(bar_text);
        bar_t3.setLayoutX(bar_base.getCenterX() + 49);
        bar_t3.setLayoutY(bar_base.getCenterY() - 52);
        root.getChildren().add(bar_t3);

        Text bar_t4 = new Text("4");
        bar_t4.setFont(bar_text);
        bar_t4.setLayoutX(bar_base.getCenterX() + 74);
        bar_t4.setLayoutY(bar_base.getCenterY() + 2);
        root.getChildren().add(bar_t4);
        //-----
        pres_arrow = new Polyline(bar_base.getCenterX() - 7, bar_base.getCenterY() - 3, bar_base.getCenterX() + 63,
                bar_base.getCenterY(), bar_base.getCenterX() - 7, 103, bar_base.getCenterX() - 7, bar_base.getCenterY() - 3);
        pres_arrow.setFill(Color.WHITE);
        root.getChildren().add(pres_arrow);
        paRotate = new Rotate(0, 560, 100);
        pres_arrow.getTransforms().add(paRotate);
        Circle bar_cen = new Circle(bar_base.getCenterX(), bar_base.getCenterY(), 5);
        bar_cen.setFill(Color.WHITE);
        bar_cen.setStroke(Color.BLACK);
        root.getChildren().add(bar_cen);

    }

    private void drawSet() {
        TextField tfPressure = new TextField();
        root.getChildren().add(tfPressure);
        tfPressure.setLayoutX(150);
        tfPressure.setLayoutY(250);

        Button btnPressure = new Button("Set Pressure");
        root.getChildren().add(btnPressure);
        btnPressure.setLayoutX(50);
        btnPressure.setLayoutY(250);
        btnPressure.setOnAction((ActionEvent event) -> {
                double val = Double.valueOf(tfPressure.getText());
                synchronized (pressure) {
                    pressure.value = val;
                }
        });

        TextField tfDelta = new TextField();
        root.getChildren().add(tfDelta);
        tfDelta.setLayoutX(150);
        tfDelta.setLayoutY(310);

        Button btnDelta = new Button("Set Delta");
        root.getChildren().add(btnDelta);
        btnDelta.setLayoutX(50);
        btnDelta.setLayoutY(310);
        btnDelta.setOnAction((ActionEvent event) -> {
                double val = Double.valueOf(tfDelta.getText());
                synchronized (delta) {
                    baseDelta = val;
                }
        });

    }

    private void makeFuzzy(){
        pressure = new FuzzyPin();
        FuzzyTerm veryLow = new FuzzyTerm(new FuzzyFunc(0.2, 2.5, 0.3));
        FuzzyTerm low = new FuzzyTerm(new FuzzyFunc(0.4, 2.5, 0.85));
        FuzzyTerm normal = new FuzzyTerm(new FuzzyFunc(0.6, 2.5, 1.65));
        FuzzyTerm high = new FuzzyTerm(new FuzzyFunc(0.6, 2.5, 2.55));
        pressure.terms.add(veryLow);
        pressure.terms.add(low);
        pressure.terms.add(normal);
        pressure.terms.add(high);

        delta = new FuzzyPin();
        FuzzyTerm down = new FuzzyTerm(new FuzzyFunc(0.75, 2.5, -1.25));
        FuzzyTerm equal = new FuzzyTerm(new FuzzyFunc(0.7, 2.5, 0));
        FuzzyTerm up = new FuzzyTerm(new FuzzyFunc(0.75, 2.5, 5, 1.25));
        delta.terms.add(down);
        delta.terms.add(equal);
        delta.terms.add(up);

        hand_angle = new FuzzyPin();
        FuzzyTerm air = new FuzzyTerm(new FuzzyFunc(15, 1.8, -30));
        FuzzyTerm nothing = new FuzzyTerm(new FuzzyFunc(15, 1.8, 0));
        FuzzyTerm water = new FuzzyTerm(new FuzzyFunc(15, 1.8, 30));
        hand_angle.terms.add(air);
        hand_angle.terms.add(nothing);
        hand_angle.terms.add(water);

        fuzzy = new Fuzzy(hand_angle);
        fuzzy.addInput(pressure);
        fuzzy.addInput(delta);

        FuzzyRule r1 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r1.outTerm = water;
        r1.terms.add(veryLow);
        r1.terms.add(down);

        FuzzyRule r2 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r2.outTerm = water;
        r2.terms.add(veryLow);
        r2.terms.add(equal);

        FuzzyRule r3 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r3.outTerm = water;
        r3.terms.add(veryLow);
        r3.terms.add(up);

        FuzzyRule r4 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r4.outTerm = water;
        r4.terms.add(low);
        r4.terms.add(down);

        FuzzyRule r5 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r5.outTerm = water;
        r5.terms.add(low);
        r5.terms.add(equal);

        FuzzyRule r6 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r6.outTerm = water;
        r6.terms.add(low);
        r6.terms.add(up);

        FuzzyRule r7 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r7.outTerm = nothing;
        r7.terms.add(normal);
        r7.terms.add(down);

        FuzzyRule r8 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r8.outTerm = nothing;
        r8.terms.add(normal);
        r8.terms.add(equal);

        FuzzyRule r9 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r9.outTerm = nothing;
        r9.terms.add(normal);
        r9.terms.add(up);

        FuzzyRule r10 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r10.outTerm = nothing;
        r10.terms.add(high);
        r10.terms.add(down);

        FuzzyRule r11 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r11.outTerm = air;
        r11.terms.add(high);
        r11.terms.add(equal);

        FuzzyRule r12 = new FuzzyRule(FuzzyRule.FUZZY_AND);
        r12.outTerm = air;
        r12.terms.add(high);
        r12.terms.add(up);

        fuzzy.addRule(r1);
        fuzzy.addRule(r2);
        fuzzy.addRule(r3);
        fuzzy.addRule(r4);
        fuzzy.addRule(r5);
        fuzzy.addRule(r6);
        fuzzy.addRule(r7);
        fuzzy.addRule(r8);
        fuzzy.addRule(r9);
        fuzzy.addRule(r10);
        fuzzy.addRule(r11);
        fuzzy.addRule(r12);
    }

    private void rotateBar(double bar) {
        double coef = 175.0/4.0;
        double nAngle, curAngle;
        curAngle = paRotate.getAngle();
        nAngle = bar*coef - 175;
        paRotate.setAngle(nAngle);

        /*Timeline t = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(paRotate.angleProperty(), 0)),
                new KeyFrame(Duration.millis(250), new KeyValue(paRotate.angleProperty(), nAngle - curAngle))
        );
        t.play();*/
    }

    private void rotateWater(double angle) {
        double nAngle;
        nAngle = angle - wRotate.getAngle();
        wRotate.setAngle(angle);
        /*Timeline t = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(wRotate.angleProperty(), 0)),
                new KeyFrame(Duration.millis(250), new KeyValue(wRotate.angleProperty(), angle))
        );
        t.play();*/
    }

    private void rotateAir(double angle) {
        double nAngle;
        nAngle = angle - aRotate.getAngle();
        aRotate.setAngle(angle);
        /*Timeline t = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(aRotate.angleProperty(), 0)),
                new KeyFrame(Duration.millis(250), new KeyValue(aRotate.angleProperty(), angle))
        );
        t.play();*/
    }

    private void simulate() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFX");
        root = new Group();
        drawUi();
        drawSet();
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        makeFuzzy();
        rand = new Random();
        baseDelta = -1;
        pressure.value = 1.2;
        delta.value = baseDelta;
        i = 1;
        a = 0;
        main = new Timer();
        main.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    rotateBar(pressure.value);
                    if (i >= 30) {
                        a = fuzzy.output();
                        if (a == 0) {
                            rotateWater(a);
                            rotateAir(a);
                        }
                        if (a > 0) {
                            rotateWater(-a);
                            rotateAir(0);
                        }
                        if (a < 0) {
                            rotateAir(a);
                            rotateWater(0);
                        }
                        i = 1;
                        //System.out.println("Pressure: "+pressure.value+"; delta: "+delta.value+"; angle: "+a);
                    }
                    i++;
                    double dwP = 0; //дельда давления при открытом кране воды
                    double daP = 0; //дельда давления при открытом кране воздуха
                    double time = 600;
                    if (a > 0)
                        dwP = a/3;
                    else
                        daP = a/3;

                    delta.value = baseDelta + dwP + daP;
                    pressure.value += delta.value/time;
                });
            }
        }, 0, 100);
    }


    public static void main(String[] args) {
        launch(args);
        main.cancel();
    }
}
