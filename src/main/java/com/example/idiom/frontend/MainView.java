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
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
@PageTitle("PhrasalVerb and Idiom Downloader")
public class MainView extends AppLayout {
    Tabs primaryNavigation = getPrimaryNavigation();
    H2 navigatorSubtitle = new H2("Navigator");


    public MainView() {

        DrawerToggle toggle = new DrawerToggle();
        HorizontalLayout wrapper = new HorizontalLayout(toggle, navigatorSubtitle);

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
        addToDrawer(appTitle, primaryNavigation);
        setPrimarySection(Section.DRAWER);

    }


    private Tabs getPrimaryNavigation() {
        RouterLink idiomLink = new RouterLink("Idiom", IdiomView.class);
        idiomLink.add(VaadinIcon.BOOK.create());
        Tab idiomTab = new Tab(idiomLink);


        RouterLink phrasalVerbLink = new RouterLink("PhrasalVerb", PhrasalView.class);
        phrasalVerbLink.add(VaadinIcon.BOOK.create());
        Tab phrasalVerbTab = new Tab(phrasalVerbLink);


        RouterLink testLink = new RouterLink("Test", TestView.class);
        testLink.add(VaadinIcon.TRAIN.create());
        Tab testTab = new Tab(testLink);


        Tabs navigationList = new Tabs();
        navigationList.add(idiomTab, phrasalVerbTab, testTab);
        navigationList.setOrientation(Tabs.Orientation.VERTICAL);
        return navigationList;
    }


}
