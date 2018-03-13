package ca.purpleowl.examples.swing;

import ca.purpleowl.examples.swing.ui.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * This CommandLineRunner fires off at runtime and boots up our GUI.
 */
@Component
public class SpringBootSwingCommandLineRunner implements CommandLineRunner {
    private final MainController controller;

    @Autowired
    public SpringBootSwingCommandLineRunner(MainController controller) {
        this.controller = controller;
    }


    @Override
    public void run(String... args) {
        //This boots up the GUI.
        EventQueue.invokeLater(() -> controller.setVisible(true));
    }
}
