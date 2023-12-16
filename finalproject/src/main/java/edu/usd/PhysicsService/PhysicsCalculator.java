public abstract class PhysicsCalculator extends Calculator {

    public abstract boolean validateInputs(String[] inputs);

    public abstract String[] getInputs();

    public abstract String getOption();

    public abstract void setValues(String[] inputs, String option);
    
    public abstract double calculate(String[] inputs, String option);

    @Override
    public abstract double calculate();

    @Override
    public void saveResult(String filename, String data) {
    }

    @Override
    public void printResult(String data) {
    }
}
