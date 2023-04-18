package com.example.idiom.frontend;

import com.example.idiom.controller.IdiomController;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("PhrasalVerb and Idiom Downloader")
public class MainView extends AppLayout {
    private final IdiomController idiomController;

    public MainView(IdiomController idiomController) {
        this.idiomController = idiomController;

        DrawerToggle toggle = new DrawerToggle();
        Tabs views = getPrimaryNavigation();
        views.addSelectedChangeListener((selectedChangeEvent)->{
            System.out.println(views.getSelectedTab().getLabel());
        });

        H2 subTitle = new H2("Idioms");
        subTitle.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        HorizontalLayout wrapper = new HorizontalLayout(toggle, subTitle);
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setSpacing(false);
        Component idiomView = configureIdiomView();
        VerticalLayout viewHeader = new VerticalLayout(wrapper,idiomView);

        viewHeader.setPadding(false);
        viewHeader.setSpacing(false);

        H1 appTitle = new H1("Word downloader");
        appTitle.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("line-height", "var(--lumo-size-l)")
                .set("margin", "0 var(--lumo-space-m)")
                .set("color", "red");
        addToNavbar(viewHeader);
        addToDrawer(appTitle, views);
        setPrimarySection(Section.DRAWER);

    }


    private Component configureIdiomView() {
        IdiomView idiomView=new IdiomView(idiomController);
        return idiomView;
    }

    private Tabs getPrimaryNavigation() {

        Tabs tabs = new Tabs(
                (new Tab(VaadinIcon.NOTEBOOK.create(), new Span("Idiom"))),
                (new Tab(VaadinIcon.NOTEBOOK.create(), new Span("PhrasalVerb"))),
                (new Tab(VaadinIcon.COG.create(), new Span("Settings")))
        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }


}
