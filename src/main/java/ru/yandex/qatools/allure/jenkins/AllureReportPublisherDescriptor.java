package ru.yandex.qatools.allure.jenkins;

import com.google.common.base.Strings;
import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import org.kohsuke.stapler.StaplerRequest;
import ru.yandex.qatools.allure.jenkins.config.ReportBuildPolicy;

/**
 * User: eroshenkoam
 * Date: 10/9/13, 7:49 PM
 */
@Extension
public class AllureReportPublisherDescriptor extends BuildStepDescriptor<Publisher> {

    private String reportVersionDefault = AllureReportPlugin.DEFAULT_REPORT_VERSION;

    private String resultsPatternDefault = AllureReportPlugin.DEFAULT_RESULTS_PATTERN;

    public AllureReportPublisherDescriptor() {
        super(AllureReportPublisher.class);
    }

    @Override
    public String getDisplayName() {
        return AllureReportPlugin.DESCRIPTION;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean isApplicable(Class<? extends AbstractProject> jobType) {
        return true;
    }

    @SuppressWarnings("unused")
    public ReportBuildPolicy[] getReportBuildPolicies() {
        return ReportBuildPolicy.values();
    }


    @SuppressWarnings("unused")
    public String getResultsPatternDefault() {
        return resultsPatternDefault;
    }

    public void setResultsPatternDefault(String reportGlobDefault) {
        this.resultsPatternDefault = reportGlobDefault;
    }

    @SuppressWarnings("unused")
    public String getReportVersionDefault() {
        return reportVersionDefault;
    }

    public void setReportVersionDefault(String reportVersionDefault) {
        this.reportVersionDefault = reportVersionDefault;
    }

    @Override
    public boolean configure(StaplerRequest req, net.sf.json.JSONObject json) throws FormException {
        String resultsGlobDefaultValue = json.getString("resultsPatternDefault");
        if (!Strings.isNullOrEmpty(resultsGlobDefaultValue)) {
            setResultsPatternDefault(resultsGlobDefaultValue);
        }
        String reportVersionDefaultValue = json.getString("reportVersionDefault");
        if (!Strings.isNullOrEmpty(reportVersionDefaultValue)) {
            setReportVersionDefault(reportVersionDefaultValue);
        }
        save();
        return true;
    }
}
