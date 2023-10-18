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
 * @author ilyes
 */
public class EditPlanForm extends Form{
    private TextField nomField, descriptionField, nbParticipantField, nombreDeSeancesField;
private Button editButton;
private Plan plan;

public EditPlanForm(Coach coach, Plan plan) {
    super("Edit Plan", BoxLayout.y());
    this.plan = plan;

    // Initialize form fields with plan details
    nomField = new TextField(plan.getNom(), "Nom", 20, TextField.ANY);
    descriptionField = new TextField(plan.getDescription(), "Description", 20, TextField.ANY);
    nbParticipantField = new TextField(String.valueOf(plan.getNbParticipant()), "Nombre de participants", 20, TextField.NUMERIC);
    nombreDeSeancesField = new TextField(String.valueOf(plan.getNombre_de_seances()), "Nombre de séances", 20, TextField.NUMERIC);

    // Initialize edit button
    editButton = new Button("Edit");

    // Add input validation to form fields
    Validator val = new Validator();
    val.addConstraint(nbParticipantField, new NumericConstraint(true));
    val.addConstraint(nombreDeSeancesField, new NumericConstraint(true));
    val.addSubmitButtons(editButton);

    // Add components to form
    add(nomField);
    add(descriptionField);
    add(nbParticipantField);
    add(nombreDeSeancesField);
    add(editButton);

    // Add button action listener to edit plan on server
    editButton.addActionListener(e -> {
        if (val.isValid()) {
            int nbParticipant = Integer.parseInt(nbParticipantField.getText());
            int nombreDeSeances = Integer.parseInt(nombreDeSeancesField.getText());
            String nom = nomField.getText();
            String description = descriptionField.getText();

            plan.setNbParticipant(nbParticipant);
            plan.setNombre_de_seances(nombreDeSeances);
            plan.setNom(nom);
            plan.setDescription(description);
            PlanService.getInstance().editPlan(plan);

            // Show confirmation dialog and go back to previous form
            Dialog.show("Success", "Plan updated successfully", "OK", null);
            this.showBack();
            new PlanListForm(coach).show();
        }
    });
}





}
