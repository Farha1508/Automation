package Base;

import cucumber.api.PickleStepTestStep;
import cucumber.api.event.ConcurrentEventListener;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestStepFinished;
import cucumber.api.formatter.NiceAppendable;

import java.io.File;

public class FailedStepReport implements ConcurrentEventListener {
    private final NiceAppendable out;

    public FailedStepReport(Appendable out) {
        this.out = new NiceAppendable(out);
    }

    private void stepResult(TestStepFinished step) {
        String result = step.result.getStatus().toString();
        if(step.result.getStatus().toString().equals("FAILED")) {
            if(step.testStep instanceof PickleStepTestStep) {
                PickleStepTestStep testStep = (PickleStepTestStep)step.testStep;
                out.println("Failed step: "+testStep.getStepText());
                out.println("Status is: " + result);
                BaseUtil.testRailStatus = "Test failed at step: "+"\n"+testStep.getStepText()+"\n"+
                        "With error: "+"\n"+step.result.getError();
            }

        }
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::stepResult);
    }
}
