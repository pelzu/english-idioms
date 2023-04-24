package com.example.idiom.frontend;

import com.example.idiom.controller.TranslateController;
import com.example.idiom.model.phrasal.PhrasalVerb;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "phrasalVerb", layout = MainView.class)
@PageTitle("Phrasal Verbs")
public class PhrasalView extends VerticalLayout {

    private final TranslateController translateController;
    private final List<PhrasalVerb> phrasalVerbs = new ArrayList<>();
    private final Grid<PhrasalVerb> phrasalVerbGrid = new Grid<>(PhrasalVerb.class);


    public PhrasalView(TranslateController translateController) {
        this.translateController = translateController;
        fillTab();
        configureGrid();
        add(phrasalVerbGrid);
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
        phrasalVerbGrid.setHeight(20, Unit.CM);

        return phrasalVerbGrid;

    }

    private void fillTab() {
        List<PhrasalVerb> phrasalVerbsDbOrDownload = translateController.getPhrasalsFromDB();
        if (phrasalVerbsDbOrDownload.isEmpty()) {
            phrasalVerbsDbOrDownload = translateController.grabPhrasalsToDb();
        }
        this.phrasalVerbs.addAll(phrasalVerbsDbOrDownload);
    }
}
