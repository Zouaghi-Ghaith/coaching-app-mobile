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
public class CoachListForm extends Form {
    

    private ArrayList<Coach> coaches;

    public CoachListForm() {
        super("Coach List");
        
    

        
        // Get all the coaches from the server
        coaches = CoachService.getInstance().getAllCoaches();

        // Create a container to hold the coaches
        Container coachContainer = new Container();
        coachContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        coachContainer.setScrollableY(true);

        // Add each coach to the container with buttons to edit and delete it
        for (Coach coach : coaches) {
            // Create a container to hold the coach's information and buttons
            Container coachRow = new Container(new BorderLayout());
            coachRow.setUIID("CoachBox");

            // Create labels to display the coach's information
            Label nomLabel = new Label( coach.getNom()+" "+coach.getPrenom()+" ("+coach.getAge()+") ");
            nomLabel.setUIID("CoachLabel");
            Label emailLabel = new Label("Email: " + coach.getEmail());
            emailLabel.setUIID("CoachLabel");
            Label specialiteLabel = new Label("Spécialité: " + coach.getSpecialite());
            specialiteLabel.setUIID("CoachLabel");
            
            // Create button to show plans
            Button showPlansBtn = new Button("Show Plans");
            showPlansBtn.setUIID("ShowPlansButton");
            showPlansBtn.addActionListener(e -> {
                // Show the plans for the selected coach
                new PlanListForm(coach).show();
            });

            // Create buttons to edit and delete the coach
            Button editBtn = new Button("Edit");
            editBtn.addActionListener(e -> {
                new EditCoachForm(coach).show();
            });
            Button deleteBtn = new Button("Delete");
            deleteBtn.addActionListener(e -> {
                // Delete the coach from the server
                CoachService.getInstance().deleteCoach(coach.getId());

                // Remove the coach from the container
                coachContainer.removeComponent(coachRow);
            });

            // Add the labels to the coach row
            Container labelsContainer = new Container(new GridLayout(6, 1));
            labelsContainer.add(nomLabel);
            labelsContainer.add(emailLabel);
            labelsContainer.add(specialiteLabel);

            coachRow.add(BorderLayout.CENTER, labelsContainer);

            // Create a container to hold the buttons
            Container buttonsContainer = new Container(new GridLayout(3, 1));
            buttonsContainer.setUIID("CoachButtonBox");
            buttonsContainer.add(showPlansBtn);
            buttonsContainer.add(editBtn);
            buttonsContainer.add(deleteBtn);

            coachRow.add(BorderLayout.EAST, buttonsContainer);

            // Add the coach row to the container
            coachContainer.add(coachRow);
        }

        // Add the container to the form
        add(coachContainer);

        // Add a button to add a new coach
        Button addBtn = new Button("Add Coach");
        addBtn.addActionListener(e -> {
            new AddCoachForm().show();
        });
        add(addBtn);

        // Add a return button to go back to the previous form
        Button returnBtn = new Button("Return");
        returnBtn.addActionListener(e -> {
            // Go back to the previous form
            this.showBack();
        });
        Toolbar toolbar = new Toolbar();
      //  toolbar.addCommandToLeftBar(returnBtn);
        setToolbar(toolbar);
    }
}
