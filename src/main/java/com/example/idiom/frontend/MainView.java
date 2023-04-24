package com.example.idiom.frontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
@PageTitle("PhrasalVerb and Idiom Downloader")
public class MainView extends AppLayout {

    public MainView() {


        DrawerToggle toggle = new DrawerToggle();

        VerticalLayout navigationBar = getPrimaryNavigation();


        H2 subTitle = new H2("Navigator");
        subTitle.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        HorizontalLayout wrapper = new HorizontalLayout(toggle, subTitle);
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setSpacing(false);
        VerticalLayout viewHeader = new VerticalLayout(wrapper);

        viewHeader.setPadding(false);
        viewHeader.setSpacing(false);

        H1 appTitle = new H1("Idiom&Phrasal");
        appTitle.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("line-height", "var(--lumo-size-l)")
                .set("margin", "0 var(--lumo-space-m)");
        addToNavbar(viewHeader);
        addToDrawer(appTitle, navigationBar);
        setPrimarySection(Section.DRAWER);

    }


    private VerticalLayout getPrimaryNavigation() {


        VerticalLayout navigationList = new VerticalLayout(
                new Tab(VaadinIcon.ARCHIVES.create(), new RouterLink("idiom", IdiomView.class)),
                new Tab(VaadinIcon.ARCHIVE.create(), new RouterLink("phrasalVerb", PhrasalView.class)),
                new Tab(VaadinIcon.TERMINAL.create(), new RouterLink("test", TestView.class))
        );


        return navigationList;
    }


}
