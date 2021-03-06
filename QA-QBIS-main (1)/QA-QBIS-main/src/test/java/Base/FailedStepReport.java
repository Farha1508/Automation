package Base;


import io.cucumber.core.plugin.JSONFormatter;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestStepFinished;

public class FailedStepReport implements ConcurrentEventListener {
    private final CleanAppendable out;
    private JSONFormatter test;

    public FailedStepReport(Appendable out) {
        this.out = new CleanAppendable(out);
    }

    private void stepResult(TestStepFinished step) {
        String result = step.getResult().getStatus().toString();
        if(step.getResult().getStatus().toString().equals("FAILED")) {
            if(step.getTestStep() instanceof PickleStepTestStep) {
                PickleStepTestStep testStep = (PickleStepTestStep)step.getTestStep();
                out.println("Step name: "+testStep.getStep().getText());
                out.println("Status is: " + result);
                BaseUtil.testRailStatus = "Test failed at step: "+"\n"+testStep.getStep().getText()+"\n"+
                "With error: "+"\n"+step.getResult().getError();
            }

        }
    }

    private void caseResult(TestCaseFinished testCase) {


        out.println(String.join(",", (CharSequence) testCase.getTestCase().getTestSteps()));

    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::stepResult);
//        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::caseResult);
    }
}
