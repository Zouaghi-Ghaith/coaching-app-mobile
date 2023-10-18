/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.entities.Coach;
import com.mycompany.services.CoachService;

/**
 *
 * @author ilyes
 */
public class EditCoachForm extends Form{
    private TextField nomField;
    private TextField prenomField;
    private TextField ageField;
    private TextField emailField;
    private TextField specialiteField;
    private Button editButton;
    private Coach coach;

    public EditCoachForm(Coach coach) {
        super("Edit Coach");
        this.coach = coach;
        
        nomField = new TextField(coach.getNom(), "Nom", 20, TextField.ANY);
        prenomField = new TextField(coach.getPrenom(), "Prenom", 20, TextField.ANY);
        ageField = new TextField(String.valueOf(coach.getAge()), "Age", 3, TextField.NUMERIC);
        emailField = new TextField(coach.getEmail(), "Email", 50, TextField.EMAILADDR);
        specialiteField = new TextField(coach.getSpecialite(), "Specialite", 50, TextField.ANY);
        
        
            // Initialize edit button
    editButton = new Button("Edit");
        
        // Add the input fields to the form
        add(nomField);
        add(prenomField);
        add(ageField);
        add(emailField);
        add(specialiteField);
        add(editButton);
        
        Button updateButton = new Button("Update");
        updateButton.addActionListener(e -> {
            // When the button is clicked, validate the input fields and update the coach on the server
            if (validateInputFields()) {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                int age = Integer.parseInt(ageField.getText());
                String email = emailField.getText();
                String specialite = specialiteField.getText();
                coach.setNom(nom);
                coach.setPrenom(prenom);
                coach.setAge(age);
                coach.setEmail(email);
                coach.setSpecialite(specialite);
                boolean updated = CoachService.getInstance().editCoach(coach);
                //if (updated) {
                    Dialog.show("Success", "Coach updated successfully", "OK", null);
                    // Close the form
                   // this.close();
                   new CoachListForm().show();
               // } else {
                //    Dialog.show("Error", "Failed to update coach", "OK", null);
                //}
            }
        });
        
          // Add input validation to form fields
    Validator val = new Validator();
   
    val.addSubmitButtons(editButton);
        add(updateButton);
        
        // Add a return button to go back to the previous form
        Button returnBtn = new Button("Return");
        returnBtn.addActionListener(e -> {
            // Go back to the previous form
            this.showBack();
            new CoachListForm().show();
        });
        add(returnBtn);
        
        
        
        
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

    //    if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
    //        Dialog.show("Error", "Invalid email address", "OK", null);
    //        return false;
    //    }

        return true;
    }
    
    
}
