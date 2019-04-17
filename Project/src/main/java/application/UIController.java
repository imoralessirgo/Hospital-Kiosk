package application;

import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.DayView;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * The UIController Superclass
 * This controller handles all of the other UIControllers as well as
 * storing the EditableTableCell which is used in adminTools
 * @author Jonathan Chang
 * @version interation 1
 */
public class UIController {

    // The various scenes that this UIController handles
    public static final String LOGIN_MAIN = "LM";
    public static final String WELCOME_MAIN = "WM";
    public static final String GUEST_MAIN_MENU_MAIN = "GMMM";
    public static final String USER_MAIN_MENU_MAIN = "UMMM";
    public static final String ADMIN_MAIN_MENU_MAIN = "AMMM";
    public static final String ABOUT_PAGE= "AP";

    public static final String PATHFINDING_MAIN = "PFM";

    public static final String RESERVATIONS_MAIN = "RVM";
    public static final String RESERVATIONS_EDIT = "RVE";
    public static final String RESERVATIONS_MAIN_MENU = "RVMM";
    public static final String RESERVATIONS_CALENDAR_VIEW = "RVCV";

    public static final String ADMIN_TOOLS_MAIN = "ATM";
    public static final String ADMIN_TOOLS_VIEW_NODES = "ATVN";
    public static final String ADMIN_TOOLS_VIEW_EDGES = "ATVE";
    public static final String ADMIN_TOOLS_VIEW_SERVICE_REQUESTS = "ATVSR";
    public static final String POPUP_DIRECTIONS = "PUD";
    public static final String ADMIN_TOOLS_VIEW_USERS = "ATVU";
    public static final String ADMIN_TOOLS_CHANGE_ALGORITHM = "ATCA";
    public static final String ADMIN_TOOLS_MAP_VIEW = "ATMV";
    public static final String ADMIN_TOOLS_EDIT_RESERVATIONS = "ATER";

    public static final String SERVICE_REQUEST_MAIN = "SRM";
//    public static final String SERVICE_REQUEST_BASE = "SRB";
    public static final String SERVICE_REQUEST_SANITATION = "SRSA";
    public static final String SERVICE_REQUEST_INTERPRETER = "SRIN";


    public static final String SERVICE_REQUEST_PRESCRIPTION_SERVICES_MAIN = "SRPSM";
    public static final String SERVICE_REQUEST_IT = "SRIT";
    public static final String SERVICE_REQUEST_BABYSITTING = "SRB";
    public static final String SERVICE_REQUEST_FLOWER_DELIVERY = "SRFD";
    public static final String SERVICE_REQUEST_SECURITY = "SRS";
    public static final String SERVICE_REQUEST_TRANSPORT = "SRET";

    public static final String SERVICE_REQUEST_RELIGIOUS_SERVICES = "SRRS";

    public static final String SERVICE_REQUEST_AV_EQUIPMENT = "SRAVE";

    // The starting width and height of the window
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int MIN_WIDTH = 1280;
    private static final int MIN_HEIGHT = 720;

    private static final int WIDTH_POPUP_WARNING = 300;
    private static final int HEIGHT_POPUP_WARNING = 150;

    // Data storage about the stage
    private static Scene rootScene;
    private static BorderPane rootPane;
    protected static Stage primaryStage;
    private static Map<String, Scene> scenes;
    private static Map<String, UIController> sceneControllers;
    private static Map<String, String> sceneFiles;
    private static Map<String, String> sceneTitles;
    private static Map<String, Parent> sceneParents;

    /**
     * Constructor
     */
    public UIController() {

    }

    /**
     * Constructor
     * Sets the primary stage to the given stage and initializes the file lists
     * @param stage
     */
    public UIController(Stage stage) {
        primaryStage = stage;
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        rootPane = new BorderPane();
        rootScene = new Scene(rootPane, WIDTH, HEIGHT);
        primaryStage.setScene(rootScene);
        primaryStage.show();

        setLists();
    }

    /**
     * Function that is run on scene show
     */
    public void onShow() {}

