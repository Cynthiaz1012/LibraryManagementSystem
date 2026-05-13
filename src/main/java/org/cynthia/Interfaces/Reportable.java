package org.cynthia.Interfaces;

import org.cynthia.domain.Item;
import java.util.List;

public interface Reportable {
    void generateReport(List<Item> items);
}
