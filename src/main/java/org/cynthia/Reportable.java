package org.cynthia;

public interface Reportable {
    /**
     * Generates a report of all library items based on their current status.
     * @param status The status of items to include in the report
     * @return A string representation of the report.
     */
    String generateReport(String status);
}
