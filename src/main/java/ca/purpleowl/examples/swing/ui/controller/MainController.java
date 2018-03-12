package ca.purpleowl.examples.swing.ui.controller;

import ca.purpleowl.examples.swing.ui.view.MainView;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;

@Controller
public class MainController extends JFrame {
    private final MainView view;

    public MainController() {
        this.view = new MainView();

        this.setContentPane(view.$$$getRootComponent$$$());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }
}
