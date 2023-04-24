package com.example.idiom.frontend;

import com.example.idiom.controller.TranslateController;
import com.example.idiom.model.idiom.Idiom;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "idiom", layout = MainView.class)
@PageTitle("Welcome to Idiom downloader")
public class IdiomView extends VerticalLayout {

    private final TranslateController translateController;
    private final List<Idiom> idioms = new ArrayList<>();
    private final Grid<Idiom> idiomsGrid = new Grid<>(Idiom.class);

    public IdiomView(TranslateController translateController) {
        this.translateController = translateController;
        fillTab();
        configureGrid();
        add(idiomsGrid);
    }

    private void fillTab() {
        List<Idiom> idioms = translateController.getIdiomsFromDB();
        if (idioms.isEmpty()) {
            idioms = translateController.grabIdiomsToDB();
        }
        this.idioms.addAll(idioms);
    }

    private Grid<Idiom> configureGrid() {
        idiomsGrid.setClassName("contact-grid");
        idiomsGrid.setItems(idioms);
        idiomsGrid.setColumnOrder(
                idiomsGrid.getColumnByKey("id"),
                idiomsGrid.getColumnByKey("polishMeaning"),
                idiomsGrid.getColumnByKey("englishMeaning"),
                idiomsGrid.getColumnByKey("englishExample"),
                idiomsGrid.getColumnByKey("audioTranslateLink"),
                idiomsGrid.getColumnByKey("audioExampleLink"),
                idiomsGrid.getColumnByKey("linkToIdiom")
        );
        idiomsGrid.setHeight(20, Unit.CM);
        return idiomsGrid;
    }

}
