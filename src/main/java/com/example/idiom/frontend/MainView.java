package com.example.idiom.frontend;

import com.example.idiom.controller.IdiomController;
import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.repository.idiom.IdiomDBService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private final IdiomDBService idiomDBService;
    private final IdiomController idiomController;
    Grid<Idiom> idiomGrid = new Grid<>(Idiom.class);
    Button mainButton = new Button("Download");
    private List<Idiom> idiomList = new ArrayList<>();


    public MainView(IdiomDBService idiomDBService, IdiomController idiomController) {
        this.idiomDBService = idiomDBService;
        this.idiomController = idiomController;
        configureGrid();
        configureButton();

        add(idiomGrid, mainButton);

    }

    private Component configureButton() {

        mainButton.addClickListener((clickEvent) -> {
            configureGrid();
        });
        return mainButton;
    }

    private Grid<Idiom> configureGrid() {
        idiomController.getPhrase("idiom", "false", "false");
        getIdiomList();
        idiomGrid.setClassName("contact-grid");
        idiomGrid.setItems(idiomList);

        return idiomGrid;
    }

    private void getIdiomList() {
        if (idiomList.isEmpty()) {
            idiomList = idiomDBService.getIdiomListFromDB();
        }
    }


}
