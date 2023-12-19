module edu.usd {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    exports edu.usd to javafx.graphics, javafx.fxml;

    exports edu.usd.GradeService to javafx.fxml;
    opens edu.usd.GradeService to javafx.fxml;

    exports edu.usd.PhysicsService to javafx.fxml;
    opens edu.usd.PhysicsService to javafx.fxml;

    exports edu.usd.FinanceService to javafx.fxml;
    opens edu.usd.FinanceService to javafx.fxml;

    exports edu.usd.ArithmeticService to javafx.fxml;
    opens edu.usd.ArithmeticService to javafx.fxml;

}