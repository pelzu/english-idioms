package com.example.idiom.frontend;

import com.example.idiom.controller.IdiomController;
import com.example.idiom.model.phrasal.PhrasalVerb;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;

import java.util.ArrayList;
import java.util.List;


public class PhrasalView extends VerticalLayout {

    private final IdiomController idiomController;
    private List<PhrasalVerb> phrasalVerbs = new ArrayList<>();
    private final Select<String> downloadSelector = new Select<>();
    private final Grid<PhrasalVerb> phrasalVerbGrid = new Grid<>(PhrasalVerb.class);
    private final Button downloadButton = new Button("Download");
    private final HorizontalLayout horizontalLayout = new HorizontalLayout(downloadSelector, downloadButton);

    public PhrasalView(IdiomController idiomController) {
        this.idiomController = idiomController;
        configureGrid();
        configureButton();
        configureTriggers();
        configureHorizontalLayout();
        add(phrasalVerbGrid, horizontalLayout);
    }



    private void configureHorizontalLayout() {
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout.addClassName("backColorGrey");
    }

    private void configureTriggers() {
//        selectDownload.setLabel("Choose to download");
        downloadSelector.setItems("phrasal", "idiom");
    }

    private Component configureButton() {
        downloadButton.addClickListener((clickEvent) -> {
            addWordsToDb();
            downloadButton.setText("Downloading...");
            configureGrid();
        });
        return downloadButton;
    }

    private Grid<PhrasalVerb> configureGrid() {
        phrasalVerbGrid.setClassName("contact-grid");
        phrasalVerbGrid.setItems(phrasalVerbs);
        phrasalVerbGrid.setColumnOrder(
                phrasalVerbGrid.getColumnByKey("id"),
                phrasalVerbGrid.getColumnByKey("polishMeaning"),
                phrasalVerbGrid.getColumnByKey("englishMeaning"),
                phrasalVerbGrid.getColumnByKey("englishExample"),
                phrasalVerbGrid.getColumnByKey("linkToPhrasalVerb")
        );
        return phrasalVerbGrid;
    }

    private void addWordsToDb() {
        if (phrasalVerbs.isEmpty()) {
            phrasalVerbs.addAll(idiomController.downloadPhrasalVerbs());
        }
    }
}
