package com.example.idiom.frontend;

import com.example.idiom.controller.IdiomController;
import com.example.idiom.model.idiom.Idiom;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route
public class IdiomView extends VerticalLayout {

    private final IdiomController idiomController;
    private List<Idiom> idiomList = new ArrayList<>();
    private final Grid<Idiom> idiomGrid = new Grid<>(Idiom.class);
    private final Button downloadButton = new Button("Download");
    private final Button getIdiomFromDBButton = new Button("getIdiomFromDB");
    private final Select<String> downloadSelector = new Select<>();

    HorizontalLayout horizontalLayout = new HorizontalLayout(downloadSelector, downloadButton, getIdiomFromDBButton);


    public IdiomView(IdiomController idiomController) {
        this.idiomController = idiomController;
        configureGrid();
        configureButton();
        configuregetIdiomFromDBButton();
        configureTriggers();
        configureHorizontalLayout();
        add(idiomGrid, horizontalLayout);
    }

    private Component configuregetIdiomFromDBButton() {
        getIdiomFromDBButton.addClickListener((clickEvent) -> {
            List<Idiom> idioms=idiomController.getIdiomsFromDB();
            idiomList.addAll(idioms);
            getIdiomFromDBButton.setText("GetFromDB");
            configureGrid();
        });
        return getIdiomFromDBButton;
    }


    private void configureHorizontalLayout() {
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.addClassName("backColorGrey");
    }

    private void configureTriggers() {
//        selectDownload.setLabel("Choose to download");
        downloadSelector.setItems("phrasal", "idiom");
    }

    private Component configureButton() {
        downloadButton.addClickListener((clickEvent) -> {
            addWordsToDb();
            downloadButton.setText("Download");
            configureGrid();
        });
        return downloadButton;
    }

    private Grid<Idiom> configureGrid() {
        idiomGrid.setClassName("contact-grid");
        idiomGrid.setItems(idiomList);
        idiomGrid.setColumnOrder(
                idiomGrid.getColumnByKey("id"),
                idiomGrid.getColumnByKey("polishMeaning"),
                idiomGrid.getColumnByKey("englishMeaning"),
                idiomGrid.getColumnByKey("englishExample"),
                idiomGrid.getColumnByKey("audioTranslateLink"),
                idiomGrid.getColumnByKey("audioExampleLink"),
                idiomGrid.getColumnByKey("linkToIdiom")
        );
        return idiomGrid;
    }

    private void addWordsToDb() {
        if (idiomList.isEmpty()) {
            idiomList.addAll(idiomController.addIdiomsToDB());
        }
    }


}
