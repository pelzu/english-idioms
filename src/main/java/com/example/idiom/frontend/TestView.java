package com.example.idiom.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "test", layout =MainView.class)
@PageTitle("Strona Testowa")
public class TestView extends HorizontalLayout {
    private Button endButton =new Button("Orzeszki") ;
    private H1 header = new H1("Misio");

    private Tabs primaryView=new Tabs();
    public TestView() {
        configurebutton() ;
        primaryView=getPrimaryNavigation();

       add(header,endButton,primaryView);
    }

    private void configurebutton() {
        endButton.setText("End button");
    endButton.addClickListener(event -> {
        header.setText(String.valueOf(primaryView.getSelectedIndex()));
        System.out.println(primaryView.getSelectedTab().getLabel());
        endButton.setText("StartButton");
    });
    }
    private Tabs getPrimaryNavigation() {
        Tab conditional= new Tab(VaadinIcon.CIRCLE.create(),new Span("Conditional"));


        Tabs tabs = new Tabs(
                (new Tab(VaadinIcon.NOTEBOOK.create(), new Span("Idiom"))),
                (new Tab(VaadinIcon.NOTEBOOK.create(), new Span("PhrasalVerb"))),
                (new Tab(VaadinIcon.COG.create(), new Span("Settings"))),
                conditional
        );

        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        return tabs;
    }


}
