/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Form;import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Coach;
import com.mycompany.entities.Plan;
import com.mycompany.services.CoachService;
import com.mycompany.services.PlanService;
import java.util.ArrayList;




/**
 *
 * @author zouag
 */
public class PlanListForm extends Form {

    private ArrayList<Plan> plans;

    public PlanListForm(Coach coach) {
        super("Plans List");

        // Get all the coaches from the server
        plans = PlanService.getInstance().getAllPlans(coach.getId()); // a  modifier (1 coach)

        // Create a container to hold the coaches
        Container planContainer = new Container();
        planContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        planContainer.setScrollableY(true); 
        
        
        // Add a button to add a new category
   
        // Add each coach to the container with buttons to edit and delete it
        for (Plan plan : plans) {
            // Create a container to hold the coach's information and buttons
            Container planRow = new Container(new BorderLayout());
            planRow.setUIID("CoachBox");

            // Create labels to display the coach's information
            Label nomLabel = new Label("Nom: " + plan.getNom());
            nomLabel.setUIID("PlanLabel");
            Label descriptionLabel = new Label("Description: " + plan.getDescription());
            descriptionLabel.setUIID("PlanLabel");
            Label nbParticipantLabel = new Label("Nombre de participants: " + plan.getNbParticipant());
            nbParticipantLabel.setUIID("PlanLabel");
            Label nombreDeSeancesLabel = new Label("Nombre de séances: " + plan.getNombre_de_seances());
            nombreDeSeancesLabel.setUIID("PlanLabel");
            
            // Create buttons to edit and delete the coach
            Button editBtn = new Button("Edit");
            editBtn.addActionListener(e -> {
               // new EditCoachForm.show();
               new EditPlanForm(coach, plan).show();
            });
            Button deleteBtn = new Button("Delete");
            deleteBtn.addActionListener(e -> {
                // Delete the coach from the server
               // CoachService.getInstance().deleteCoach(plan.getId());
               PlanService.getInstance().deletePlan(plan.getId());
                // Remove the coach from the container
                planContainer.removeComponent(planRow);
            });

            // Add the labels to the coach row
            Container labelsContainer = new Container(new GridLayout(6, 1));
            labelsContainer.add(nomLabel);
            labelsContainer.add(descriptionLabel);
            labelsContainer.add(nbParticipantLabel);
            labelsContainer.add(nombreDeSeancesLabel);

            planRow.add(BorderLayout.CENTER, labelsContainer);

            // Create a container to hold the buttons
            Container buttonsContainer = new Container(new GridLayout(3, 1));
            buttonsContainer.setUIID("CoachButtonBox");
         //   buttonsContainer.add(showPlansBtn);
            buttonsContainer.add(editBtn);
            buttonsContainer.add(deleteBtn);

            planRow.add(BorderLayout.EAST, buttonsContainer);

            // Add the coach row to the container
            planContainer.add(planRow);
        }

        // Add the container to the form
        add(planContainer);

        // Add a button to add a new coach
        Button addBtn = new Button("Add Plan");
        addBtn.addActionListener(e -> {
            new AddPlanForm(coach).show();
        });
        add(addBtn);
        

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
