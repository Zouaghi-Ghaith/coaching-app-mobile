/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.entities.Coach;
import com.mycompany.entities.Plan;
import com.mycompany.services.PlanService;

/**
 *
 * @author ghaith
 */
public class AddPlanForm extends Form {

    private TextField nomField, descriptionField, nbParticipantField, nombreDeSeancesField;
    private Button addButton;

    public AddPlanForm(Coach coach) {
        super("Add Plan", BoxLayout.y());
       //     super('Add',)
//super('Add Plan')
        
       // super("Add Plan", BoxLayout.y());

        // Initialize form fields
        nomField = new TextField("", "Nom", 20, TextField.ANY);
        descriptionField = new TextField("", "Description", 20, TextField.ANY);
        nbParticipantField = new TextField("", "Nombre de participants", 20, TextField.NUMERIC);
        nombreDeSeancesField = new TextField("", "Nombre de séances", 20, TextField.NUMERIC);

        // Initialize add button
        addButton = new Button("Add");

        // Add input validation to form fields
        Validator val = new Validator();
        val.addConstraint(nbParticipantField, new NumericConstraint(true));
        val.addConstraint(nombreDeSeancesField, new NumericConstraint(true));
        val.addSubmitButtons(addButton);

        // Add components to form
        add(nomField);
        add(descriptionField);
        add(nbParticipantField);
        add(nombreDeSeancesField);
        add(addButton);

        // Add button action listener to add plan to server
        addButton.addActionListener(e -> {
            if (val.isValid()) {
                int nbParticipant = Integer.parseInt(nbParticipantField.getText());
                int nombreDeSeances = Integer.parseInt(nombreDeSeancesField.getText());
                String nom = nomField.getText();
                String description = descriptionField.getText();

                Plan plan = new Plan(0, nbParticipant, nombreDeSeances, nom, description);
                plan.setCoachId(coach.getId());
                PlanService.getInstance().addPlan(plan);

                // Show confirmation dialog and go back to previous form
                Dialog.show("Success", "Plan added successfully", "OK", null);
                new PlanListForm(coach).show();
            }
        });
        
         // Add a return button to go back to the previous form
        Button returnBtn = new Button("Return");
        returnBtn.addActionListener(e -> {
            // Go back to the previous form
            this.showBack();
            new CoachListForm().show();
        });
        add(returnBtn);
    
        
      
    }
    
  
         
    

    
}

