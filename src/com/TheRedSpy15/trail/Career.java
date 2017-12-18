package com.TheRedSpy15.trail;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Career {

    protected void careerPicker() throws IOException {

        // Career scene
        // assigns career scene fxml file to career anchor object
        Main.setCareerAnchor(FXMLLoader.load(getClass().getResource("CareerScene.fxml")));
    }
}
