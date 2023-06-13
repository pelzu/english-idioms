package com.example.idiom.frontend;

import com.example.idiom.controller.TranslateController;
import com.example.idiom.model.idiom.Idiom;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
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
    private final Button downloadIdiomButton = new Button("Download Idiom");
    private final Button deleteIdiomsButton = new Button("Delete Idiom List");

    private final HorizontalLayout buttonSegment = new HorizontalLayout();

    private final TextArea message = new TextArea();

    public IdiomView(TranslateController translateController) {
        this.translateController = translateController;
//        fillTab();
        configureGrid();
        configureDownloadButton();
        configureIdiomsDeleteButton();
        configureButtonSegment();
        add(idiomsGrid, buttonSegment);
    }

    private void configureButtonSegment() {
        buttonSegment.add(downloadIdiomButton, deleteIdiomsButton, message);
    }

    private void configureIdiomsDeleteButton() {

        deleteIdiomsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        deleteIdiomsButton.addClickListener(clickEvent -> {
            clearTab();
            configureGrid();
        });
    }

    private void configureDownloadButton() {
        downloadIdiomButton.addClickListener(event -> {
            fillTab();
            configureGrid();
        });
    }

    private void fillTab() {
        List<Idiom> idioms = translateController.getIdiomsFromDB();
        if (idioms.isEmpty()) {
            idioms = translateController.grabIdiomsToDB();
        }
        this.idioms.addAll(idioms);
    }

    private void clearTab() {
        idioms.clear();
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
        idiomsGrid.addItemClickListener(idiomItemClickEvent -> {
            configureMessage(idiomItemClickEvent.getItem().getPolishMeaning());
            

        });
        return idiomsGrid;
    }

    private void configureMessage(String popup) {
        message.setValue(popup);
    }

}