    /**
     * Initializes the lists of titles and fxml files for each scene
     */
    private void setLists() {
        scenes = new HashMap<String, Scene>();
        sceneControllers = new HashMap<String, UIController>();
        sceneFiles = new HashMap<String, String>();
        sceneTitles = new HashMap<String, String>();
        sceneParents = new HashMap<String, Parent>();

        sceneFiles.put(UIController.LOGIN_MAIN, "/application/login_main.fxml");
        sceneTitles.put(UIController.LOGIN_MAIN, "Login Screen");

        sceneFiles.put(UIController.WELCOME_MAIN, "/application/welcome_main.fxml");
        sceneTitles.put(UIController.WELCOME_MAIN, "Welcome Screen");

        sceneFiles.put(UIController.ABOUT_PAGE, "/application/about_page.fxml");
        sceneTitles.put(UIController.ABOUT_PAGE, "About Page");
        // Main Menus
        sceneFiles.put(UIController.GUEST_MAIN_MENU_MAIN, "/guest_main_menu_main.fxml");
        sceneTitles.put(UIController.GUEST_MAIN_MENU_MAIN, "Main Menu");

        sceneFiles.put(UIController.USER_MAIN_MENU_MAIN, "/application/user_main_menu_main.fxml");
        sceneTitles.put(UIController.USER_MAIN_MENU_MAIN, "Main Menu");

        sceneFiles.put(UIController.ADMIN_MAIN_MENU_MAIN, "/admintools/admin_main_menu_main.fxml");
        sceneTitles.put(UIController.ADMIN_MAIN_MENU_MAIN, "Main Menu");

        // Admin Tools
        sceneFiles.put(UIController.ADMIN_TOOLS_MAIN, "/admintools/admin_tools_main.fxml");
        sceneTitles.put(UIController.ADMIN_TOOLS_MAIN, "Admin Tools - Main");

        sceneFiles.put(UIController.ADMIN_TOOLS_VIEW_EDGES, "/admintools/admin_tools_view_edges.fxml");
        sceneTitles.put(UIController.ADMIN_TOOLS_VIEW_EDGES, "Admin Tools - View Edges");

        sceneFiles.put(UIController.ADMIN_TOOLS_VIEW_NODES, "/admintools/admin_tools_view_nodes.fxml");
        sceneTitles.put(UIController.ADMIN_TOOLS_VIEW_NODES, "Admin Tools - View Nodes");

        sceneFiles.put(UIController.ADMIN_TOOLS_VIEW_SERVICE_REQUESTS, "/admintools/admin_tools_view_service_request.fxml");
        sceneTitles.put(UIController.ADMIN_TOOLS_VIEW_SERVICE_REQUESTS, "Admin Tools - View Service Requests");

        sceneFiles.put(UIController.ADMIN_TOOLS_VIEW_USERS, "/admintools/admin_tools_view_users.fxml");
        sceneTitles.put(UIController.ADMIN_TOOLS_VIEW_USERS, "Admin Tools - View Users");

        sceneFiles.put(UIController.ADMIN_TOOLS_CHANGE_ALGORITHM, "/admintools/admin_tools_switch_algorithm.fxml");
        sceneTitles.put(UIController.ADMIN_TOOLS_CHANGE_ALGORITHM, "Admin Tools - Change Algorithm");
        sceneFiles.put((UIController.ADMIN_TOOLS_MAP_VIEW), "/admintools/admin_tools_map_view.fxml");
        sceneTitles.put((UIController.ADMIN_TOOLS_MAP_VIEW), "Admin Tools ; Map View");

        sceneFiles.put(UIController.ADMIN_TOOLS_EDIT_RESERVATIONS, "/admintools/admin_tools_edit_reservations.fxml");
        sceneTitles.put(UIController.ADMIN_TOOLS_EDIT_RESERVATIONS, "Admin Tools - Edit Reservation");




        // Service Request
        sceneFiles.put(UIController.SERVICE_REQUEST_MAIN, "/servicerequests/service_request_main.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_MAIN, "Service Request - Main");

        sceneFiles.put(UIController.SERVICE_REQUEST_AV_EQUIPMENT, "/servicerequests/service_request_audio_visual.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_AV_EQUIPMENT, "Service Request - Audio Visual");

        sceneFiles.put(UIController.SERVICE_REQUEST_BABYSITTING, "/servicerequests/service_request_babysitting.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_BABYSITTING, "Service Request - Babysitting");

        sceneFiles.put(UIController.SERVICE_REQUEST_IT, "/servicerequests/service_request_It.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_IT, "Service Request - IT");

        sceneFiles.put(UIController.SERVICE_REQUEST_PRESCRIPTION_SERVICES_MAIN, "/servicerequests/service_request_prescription_services_main.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_PRESCRIPTION_SERVICES_MAIN, "Service Request - Prescription Services");

        sceneFiles.put(UIController.SERVICE_REQUEST_FLOWER_DELIVERY, "/servicerequests/service_request_flower_delivery.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_FLOWER_DELIVERY, "Service Request - Flower Delivery");


        sceneFiles.put(UIController.SERVICE_REQUEST_SANITATION, "/servicerequests/service_request_sanitation.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_SANITATION, "Service Request - Sanitation");

        sceneFiles.put(UIController.SERVICE_REQUEST_SECURITY, "/servicerequests/service_request_security.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_SECURITY, "Service Request - Security");

        sceneFiles.put(UIController.SERVICE_REQUEST_RELIGIOUS_SERVICES, "/servicerequests/service_request_religious_services.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_RELIGIOUS_SERVICES, "Service Request - Religious Services");

        sceneFiles.put(UIController.SERVICE_REQUEST_INTERPRETER, "/servicerequests/service_request_interpreter.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_INTERPRETER, "Service Request - Interpreter");

        sceneFiles.put(UIController.SERVICE_REQUEST_TRANSPORT, "/servicerequests/service_request_transport.fxml");
        sceneTitles.put(UIController.SERVICE_REQUEST_TRANSPORT, "Service Request - Transport");

        // Reservations
        sceneFiles.put(UIController.RESERVATIONS_MAIN, "/reservations/reservations_main.fxml");
        sceneTitles.put(UIController.RESERVATIONS_MAIN, "Reservations - Main");

        sceneFiles.put(UIController.RESERVATIONS_EDIT, "/reservations/reservations_edit.fxml");
        sceneTitles.put(UIController.RESERVATIONS_EDIT, "Reservations - Edit");

        sceneFiles.put(UIController.RESERVATIONS_MAIN_MENU, "/reservations/reservations_main_menu.fxml");
        sceneTitles.put(UIController.RESERVATIONS_MAIN_MENU, "Reservations - Main Menu");

        sceneFiles.put(UIController.RESERVATIONS_CALENDAR_VIEW, "/reservations/reservations_main_calendar_view.fxml");
        sceneTitles.put(UIController.RESERVATIONS_CALENDAR_VIEW, "Reservations - Calendar View");

        // Pathfinding
        sceneFiles.put(UIController.PATHFINDING_MAIN, "/pathfinding/path_find_main.fxml");
        sceneTitles.put(UIController.PATHFINDING_MAIN, "Path Finding Main");

        // Popups
        sceneFiles.put(UIController.POPUP_DIRECTIONS, "/direction_popup.fxml");
        sceneTitles.put(UIController.POPUP_DIRECTIONS, "Popup Window For Directions");
    }

    /**
     * Switches the primary stage to the given scene
     * If the scene has not yet been created, creates that scene
     * @param sceneString String representation of the scene to swtich to
     * @return The UIController for that particular new scene
     */
    @FXML
    protected UIController goToScene(String sceneString, DayView dayCal) {
        Scene scene = scenes.get(sceneString);


        // If the scene has not yet been created
        if(scene == null) {
            try {
                //FXMLLoader fxmlLoader = new FXMLLoader(new File(System.getProperty("user.dir") + "/resources" + sceneFiles.get(sceneString)).toURI().toURL());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneFiles.get(sceneString)));
                fxmlLoader.setRoot(dayCal);
                Parent root = fxmlLoader.load();
                sceneParents.put(sceneString, root);
                sceneControllers.put(sceneString, fxmlLoader.getController());
                scenes.put(sceneString, new Scene(root, WIDTH, HEIGHT));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        // Show the scene
        primaryStage.setTitle(sceneTitles.get(sceneString));
        rootPane.setCenter(sceneParents.get(sceneString));

        // Run the onShow function and return the controller
        sceneControllers.get(sceneString).onShow();
        return sceneControllers.get(sceneString);
    }


    /**
     * Switches the primary stage to the given scene
     * If the scene has not yet been created, creates that scene
     * @param sceneString String representation of the scene to swtich to
     * @return The UIController for that particular new scene
     */
    @FXML
    protected UIController goToScene(String sceneString) {
        Scene scene = scenes.get(sceneString);


        // If the scene has not yet been created
        if(scene == null) {
            try {
                //FXMLLoader fxmlLoader = new FXMLLoader(new File(System.getProperty("user.dir") + "/resources" + sceneFiles.get(sceneString)).toURI().toURL());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneFiles.get(sceneString)));
                Parent root = fxmlLoader.load();
                sceneParents.put(sceneString, root);
                sceneControllers.put(sceneString, fxmlLoader.getController());
                scenes.put(sceneString, new Scene(root, WIDTH, HEIGHT));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        // Show the scene
        primaryStage.setTitle(sceneTitles.get(sceneString));
        rootPane.setCenter(sceneParents.get(sceneString));

        // Run the onShow function and return the controller
        sceneControllers.get(sceneString).onShow();
        return sceneControllers.get(sceneString);
    }

    /**
     * Pops up a window with the given warning that the user must acknowledge to continue
     * @param message The warning string to dispaly
     */
    @FXML
    public void popupMessage(String message, boolean isWarning) {
        Stage stage = new Stage();

        Image icon = new Image("file:warning.png");
        stage.getIcons().add(icon);

        Scene scene = null;
        UIControllerPUM controller = null;
        try {
            FXMLLoader fxmlLoader = isWarning ? new FXMLLoader(getClass().getResource("/popup_main.fxml")) :  new FXMLLoader(getClass().getResource("/popup_confirm.fxml"));
            Parent root = fxmlLoader.load();
            scene = new Scene(root, WIDTH_POPUP_WARNING, HEIGHT_POPUP_WARNING);
            controller = fxmlLoader.getController();
        } catch(Exception e) {
            e.printStackTrace();
        }

        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        controller.setMessage(message);

        stage.setTitle("Warning");
        stage.setScene(scene);
        stage.showAndWait();
        stage.setAlwaysOnTop(true);


    }

    /**
     * Switches the scene to the application menu
     * Used across all UIControllers
     */
    @FXML
    private void setHomeButton() {
        int permission = CurrentUser.user.getPermissions();
        switch (permission){
            case 1:
                this.goToScene(UIController.SERVICE_REQUEST_MAIN);
                break;
            case 2:
                this.goToScene(UIController.USER_MAIN_MENU_MAIN);
                break;
            case 3:
                this.goToScene(UIController.ADMIN_MAIN_MENU_MAIN);
                break;
            default:
                this.goToScene(UIController.WELCOME_MAIN);
                break;
        }
    }

    @FXML
    private void setBackButton(){
        this.goToScene(UIController.PATHFINDING_MAIN);
    }

    /**
     * Runs the given getter on the giveb object and puts the data in the given label
     * @param object The object to get from
     * @param methodName The method name of the getter
     * @param label The label to put the value into
     */
    public void runStringGetter(Object object, String methodName, Label label) {
        try {
            Method method = object.getClass().getMethod(methodName);
            label.setText("" + method.invoke(object));
        } catch (Exception e) {
            //e.printStackTrace();
            label.setText("");
        }
    }

    /**
     * Runs the given getter on the giveb object and puts the data in the given label and textField
     * @param object The object to get from
     * @param methodName The method name of the getter
     * @param label The label to put the value into
     * @param textField The textField to put the value into
     */
    public void runStringGetterEditable(Object object, String methodName, Label label, TextField textField) {
        try {
            Method method = object.getClass().getMethod(methodName);
            textField.setText("" + method.invoke(object));
            runStringGetter(object, methodName, label);
        } catch (Exception e) {
            //e.printStackTrace();
            textField.setText("");
        }
    }

    /**
     * Runs the setter on the given object and gets the value and class from the givven arguments
     * @param object The object to run the setter on
     * @param methodName The methodName of the setter
     * @param className The class of the argument for the setter
     * @param argument The argument of the given class to set to
     */
    public void runSetter(Object object, String methodName, Class className, Object argument) {
        try {
            Method method = object.getClass().getMethod(methodName, className);
            method.invoke(object, argument);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * An object that extends TableCell and allows String edits to the cell
     * @param <T> The object that is being displayed in the TableView
     * @param <S> The object that is being displayed in the TableView
     */
    protected class EditableTextCell<T, S> extends TableCell<T, S> {
        protected JFXTextField textField = new JFXTextField(); /**< The Textfield to edit*/
        protected Label label = new Label(); /**< The Label to display*/
        protected TableColumn column; /**< The column that the cell is in, used for width properties*/
        protected int index; /**< The Column index, used for per column commands*/

        /**
         * Constructor
         * Construts with the column and index
         * @param tableColumn The TableColumn that this cell is in
         * @param indexOut The index of the column
         */
        public EditableTextCell(TableColumn<T, S> tableColumn, int indexOut) {
            this.column = tableColumn;
            this.index = indexOut;
        }

        /**
         * Updates the object when edits are made
         * @param s The object
         * @param empty Whether the cell is currently empty
         */
        @Override
        protected void updateItem(S s, boolean empty) {
            super.updateItem(s, empty);
            if (s == null) {
                return;
            }
            // Expand elements to the entire cell width
            textField.prefWidthProperty().bind(column.prefWidthProperty());
            label.prefWidthProperty().bind(column.prefWidthProperty());

            setGraphic(label);
            // When user leaves textField, switch to label and discard changes
            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(!newValue) {
                        setGraphic(label);
                        textField.setText(label.getText());
                    }
                }
            });

            // When the user double clicks the label switch to textField
            label.setOnMouseClicked(et -> {
                if(et.getClickCount() == 2) {
                    setGraphic(textField);
                    textField.setText(label.getText());
                    textField.positionCaret(label.getText().length() - 1);
                }
            });

        }
    }
}
