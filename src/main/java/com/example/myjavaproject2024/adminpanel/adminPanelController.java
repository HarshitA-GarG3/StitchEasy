package com.example.myjavaproject2024.adminpanel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import com.example.myjavaproject2024.HelloApplication;
import com.example.myjavaproject2024.jdbc.mysqlconnectionclass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class adminPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField ipassword;

    @FXML
    private Button iconsole;

    @FXML
    private Button ienroll;

    @FXML
    private Button iexplorer;

    @FXML
    private Button ilist;

    @FXML
    private Button imeasure;

    @FXML
    private Button iorder;

    @FXML
    private Label id;

    @FXML
    private Label ir;

    @FXML
    private Label ip;

    @FXML
    private PieChart ipieChart;

    @FXML
    private Button iproducts;

    Connection con;
    PreparedStatement stmt;

    @FXML
    void doEnroll(ActionEvent event) {
        loadScene(event, "/com/example/myjavaproject2024/custEnrolll/CustEnrollView.fxml");
    }

    @FXML
    void doExplore(ActionEvent event) {
        loadScene(event, "/com/example/myjavaproject2024/measurementsExplorerr/MeasurementExplorerView.fxml");

    }

    @FXML
    void doMeasurement(ActionEvent event) {
        loadScene(event, "/com/example/myjavaproject2024/measurementt/MeasurementView.fxml");

    }

    @FXML
    void doReadyProducts(ActionEvent event) {
        loadScene(event, "/com/example/myjavaproject2024/getReadyProductss/getReadyProductsView.fxml");
    }

    @FXML
    void doWorkersConsole(ActionEvent event) {
        loadScene(event, "/com/example/myjavaproject2024/workersConsolee/WorkersConsoleView.fxml");

    }

    @FXML
    void doWorkersList(ActionEvent event) {
        loadScene(event, "/com/example/myjavaproject2024/tableWorkersVieww/tableWorkersView.fxml");
    }

    @FXML
    void doorderDelivery(ActionEvent event) {
        loadScene(event, "/com/example/myjavaproject2024/orderDeliveryPanell/orderDeliveryPanelView.fxml");

    }

    @FXML
    void doUnlock(ActionEvent event) {

        if(ipassword.getText().equals("Admin@123")) {
            showMyMsg("Password is correct");
            iconsole.setDisable(false);
            ienroll.setDisable(false);
            iexplorer.setDisable(false);
            iproducts.setDisable(false);
            iorder.setDisable(false);
            imeasure.setDisable(false);
            ilist.setDisable(false);

        }
        else
            showMyMsg("Password is not correct");
    }


    @FXML
    void initialize()  {

        con = mysqlconnectionclass.doConnect();
        if (con == null)
            System.out.println("Connection Did not Established");
        else
            System.out.println("Connection Doneeee");



        fillsatus();


    }

    void fillsatus()  {
    try {
        stmt = con.prepareStatement("select status from measurements");
        ResultSet rs = stmt.executeQuery();
        int ct1 = 0;
        int ct2 = 0;
        int ct3 = 0;
        int arr = 0;
        while (rs.next()) {
            arr = rs.getInt("status");
            if (arr == 1) {
                ct1++;
            } else if (arr == 2) {
                ct2++;
            } else if (arr == 3) {
                ct3++;
            }


        }
        System.out.println(ct1);
        ip.setText(String.valueOf(ct1));
        ir.setText(String.valueOf(ct2));
        id.setText(String.valueOf(ct3));


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("In Progress", ct1 + ct2),
                new PieChart.Data("Completed", ct3));
        ipieChart.setData(pieChartData);
    }
    catch (Exception exp)
    {
        exp.printStackTrace();
    }

    }

    private void loadScene(ActionEvent event, String fxmlFile) {
        try {
//            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
            FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            Stage stage=new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load the scene: " + fxmlFile);
        }
    }

    void showMyMsg(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Alert alert = new Alert(Alert.AlertType.WARNING);


        alert.setTitle("Information Dialog");


        alert.setHeaderText("Records");
        alert.setContentText(msg);

        alert.showAndWait();

    }
}
