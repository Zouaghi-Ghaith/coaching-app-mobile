/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.entities.Coach;
import com.mycompany.services.CoachService;
//import java.util.regex.Matcher;

/**
 *
 * @author zouag
 */
public class AddCoachForm extends Form {
    private TextField nomField;
    private TextField prenomField;
    private TextField ageField;
    private TextField emailField;
    private TextField specialiteField;

    public AddCoachForm() {
        super("Add Coach");

        // Create the input fields for the coach's information
        nomField = new TextField("", "Nom", 20, TextField.ANY);
        prenomField = new TextField("", "Prenom", 20, TextField.ANY);
        ageField = new TextField("", "Age", 3, TextField.NUMERIC);
        emailField = new TextField("", "Email", 50, TextField.EMAILADDR);
        specialiteField = new TextField("", "Specialite", 50, TextField.ANY);

        // Add the input fields to the form
        add(nomField);
        add(prenomField);
        add(ageField);
        add(emailField);
        add(specialiteField);

        // Add a button to submit the new coach's information
        Button addButton = new Button("Add");
        addButton.addActionListener(e -> {
            // When the button is clicked, validate the input fields and add the new coach to the server
            if (validateInputFields()) {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                int age = Integer.parseInt(ageField.getText());
                String email = emailField.getText();
                String specialite = specialiteField.getText();
                Coach coach = new Coach(age,nom, prenom, email, specialite);
                boolean added = CoachService.getInstance().addCoach(coach);
                if (added) {
                    Dialog.show("Success", "Coach added successfully", "OK", null);
                    // Clear the input fields
                    new CoachListForm().show();
                } else {
                    Dialog.show("Error", "Failed to add coach", "OK", null);
                }
            }
        });
        add(addButton);
    }

    private boolean validateInputFields() {
    String nom = nomField.getText();
    String prenom = prenomField.getText();
    String ageStr = ageField.getText();
    String email = emailField.getText();
    String specialite = specialiteField.getText();

    if (nom.isEmpty() || prenom.isEmpty() || ageStr.isEmpty() || email.isEmpty() || specialite.isEmpty()) {
        Dialog.show("Error", "All fields are required", "OK", null);
        return false;
    }

    int age;
    try {
        age = Integer.parseInt(ageStr);
        if (age <= 0) {
            Dialog.show("Error", "Age must be a positive integer", "OK", null);
            return false;
        }
    } catch (NumberFormatException e) {
        Dialog.show("Error", "Age must be a positive integer", "OK", null);
        return false;
    }

    //email.matches(prenom);
    //Matcher.quoteReplacement(nom)
    //  if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
    //   Dialog.show("Error", "Invalid email address", "OK", null);
    //    return false;
    //}

    return true;
}

}
