module edu.usd {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens edu.usd to javafx.fxml;
    exports edu.usd.GradeService to javafx.fxml;
    opens edu.usd.GradeService to javafx.fxml;
    exports edu.usd;
}